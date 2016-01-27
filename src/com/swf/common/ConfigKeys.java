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

public class ConfigKeys {

	public static final String SWF_SERVICE_URL_KEY = "SWF.Service.Url";
	public static final String SWF_ACCESS_ID_KEY = "SWF.Access.ID";
	public static final String SWF_SECRET_KEY_KEY = "SWF.Secret.Key";

	public static final String S3_SERVICE_URL_KEY = "S3.Service.Url";
	public static final String S3_ACCESS_ID_KEY = "S3.Access.ID";
	public static final String S3_SECRET_KEY_KEY = "S3.Secret.Key";
	
	//temp
	public static final String S3_OWNER_ID_KEY = "S3.Owner.ID";
	public static final String S3_OWNER_DISPLAYNAME_KEY = "S3.Owner.DisplayName";

	public static final String REDSHIFT_SERVICE_URL_KEY = "Redshift.Service.Url";
	public static final String REDSHIFT_ACCESS_ID_KEY = "Redshift.Access.ID";
	public static final String REDSHIFT_SECRET_KEY_KEY = "Redshift.Secret.Key";
	
	public static final String REDSHIFT_DB_USER_KEY = "Redshift.Db.User";
	public static final String REDSHIFT_DB_PASSWD_KEY = "Redshift.Db.Passwd";
	public static final String REDSHIFT_JDBC_URL_KEY = "Redshift.Jdbc.Url";

	public static final String EMR_SERVICE_URL_KEY = "EMR.Service.Url";
	public static final String EMR_ACCESS_ID_KEY = "EMR.Access.ID";
	public static final String EMR_SECRET_KEY_KEY = "EMR.Secret.Key";

	public static final String DOMAIN_KEY = "domain";
	public static final String DOMAIN_RETENTION_PERIOD_KEY = "domainRetentionPeriodInDays";

	public static final String SWF_LAMBDA_ROLE_ARN = "SWF.LambdaRole.ARN";
	public static final String SWF_LAMBDA_FUNCTION = "SWF.LambdaFunction.Name";
	public static final String SWF_LAMBDA_FUNCTION_INPUT = "SWF.LambdaFunction.Input";
	
	
	//-------------------------
	
	public static final String ENV_ACCESS_PROPERTIES_PATH = "AWS_ACCESS_PROPERTIES_PATH";
	public static final String HOME_DIRECTORY_PROPERTY = "user.home";
	public static final String ACCESS_PROPERTIES_FILENAME = "access.properties";

}
