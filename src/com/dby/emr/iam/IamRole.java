package com.dby.emr.iam;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.services.identitymanagement.model.AddRoleToInstanceProfileRequest;
import com.amazonaws.services.identitymanagement.model.AttachRolePolicyRequest;
import com.amazonaws.services.identitymanagement.model.CreateInstanceProfileRequest;
import com.amazonaws.services.identitymanagement.model.CreateRoleRequest;
import com.amazonaws.services.identitymanagement.model.EntityAlreadyExistsException;
import com.amazonaws.services.identitymanagement.model.GetUserResult;
import com.amazonaws.services.identitymanagement.model.LimitExceededException;
import com.swf.common.ConfigHelper;

public class IamRole {
    private static String TEMP_ACCESS_KEY_ID = null;
    private static String TEMP_SECRET_ACCESS_KEY = null;

    AmazonIdentityManagementClient identityManagementClient;

    @Deprecated
    private IamRole() {
//        AWSCredentials credentials = null;
//        try {
//            TEMP_ACCESS_KEY_ID = PropEditor
//                    .getProp(PublicStr.TEMP_ACCESS_KEY_ID);
//            TEMP_SECRET_ACCESS_KEY = PropEditor
//                    .getProp(PublicStr.TEMP_SECRET_ACCESS_KEY);
//
//            System.out.println(TEMP_ACCESS_KEY_ID + "----"
//                    + TEMP_SECRET_ACCESS_KEY);
//
//            credentials = new BasicAWSCredentials(TEMP_ACCESS_KEY_ID,
//                    TEMP_SECRET_ACCESS_KEY);
//        } catch (Exception e) {
//            throw new AmazonClientException(
//                    "Cannot load the credentials from the credential profiles file. "
//                            + "Please make sure that your credentials file is at the correct "
//                            + "location (~/.aws/credentials), and is in valid format.",
//                    e);
//        }
//        identityManagementClient = new AmazonIdentityManagementClient(
//                credentials);
    }
    

    public IamRole(AWSCredentials credentials){
        identityManagementClient = new AmazonIdentityManagementClient(
                credentials);
    }


    public void createEMRole(){

        //EMR_EC2_DefaultRole
        try {
            CreateRoleRequest createRoleRequest = new CreateRoleRequest()
                    .withRoleName("EMR_EC2_DefaultRole")
                    .withPath("/")
                    .withAssumeRolePolicyDocument(
                            "{ \"Version\": \"2012-10-17\", \"Statement\": [ { \"Action\": \"sts:AssumeRole\", \"Sid\": \"\", \"Effect\": \"Allow\", \"Principal\": { \"Service\": \"ec2.amazonaws.com\" } } ] }");
            identityManagementClient.createRole(createRoleRequest);
        } catch (EntityAlreadyExistsException e) {
            System.out.println("EMR_EC2_DefaultRole Already Exists");
        }

        try {
            CreateInstanceProfileRequest createInstanceProfileRequest = new CreateInstanceProfileRequest()
                    .withInstanceProfileName("EMR_EC2_DefaultRole").withPath("/");
            identityManagementClient
                    .createInstanceProfile(createInstanceProfileRequest);
        } catch (EntityAlreadyExistsException e) {
            System.out.println("my-instance-profile Already Exists");
        }

        try {
            AddRoleToInstanceProfileRequest addRoleToInstanceProfileRequest = new AddRoleToInstanceProfileRequest()
                    .withInstanceProfileName("EMR_EC2_DefaultRole").withRoleName(
                            "EMR_EC2_DefaultRole");
            identityManagementClient
                    .addRoleToInstanceProfile(addRoleToInstanceProfileRequest);
        } catch (LimitExceededException e) {
            System.out.println("Cannot exceed quota for InstanceSessionsPerInstanceProfile: 1");
        }

        AttachRolePolicyRequest arpr = new AttachRolePolicyRequest()
                .withRoleName("EMR_EC2_DefaultRole")
                .withPolicyArn(
                        "arn:aws:iam::aws:policy/service-role/AmazonElasticMapReduceforEC2Role");
        identityManagementClient.attachRolePolicy(arpr);



        //EMR_DefaultRole
        try {
            CreateRoleRequest createRoleRequest02 = new CreateRoleRequest()
                    .withRoleName("EMR_DefaultRole")
                    .withPath("/")
                    .withAssumeRolePolicyDocument(
                            "{ \"Version\": \"2012-10-17\", \"Statement\": [ { \"Action\": \"sts:AssumeRole\", \"Sid\": \"\", \"Effect\": \"Allow\", \"Principal\": { \"Service\": \"elasticmapreduce.amazonaws.com\" } } ] }");
            identityManagementClient.createRole(createRoleRequest02);
        } catch (EntityAlreadyExistsException e) {
            System.out.println("EMR_DefaultRole Already Exists");
        }

        AttachRolePolicyRequest arpr02 = new AttachRolePolicyRequest()
                .withRoleName("EMR_DefaultRole")
                .withPolicyArn(
                        "arn:aws:iam::aws:policy/service-role/AmazonElasticMapReduceRole");
        identityManagementClient.attachRolePolicy(arpr02);

        System.out.println("FINISH CREATE ROLE");

    }

    public String getAccountId(){
        String accountId = null;

        try {
            GetUserResult getUserResult = identityManagementClient.getUser();
            accountId = getUserResult.getUser().getArn().split(":")[4];
        } catch (AmazonServiceException e) {
            String msg = e.getMessage();
            int arnIdx = msg.indexOf("arn:aws:iam::");
            accountId = msg.substring(arnIdx+13,arnIdx+25);
        } catch (AmazonClientException e) {
            e.printStackTrace();
        }

        return accountId;
    }
}
