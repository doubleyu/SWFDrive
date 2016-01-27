package com.dby.swf;

import com.dby.emr.cluster.EMRMain;

public class EMRActivitiesImpl implements EMRActivities {

	@Override
	public void actEMR(String inputPath, String outputPath) {
		
		System.out.println("inputPath : "+inputPath+" outputPath : "+outputPath);
		
		EMRMain.main(new String[]{inputPath, outputPath});
	}

}
