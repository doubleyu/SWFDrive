package com.dby.swf;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import com.swf.common.ConfigHelper;

public class ActivityAndWorkflowHost  {
   public static void main(String[] args) throws Exception {
	   ConfigHelper configHelper = ConfigHelper.createConfig();
       AmazonSimpleWorkflow swfService = configHelper.createSWFClient();
       String domain = configHelper.getDomain();
     

//     service.setEndpoint("https://swf.us-west-2.amazonaws.com");

     String taskListToPoll = "HelloSwfList";

     ActivityWorker aw = new ActivityWorker(swfService, domain, taskListToPoll);
  // Create activity implementations
     UnloadActivities unloadAct = new UnloadActivitiesImpl();
     EMRActivities emrAct = new EMRActivitiesImpl();
     LoadActivities loadAct = new LoadActivitiesImpl();
     
     aw.addActivitiesImplementation(unloadAct);
     aw.addActivitiesImplementation(emrAct);
     aw.addActivitiesImplementation(loadAct);
     aw.start();

     WorkflowWorker wfw = new WorkflowWorker(swfService, domain, taskListToPoll);
     wfw.addWorkflowImplementationType(SwfWorkflowImpl.class);
     wfw.start();
   }
}