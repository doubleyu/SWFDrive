package com.dby.redshift.emr;

import com.amazonaws.auth.AWSCredentials;
import com.dby.redshift.database.JDBCExecuteUpdate;
import com.dby.util.DescribeKeys;
import com.dby.util.PropEditor;
import com.dby.util.PublicStr;
import com.swf.common.ConfigHelper;


public class LoadEMResult {
    private static String S3_ACCESS_KEY_ID = null;
    private static String S3_SECRET_ACCESS_KEY = null;
    private JDBCExecuteUpdate jdbcExecute;

    @Deprecated
    private LoadEMResult(){
        DescribeKeys dk = new DescribeKeys("abc");
        S3_ACCESS_KEY_ID = dk.describe(PropEditor.getProp(PublicStr.ACCESS_KEY_ID));
        S3_SECRET_ACCESS_KEY = dk.describe(PropEditor.getProp(PublicStr.SECRET_ACCESS_KEY));
        System.out.println(S3_ACCESS_KEY_ID+"    "+S3_SECRET_ACCESS_KEY);
    }

    public LoadEMResult(AWSCredentials credentials){
        S3_ACCESS_KEY_ID = credentials.getAWSAccessKeyId();
        S3_SECRET_ACCESS_KEY = credentials.getAWSSecretKey();
    }
    
    public LoadEMResult(ConfigHelper configHelper){
    	this(configHelper.createS3Credentials());
    	this.jdbcExecute = new JDBCExecuteUpdate(configHelper);
    }

    public void loadResult(String resPath){

        String create_table_res = "create table IF NOT EXISTS res(key varchar(100), value integer);";

        String copy_res_from_s3 = "copy res from '"+resPath+"' "
                + "credentials 'aws_access_key_id="+S3_ACCESS_KEY_ID+";aws_secret_access_key="+S3_SECRET_ACCESS_KEY+"' "
                + "delimiter '\t';";

		/*
		 * create table res
		 */
        System.out.println("creating table res...");
        jdbcExecute.executeUpdate(create_table_res);


		/*
		 * copy res from s3
		 */
        System.out.println("copying res from s3...");
        jdbcExecute.executeUpdate(copy_res_from_s3);

        System.out.println("FINISH COPY");
    }
}

