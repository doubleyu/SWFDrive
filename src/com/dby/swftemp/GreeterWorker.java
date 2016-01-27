package com.dby.swftemp;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import com.swf.common.ConfigHelper;

public class GreeterWorker  {
   public static void main(String[] args) throws Exception {
     ConfigHelper configHelper = ConfigHelper.createConfig();
     AmazonSimpleWorkflow service = configHelper.createSWFClient();
     String domain = configHelper.getDomain();
     
     String taskListToPoll = "HelloWorldList2.0";

     ActivityWorker aw = new ActivityWorker(service, domain, taskListToPoll);
     aw.addActivitiesImplementation(new GreeterActivitiesImpl());
     aw.start();

     WorkflowWorker wfw = new WorkflowWorker(service, domain, taskListToPoll);
     wfw.addWorkflowImplementationType(GreeterWorkflowImpl.class);
     wfw.start();
     
     System.out.println("ActivityHost and WorkflowHost started");
   }
}