package com.dby.redshift.database;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.Owner;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.swf.common.ConfigHelper;


public class UnloadEvent {

    private static String DEFAULT_DATE = "2008-01-05";

    private static String S3_ACCESS_KEY_ID = null;
    private static String S3_SECRET_ACCESS_KEY = null;
    private ConfigHelper configHelper;
    private JDBCExecuteUpdate jdbcExecute;
    private AmazonS3 s3client = null;

    @Deprecated
    public UnloadEvent() {
//        DescribeKeys dk = new DescribeKeys("abc");
//        S3_ACCESS_KEY_ID = dk.describe(PropEditor.getProp(PublicStr.ACCESS_KEY_ID));
//        S3_SECRET_ACCESS_KEY = dk.describe(PropEditor.getProp(PublicStr.SECRET_ACCESS_KEY));
//        System.out.println(S3_ACCESS_KEY_ID + "    " + S3_SECRET_ACCESS_KEY);
//
//        s3client = new AmazonS3Client(new BasicAWSCredentials(S3_ACCESS_KEY_ID, S3_SECRET_ACCESS_KEY));
    }

    public UnloadEvent(AWSCredentials credentials) {
        S3_ACCESS_KEY_ID = credentials.getAWSAccessKeyId();
        S3_SECRET_ACCESS_KEY = credentials.getAWSSecretKey();
        System.out.println(S3_ACCESS_KEY_ID+"-----"+S3_SECRET_ACCESS_KEY);
        s3client = new AmazonS3Client(credentials);
    }
    
    public UnloadEvent(ConfigHelper configHelper){
    	this(configHelper.createS3Credentials());
    	this.configHelper = configHelper;
    	this.jdbcExecute = new JDBCExecuteUpdate(configHelper);
    }

    public void unloadEvent(String inputDate, String s3Path) {

        String date = inputDate;

//        String s3Folder = date.replace("-", "");


        String sqlSelectDate = "select eventid, venueid, catid, event.dateid, eventname, starttime from event,date"
                + " where event.dateid = date.dateid"
                + " AND caldate = \\'" + date + "\\'";


//        String s3Path = "s3://redshift-lyy/unload/" + s3Folder + "/";


        String unload_org_to_s3 = "unload ('" + sqlSelectDate + "')"
                + "to '" + s3Path + "'"
                + "credentials 'aws_access_key_id=" + S3_ACCESS_KEY_ID + ";aws_secret_access_key=" + S3_SECRET_ACCESS_KEY + "'"
                + "PARALLEL OFF; ";


        System.out.println(unload_org_to_s3);

		/*
         * unload selected event to s3
		 */
        System.out.println("unloading org to s3...");
        jdbcExecute.executeUpdate(unload_org_to_s3);

        System.out.println("finish uploading, the path is : \n" + s3Path);

        makePublic(s3Path);


    }

    public void makePublic(String s3Path) {
        String bucketName = "";
        String keyName = "";

        Pattern pattern = Pattern.compile("s3://(.+?)/(.+)");
        Matcher m = pattern.matcher(s3Path);

        if (m.matches()) {
            bucketName = m.group(1);
            keyName = m.group(2);
        }

//		System.out.println(bucketName);
//		System.out.println(keyName);

//        DescribeKeys dk = new DescribeKeys("abc");


        AccessControlList acl = new AccessControlList();
        acl.grantPermission(GroupGrantee.AllUsers, Permission.FullControl);

        Owner owner = new Owner();
        owner.setId(configHelper.getS3OwnerId());
        owner.setDisplayName(configHelper.getS3OwnerDisplayName());

		System.out.println("wonerID : "+ owner.getId());
		System.out.println("wonerName : "+ owner.getDisplayName());

        acl.setOwner(owner);

        try {
//			s3client.setObjectAcl(bucketName, keyName+"000", acl);

            ObjectListing olst = s3client.listObjects(new ListObjectsRequest().withBucketName(bucketName).withPrefix(keyName));
            List<S3ObjectSummary> soslst = olst.getObjectSummaries();
            for (S3ObjectSummary sos : soslst) {
            	System.out.println(bucketName);
                System.out.println(sos.getKey());
                s3client.setObjectAcl(bucketName, sos.getKey(), acl);
            }

        } catch (AmazonS3Exception e) {
            e.printStackTrace();
            System.err.println("NO SUCH KEY !!!!");
        }

        System.out.println("finish managing access control");

    }
}
