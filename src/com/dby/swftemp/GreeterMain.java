package com.dby.swftemp;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.swf.common.ConfigHelper;

public class GreeterMain {

   public static void main(String[] args) throws Exception {
/*     ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

     AWSCredentials awsCredentials = new ProfileCredentialsProvider().getCredentials();
     
     AmazonSimpleWorkflow swfClient = new AmazonSimpleWorkflowClient(awsCredentials, config);
     swfClient.setEndpoint("https://swf.us-west-2.amazonaws.com");

     String domain = "867530901";*/
	   
	 ConfigHelper configHelper = ConfigHelper.createConfig();
	 AmazonSimpleWorkflow swfClient = configHelper.createSWFClient();
	 String domain = configHelper.getDomain();

     GreeterWorkflowClientExternalFactory factory = new GreeterWorkflowClientExternalFactoryImpl(swfClient, domain);
     GreeterWorkflowClientExternal greeter = factory.getClient("someID");
     greeter.greet();
   }
}
