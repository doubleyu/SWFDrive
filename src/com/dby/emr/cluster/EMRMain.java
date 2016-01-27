package com.dby.emr.cluster;

import java.io.IOException;

import com.dby.emr.iam.IamRole;
import com.dby.emr.s3.InstanceS3;
import com.dby.emr.s3.MyS3;
import com.swf.common.ConfigHelper;


public class EMRMain {

    public static void main(String[] args) {
    	//s3://redshift-lyy-east/unload/20080105/ s3://hadoop-lyy-east/output/output1
    	
    	if(args.length<2){
    		System.err.println("input an input path and an output path");
    		System.exit(0);
    	}
    		
    	
    	ConfigHelper configHelper = null;
		try {
			configHelper = ConfigHelper.createConfig();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        MyS3 mys3 = new MyS3(configHelper.createS3Credentials());
        InstanceS3 ins3 = new InstanceS3(configHelper.createEMRCredentials());

        int inputSize = mys3.getFolderSize(args[0]);
        if (inputSize == 0) {
            System.exit(0);
        }

        System.out.println("input directory not null, continue...");

        mys3.deleteFolder(args[1]);

        System.out.println("creating EMRole...");
        IamRole iamr = new IamRole(configHelper.createEMRCredentials());
        iamr.createEMRole();

        System.out.println("creating LogBucket...");
        String logBucket = "emr-log-"+iamr.getAccountId();
        ins3.createBucket(logBucket);

        System.out.println("begin create EMR cluster...");

        CreateEMRCluster cemrc = new CreateEMRCluster(configHelper.createEMRCredentials());
        cemrc.createClusterWithStep(args[0], args[1], logBucket);
    }
}
