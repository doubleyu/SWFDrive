package com.dby.swftemp;

import java.util.concurrent.TimeUnit;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import com.swf.common.ConfigHelper;

public class GreeterShutdownWorker  {
   public static void main(String[] args) throws Exception {
     ConfigHelper configHelper = ConfigHelper.createConfig();
     AmazonSimpleWorkflow service = configHelper.createSWFClient();
     String domain = configHelper.getDomain();
     
     String taskListToPoll = "HelloWorldList2.0";

     ActivityWorker aw = new ActivityWorker(service, domain, taskListToPoll);
     aw.addActivitiesImplementation(new GreeterActivitiesImpl());
     aw.shutdownNow();

     WorkflowWorker wfw = new WorkflowWorker(service, domain, taskListToPoll);
     wfw.addWorkflowImplementationType(GreeterWorkflowImpl.class);
     wfw.shutdownNow();
     
     System.out.println("ActivityHost and WorkflowHost shutdowned");
   }
}