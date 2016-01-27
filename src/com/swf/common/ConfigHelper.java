/*
 * Copyright 2012-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.swf.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;





//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduce;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClient;
import com.amazonaws.services.redshift.AmazonRedshift;
import com.amazonaws.services.redshift.AmazonRedshiftClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.dby.util.SafeProperties;

/**
 * Configuration Helper to used to create SWF and S3 clients
 */

public class ConfigHelper {
	private SafeProperties propConfig;
	private File propFile;
	

	private String swfServiceUrl;
	private String swfAccessId;
	private String swfSecretKey;

	private String emrServiceUrl;
	private String emrAccessId;
	private String emrSecretKey;

	private String redshiftServiceUrl;
	private String redshiftAccessId;
	private String redshiftSecretKey;

	private String redshiftDbUser;
	private String redshiftDbPasswd;
	private String redshiftJdbcUrl;

	private String s3ServiceUrl;
	private String s3AccessId;
	private String s3SecretKey;
	//the two owner attributes are set inflexible temporarily
	private String s3OwnerId;
	private String s3OwnerDisplayName;

	private String swfLambdaRoleArn;
	private String swfLambdaFunction;
	private String swfLambdaFunctionInput;

	private String domain;
	private long domainRetentionPeriodInDays;

	private ConfigHelper(File propertiesFile) throws IOException {
		this.propFile = propertiesFile;
		loadProperties(propertiesFile);
	}

	private void loadProperties(File propertiesFile) throws IOException {

		FileInputStream inputStream = new FileInputStream(propertiesFile);
		propConfig = new SafeProperties();
		propConfig.load(inputStream);

		this.swfServiceUrl = propConfig
				.getProperty(ConfigKeys.SWF_SERVICE_URL_KEY);
		this.swfAccessId = propConfig
				.getProperty(ConfigKeys.SWF_ACCESS_ID_KEY);
		this.swfSecretKey = propConfig
				.getProperty(ConfigKeys.SWF_SECRET_KEY_KEY);

		this.emrServiceUrl = propConfig
				.getProperty(ConfigKeys.EMR_SERVICE_URL_KEY);
		this.emrAccessId = propConfig
				.getProperty(ConfigKeys.EMR_ACCESS_ID_KEY);
		this.emrSecretKey = propConfig
				.getProperty(ConfigKeys.EMR_SECRET_KEY_KEY);

		this.redshiftServiceUrl = propConfig
				.getProperty(ConfigKeys.REDSHIFT_SERVICE_URL_KEY);
		this.redshiftAccessId = propConfig
				.getProperty(ConfigKeys.REDSHIFT_ACCESS_ID_KEY);
		this.redshiftSecretKey = propConfig
				.getProperty(ConfigKeys.REDSHIFT_SECRET_KEY_KEY);

		this.redshiftDbUser = propConfig
				.getProperty(ConfigKeys.REDSHIFT_DB_USER_KEY);
		this.redshiftDbPasswd = propConfig
				.getProperty(ConfigKeys.REDSHIFT_DB_PASSWD_KEY);
		this.redshiftJdbcUrl = propConfig
				.getProperty(ConfigKeys.REDSHIFT_JDBC_URL_KEY);

		this.s3ServiceUrl = propConfig
				.getProperty(ConfigKeys.S3_SERVICE_URL_KEY);
		this.s3AccessId = propConfig.getProperty(ConfigKeys.S3_ACCESS_ID_KEY);
		this.s3SecretKey = propConfig
				.getProperty(ConfigKeys.S3_SECRET_KEY_KEY);
		//the two owner attributes are set inflexible temporarily
		this.s3OwnerId = propConfig.getProperty(ConfigKeys.S3_OWNER_ID_KEY);
		this.s3OwnerDisplayName = propConfig.getProperty(ConfigKeys.S3_OWNER_DISPLAYNAME_KEY);

		this.swfLambdaRoleArn = propConfig
				.getProperty(ConfigKeys.SWF_LAMBDA_ROLE_ARN);
		this.swfLambdaFunction = propConfig
				.getProperty(ConfigKeys.SWF_LAMBDA_FUNCTION);
		this.swfLambdaFunctionInput = propConfig
				.getProperty(ConfigKeys.SWF_LAMBDA_FUNCTION_INPUT);

		this.domain = propConfig.getProperty(ConfigKeys.DOMAIN_KEY);
		this.domainRetentionPeriodInDays = Long.parseLong(propConfig
				.getProperty(ConfigKeys.DOMAIN_RETENTION_PERIOD_KEY));
	}

