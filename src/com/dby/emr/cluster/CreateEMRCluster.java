package com.dby.emr.cluster;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClient;
import com.amazonaws.services.elasticmapreduce.model.ActionOnFailure;
import com.amazonaws.services.elasticmapreduce.model.AddJobFlowStepsRequest;
import com.amazonaws.services.elasticmapreduce.model.AddJobFlowStepsResult;
import com.amazonaws.services.elasticmapreduce.model.ClusterSummary;
import com.amazonaws.services.elasticmapreduce.model.DescribeStepRequest;
import com.amazonaws.services.elasticmapreduce.model.DescribeStepResult;
import com.amazonaws.services.elasticmapreduce.model.HadoopJarStepConfig;
import com.amazonaws.services.elasticmapreduce.model.JobFlowInstancesConfig;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowRequest;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowResult;
import com.amazonaws.services.elasticmapreduce.model.StepConfig;
import com.amazonaws.services.elasticmapreduce.model.StepExecutionState;
import com.amazonaws.services.elasticmapreduce.model.StepStatus;
import com.swf.common.ConfigHelper;

public class CreateEMRCluster {

    private static String TEMP_ACCESS_KEY_ID = null;
    private static String TEMP_SECRET_ACCESS_KEY = null;

    AmazonElasticMapReduceClient emr;
    AWSCredentials credentials;

    @Deprecated
    private CreateEMRCluster(){ 
//        TEMP_ACCESS_KEY_ID = PropEditor
//                .getProp(PublicStr.TEMP_ACCESS_KEY_ID);
//        TEMP_SECRET_ACCESS_KEY = PropEditor
//                .getProp(PublicStr.TEMP_SECRET_ACCESS_KEY);
//
//        System.out.println(TEMP_ACCESS_KEY_ID + "----" + TEMP_SECRET_ACCESS_KEY);
//        AWSCredentials credentials = new BasicAWSCredentials(TEMP_ACCESS_KEY_ID,
//                TEMP_SECRET_ACCESS_KEY);
//
//        emr = new AmazonElasticMapReduceClient(credentials);
////        emr.setEndpoint("elasticmapreduce.us-west-2.amazonaws.com");
//
//        this.credentials = credentials;
    }

    public CreateEMRCluster(AWSCredentials credentials){
        emr = new AmazonElasticMapReduceClient(credentials);
//        emr.setEndpoint("elasticmapreduce.us-west-2.amazonaws.com");

        this.credentials = credentials;
    }
    
    public CreateEMRCluster(ConfigHelper configHelper){
    	this(configHelper.createEMRCredentials());
    }

    public void createClusterWithStep(String inputPath, String outputPath, String logBucket) {
    	
    	//if there is no Cluster exists, create
    	if(emr.listClusters().getClusters().isEmpty()){
    		
            String logUri = "s3://"+logBucket+"/";

            RunJobFlowRequest request = new RunJobFlowRequest()
                    .withName("Create cluster with ReleaseLabel")
                    .withReleaseLabel("emr-4.2.0")
                            // .withSteps(customStep)
                    .withServiceRole("EMR_DefaultRole")
                    .withJobFlowRole("EMR_EC2_DefaultRole")
                    .withInstances(
                            new JobFlowInstancesConfig().withInstanceCount(3)
                                    .withKeepJobFlowAliveWhenNoSteps(true)
                                    .withMasterInstanceType("m3.xlarge")
                                    .withSlaveInstanceType("m3.xlarge"))
                    .withVisibleToAllUsers(true).withLogUri(logUri)
                    .withLogUri(logUri);

            RunJobFlowResult result = emr.runJobFlow(request);
            
    	}
    	

        // --------------------------------------------------------------------------

        ClusterSummary cluster = emr.listClusters().getClusters().get(0);

        HadoopJarStepConfig hadoopConfigAdd = new HadoopJarStepConfig()
                .withJar(
                        "s3://hadoop-lyy/code/hadoop-mapreduce-examples-2.6.0.jar")

                .withMainClass("wordcount")
                .withArgs("-Dfs.s3.canned.acl=BucketOwnerFullControl",
                        inputPath, outputPath);

        StepConfig customStepAdd = new StepConfig("Step2", hadoopConfigAdd)
                .withActionOnFailure(ActionOnFailure.CONTINUE);
        AddJobFlowStepsResult resultAdd = emr
                .addJobFlowSteps(new AddJobFlowStepsRequest().withJobFlowId(
                        cluster.getId()).withSteps(customStepAdd));

        System.out.println(resultAdd.getStepIds());

        //

        DescribeStepRequest describe = new DescribeStepRequest()
                .withStepId(resultAdd.getStepIds().get(0));

        describe.setClusterId(cluster.getId());
        describe.setRequestCredentials(credentials);

        DescribeStepResult res = emr.describeStep(describe);
        StepStatus status = res.getStep().getStatus();
        String stas = status.getState();

        while (stas.equals(StepExecutionState.PENDING.name())
                || stas.equals(StepExecutionState.RUNNING.name())) {
            try {
                Thread.sleep(5000);
                res = emr.describeStep(describe);
                status = res.getStep().getStatus();
                stas = status.getState();
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (stas.equals(StepExecutionState.COMPLETED.name())) {
            System.out.println("\n step complete");
        } else if (stas.equals(StepExecutionState.FAILED.name())
                || stas.equals(StepExecutionState.CANCELLED.name())) {
            System.out.println("\n step failed");
        }

    }
}