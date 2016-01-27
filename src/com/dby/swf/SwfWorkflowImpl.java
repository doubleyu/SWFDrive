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
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;


/**
 * Implementation of the hello world workflow
 */
public class SwfWorkflowImpl implements SwfWorkflow{
	
	TestActivitiesClient testClient = new TestActivitiesClientImpl();

    UnloadActivitiesClient unloadClient = new UnloadActivitiesClientImpl();
    
    EMRActivitiesClient emrClient = new EMRActivitiesClientImpl();
    
    LoadActivitiesClient loadClient = new LoadActivitiesClientImpl();
	
//	SyncActivitiesClient synClient = new SyncActivitiesClientImpl();

    @Override
    public void helloSwf(String name) {
    	
    	//eg:2008-01-05
    	Promise promise01 = unloadClient.actUnload("2008-01-05");
        
        //eg:s3://redshift-lyy-east/unload/20080105/ s3://hadoop-lyy-east/output/output1
    	Promise promise02 = emrClient.actEMR("s3://redshift-lyy-east/unload/20080105/", "s3://hadoop-lyy-east/output/output1",promise01);
        
        //eg:s3://hadoop-lyy-east/output/output1
    	Promise promise03 = loadClient.actLoad("s3://hadoop-lyy-east/output/output1",promise02);
    	
//    	Promise promise01 = synClient.actUnload("2008-01-05");
//    	
//    	Promise promise02 = synClient.actEMR("s3://redshift-lyy-east/unload/20080105/", "s3://hadoop-lyy-east/output/output1", promise01);
//    	
//    	Promise promise03 = synClient.actLoad("s3://hadoop-lyy-east/output/output1", promise02);
        
    }
    
}