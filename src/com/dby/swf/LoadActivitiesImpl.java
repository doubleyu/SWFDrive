package com.dby.swf;

import com.dby.redshift.emr.RedshiftLoadMain;

public class LoadActivitiesImpl implements LoadActivities {

	@Override
	public void actLoad(String inputPath) {
		System.out.println("load inputPath : "+inputPath);
		
		RedshiftLoadMain.main(new String[]{inputPath});
	}

}