	public static ConfigHelper createConfig() throws IOException,
			IllegalArgumentException {

		ConfigHelper configHelper = null;

		boolean envVariableExists = false;
		// first check the existence of environment variable
		String envConfigPath = System
				.getenv(ConfigKeys.ENV_ACCESS_PROPERTIES_PATH);
		if (envConfigPath != null && envConfigPath.length() > 0) {
			envVariableExists = true;
		}
		File accessProperties = new File(envConfigPath,
				ConfigKeys.ACCESS_PROPERTIES_FILENAME);

		if (accessProperties.exists()) {
			configHelper = new ConfigHelper(accessProperties);
		} else {
			// try checking the existence of file on relative path.
			try {
				accessProperties = new File(Thread.currentThread()
						.getContextClassLoader().getResource("").toURI()
						.getPath(), ConfigKeys.ACCESS_PROPERTIES_FILENAME);
				configHelper = new ConfigHelper(accessProperties);
			} catch (Exception e) {
				e.printStackTrace();
				throw new FileNotFoundException(
						"Cannot find AWS_SWF_SAMPLES_CONFIG environment variable, Exiting!!!");
			}
		}
		

		return configHelper;
	}
	
	public void setProp(String propName, String value){
		propConfig.setProperty(propName, value);
		
		try {
			FileOutputStream fos = new FileOutputStream(propFile);
			propConfig.store(fos, null);
	        fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
       
	}
	

	public AmazonSimpleWorkflow createSWFClient() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(
				this.swfAccessId, this.swfSecretKey);
		AmazonSimpleWorkflow client = new AmazonSimpleWorkflowClient(
				awsCredentials);
		client.setEndpoint(this.swfServiceUrl);
		return client;
	}

	public AmazonElasticMapReduce createEMRClient() {
		AWSCredentials emrAWSCredentials = new BasicAWSCredentials(
				this.emrAccessId, this.emrSecretKey);
		AmazonElasticMapReduce client = new AmazonElasticMapReduceClient(
				emrAWSCredentials);
		return client;
	}

	public AmazonRedshift createRedshiftClient() {
		AWSCredentials redshiftAWSCredentials = new BasicAWSCredentials(
				this.redshiftAccessId, this.redshiftSecretKey);
		AmazonRedshift client = new AmazonRedshiftClient(redshiftAWSCredentials);
		return client;
	}

	public AmazonS3 createS3Client() {
		AWSCredentials s3AWSCredentials = new BasicAWSCredentials(
				this.s3AccessId, this.s3SecretKey);
		return createS3Client(s3AWSCredentials);
	}
	
	public AmazonS3 createS3Client(AWSCredentials credentials){
		return new AmazonS3Client(credentials);
	}

	//-------------------------------------------
	public AWSCredentials createEMRCredentials(){
		return new BasicAWSCredentials(this.emrAccessId, this.emrSecretKey);
	}
	
	public AWSCredentials createRedshiftCredentials(){
		return new BasicAWSCredentials(this.redshiftAccessId, this.redshiftSecretKey);
	}
	
	public AWSCredentials createS3Credentials() {
		return new BasicAWSCredentials(this.s3AccessId, this.s3SecretKey);
	}
	//-------------------------------------------
	public String getRedshiftJdbcUrl() {
		return redshiftJdbcUrl;
	}

	public void setRedshiftJdbcUrl(String redshiftJdbcUrl) {
		this.redshiftJdbcUrl = redshiftJdbcUrl;
		this.setProp(ConfigKeys.REDSHIFT_JDBC_URL_KEY, redshiftJdbcUrl);
	}
	

	public String getRedshiftDbUser() {
		return redshiftDbUser;
	}

	public String getRedshiftDbPasswd() {
		return redshiftDbPasswd;
	}
	

	public String getS3OwnerId() {
		return s3OwnerId;
	}

	public String getS3OwnerDisplayName() {
		return s3OwnerDisplayName;
	}

	public String getDomain() {
		return domain;
	}

	public long getDomainRetentionPeriodInDays() {
		return domainRetentionPeriodInDays;
	}

	public String getValueFromConfig(String key) {
		return propConfig.getProperty(key);
	}

	public String getSwfLambdaRoleArn() {
		return swfLambdaRoleArn;
	}

	public String getSwfLambdaFunction() {
		return swfLambdaFunction;
	}

	public String getSwfLambdaFunctionInput() {
		return swfLambdaFunctionInput;
	}

	public static void main(String args[]) throws IllegalArgumentException,
			IOException {
		ConfigHelper ch = createConfig();
		String accessKeyId = ch.createS3Credentials().getAWSAccessKeyId();
		System.out.println(accessKeyId);
		ch.setRedshiftJdbcUrl("from memory");
		
		System.out.println(ch.getRedshiftJdbcUrl());
	}

}
