package com.dby.emr.s3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.swf.common.ConfigHelper;


public class InstanceS3 {
	private static String TEMP_ACCESS_KEY_ID = null;
	private static String TEMP_SECRET_ACCESS_KEY = null;

	AmazonS3 s3client;

	@Deprecated
	private InstanceS3() {
		super();
		
//		TEMP_ACCESS_KEY_ID = PropEditor
//				.getProp(PublicStr.TEMP_ACCESS_KEY_ID);
//		TEMP_SECRET_ACCESS_KEY = PropEditor
//				.getProp(PublicStr.TEMP_SECRET_ACCESS_KEY);
//
//		System.out.println(TEMP_ACCESS_KEY_ID + "----" + TEMP_SECRET_ACCESS_KEY);
//		AWSCredentials credentials = new BasicAWSCredentials(TEMP_ACCESS_KEY_ID,
//				TEMP_SECRET_ACCESS_KEY);
//		s3client = new AmazonS3Client(credentials);
	}

    public InstanceS3(AWSCredentials credentials){
        s3client = new AmazonS3Client(credentials);
    }
    

	public static void main(String[] args) {
		
	}
	
	public void createBucket(String bucketName){
		
		try {
			CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
			s3client.createBucket(createBucketRequest);
		} catch (AmazonServiceException e) {
			if(e.getErrorCode().equals("BucketAlreadyOwnedByYou")){
				System.out.println("Bucket Already Owned By You !");
			}else{
				e.printStackTrace();
			}
		} catch (AmazonClientException e) {
			e.printStackTrace();
		}
	}
	
}
