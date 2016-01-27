package com.dby.redshift.database;

import com.amazonaws.auth.AWSCredentials;
import com.swf.common.ConfigHelper;


public class DateAndEvent {
//	private static String S3_ACCESS_KEY_ID = null;
//	private static String S3_SECRET_ACCESS_KEY = null;
	
	private static String TEMP_ACCESS_KEY_ID = null;
	private static String TEMP_SECRET_ACCESS_KEY = null;
	private ConfigHelper configHelper;
	private JDBCExecuteUpdate jdbcExecute;

	@Deprecated
    private DateAndEvent(){
        //		DescribeKeys dk = new DescribeKeys("abc");
//		S3_ACCESS_KEY_ID = dk.describe(PropEditor.getProp(PublicStr.ACCESS_KEY_ID));
//		S3_SECRET_ACCESS_KEY = dk.describe(PropEditor.getProp(PublicStr.SECRET_ACCESS_KEY));
//		System.out.println(S3_ACCESS_KEY_ID+"    "+S3_SECRET_ACCESS_KEY);

//        TEMP_ACCESS_KEY_ID = PropEditor.getProp(PublicStr.TEMP_ACCESS_KEY_ID);
//        TEMP_SECRET_ACCESS_KEY = PropEditor.getProp(PublicStr.TEMP_SECRET_ACCESS_KEY);
    }

    public DateAndEvent(AWSCredentials credentials){
        TEMP_ACCESS_KEY_ID = credentials.getAWSAccessKeyId();
        TEMP_SECRET_ACCESS_KEY = credentials.getAWSSecretKey();
    }
    
    public DateAndEvent(ConfigHelper configHelper){
    	this(configHelper.createRedshiftCredentials());
    	this.configHelper = configHelper;
    	this.jdbcExecute = new JDBCExecuteUpdate(configHelper);
    }

	public void initDateAndEvent() {
		String create_table_date = "create table if not exists date(dateid smallint not null distkey sortkey, caldate date not null, day character(3) not null, week smallint not null, month character(5) not null, qtr character(5) not null, year smallint not null, holiday boolean default('N'));";
		String create_table_event = "create table if not exists event(eventid integer not null distkey, venueid smallint not null, catid smallint not null, dateid smallint not null sortkey, eventname varchar(200), starttime timestamp);";
		

		String copy_date_from_endpoint = "copy date from 's3://awssampledb/tickit/date2008_pipe.txt' "
				+ "credentials 'aws_access_key_id="+TEMP_ACCESS_KEY_ID+";aws_secret_access_key="+TEMP_SECRET_ACCESS_KEY+"'"
				+ "delimiter '|';";
		
		String copy_event_from_endpoint = "copy event from 's3://awssampledb/tickit/allevents_pipe.txt' "
				+ "credentials 'aws_access_key_id="+TEMP_ACCESS_KEY_ID+";aws_secret_access_key="+TEMP_SECRET_ACCESS_KEY+"'"
				+ "delimiter '|' timeformat 'YYYY-MM-DD HH:MI:SS';";
		
		
		/*
		 * create table date
		 */
		System.out.println("creating table date...");
		jdbcExecute.executeUpdate(create_table_date);
		
		
		/*
		 * create table event
		 */
		System.out.println("creating table event...");
		jdbcExecute.executeUpdate(create_table_event);
		
		
		/*
		 * copy org from endpoint
		 */
		System.out.println("copying date from endpoint...");
		jdbcExecute.executeUpdate(copy_date_from_endpoint);
		
		
		/*
		 * unload org to endpoint
		 */
		System.out.println("copying event from endpoint...");
		jdbcExecute.executeUpdate(copy_event_from_endpoint);
		
		System.out.println("FINISH!");
		
	}
}
