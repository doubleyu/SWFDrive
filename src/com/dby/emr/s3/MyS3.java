package com.dby.emr.s3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyS3 {
	private static String ACCESS_KEY_ID = null;
	private static String SECRET_ACCESS_KEY = null;

	AmazonS3 s3client;

	@Deprecated
	private MyS3() {
		super();
//		DescribeKeys dk = new DescribeKeys("abc");
//		ACCESS_KEY_ID = dk
//				.describe(PropEditor.getProp(PublicStr.ACCESS_KEY_ID));
//		SECRET_ACCESS_KEY = dk.describe(PropEditor
//				.getProp(PublicStr.SECRET_ACCESS_KEY));
//		System.out.println(ACCESS_KEY_ID + "    " + SECRET_ACCESS_KEY);
//
//		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID,
//				SECRET_ACCESS_KEY);
//		s3client = new AmazonS3Client(credentials);
	}

    public MyS3(AWSCredentials credentials){
        s3client = new AmazonS3Client(credentials);
    }


	public int getFolderSize(String s3Path) {

		String bucketName = "";
		String prefix = "";

		Pattern pattern = Pattern.compile("s3://(.+?)/(.+)");
		Matcher m = pattern.matcher(s3Path);

		if (m.matches()) {
			bucketName = m.group(1);
			prefix = m.group(2);
		}

		String str = "";
		int Finalsize = 0;

		ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
				.withBucketName(bucketName).withPrefix(prefix);
		ObjectListing objectListing;

		do {
			objectListing = s3client.listObjects(listObjectsRequest);
			for (S3ObjectSummary objectSummary : objectListing
					.getObjectSummaries()) {

				str = (objectSummary.getSize() + "");
				int size = Integer.parseInt(str);

				Finalsize = Finalsize + size;

			}
			listObjectsRequest.setMarker(objectListing.getNextMarker());
		} while (objectListing.isTruncated());

		return Finalsize;
	}

	public void deleteFolder(String s3Path) {
		String bucketName = "";
		String prefix = "";
		List<KeyVersion> keylst = new ArrayList<KeyVersion>();

		Pattern pattern = Pattern.compile("s3://(.+?)/(.+)");
		Matcher m = pattern.matcher(s3Path);

		if (m.matches()) {
			bucketName = m.group(1);
			prefix = m.group(2);
		}

		ObjectListing olst = s3client.listObjects(new ListObjectsRequest()
				.withBucketName(bucketName).withPrefix(prefix));
		List<S3ObjectSummary> soslst = olst.getObjectSummaries();
		for (S3ObjectSummary sos : soslst) {
			System.out.println(sos.getKey());
			keylst.add(new KeyVersion(sos.getKey()));
		}
		
		keylst.add(new KeyVersion(prefix));
		
		DeleteObjectsRequest multiObjectDeleteRequest = new DeleteObjectsRequest(
                bucketName).withKeys(keylst);
		
		s3client.deleteObjects(multiObjectDeleteRequest);
	}
	
	
	public boolean existsKey(String s3Path)
	{
		String bucketName = "";
		String prefix = "";

		Pattern pattern = Pattern.compile("s3://(.+?)/(.+)");
		Matcher m = pattern.matcher(s3Path);

		if (m.matches()) {
			bucketName = m.group(1);
			prefix = m.group(2);
		}


		ListObjectsRequest request = new ListObjectsRequest().withBucketName(bucketName).withPrefix(prefix);

		ObjectListing response = s3client.listObjects(request);
		

		System.out.println(response.getObjectSummaries().size());
		
		return response.getObjectSummaries().size()>0;

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
	

	public static void main(String[] args) {
		
		MyS3 mys3 = new MyS3();
		
        mys3.getFolderSize("s3://redshift-lyy/unload/");
	}
	
}
