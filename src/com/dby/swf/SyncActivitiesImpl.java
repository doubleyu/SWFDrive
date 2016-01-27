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

import com.dby.emr.cluster.EMRMain;
import com.dby.redshift.database.RedshiftUnloadMain;
import com.dby.redshift.emr.RedshiftLoadMain;

/**
 * Implementation of the hello world activities
 */
public class SyncActivitiesImpl implements SyncActivities {
    
	@Override
	public void actUnload(String date) {
		System.out.println("Parameter date : " + date + "!");
		
		RedshiftUnloadMain.main(new String[]{date});
	}
	
	@Override
	public void actEMR(String inputPath, String outputPath) {
		
		System.out.println("inputPath : "+inputPath+" outputPath : "+outputPath);
		
		EMRMain.main(new String[]{inputPath, outputPath});
	}
	
	@Override
	public void actLoad(String inputPath) {
		System.out.println("load inputPath : "+inputPath);
		
		RedshiftLoadMain.main(new String[]{inputPath});
	}

}
