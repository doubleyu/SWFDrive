package com.dby.redshift.database;

import java.io.IOException;

import com.dby.emr.s3.MyS3;
import com.dby.redshift.cluster.RedshiftCluster;
import com.swf.common.ConfigHelper;

/**
 * Created by Admin on 2016/1/11.
 */
public class RedshiftUnloadMain {
    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("input a date please");
            System.exit(2);
        }
        
        ConfigHelper configHelper = null;
		try {
			configHelper = ConfigHelper.createConfig();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        RedshiftCluster rc = new RedshiftCluster(configHelper);
        rc.createCluster();

        DateAndEvent dae = new DateAndEvent(configHelper);
        dae.initDateAndEvent();

        String inputDate = "2008-01-05";
        String date = args[0];
        String s3Folder = date.replace("-", "");
        String s3Path = "s3://redshift-lyy-east/unload/" + s3Folder + "/";

        MyS3 mys3 = new MyS3(configHelper.createRedshiftCredentials());
        mys3.deleteFolder(s3Path);

        UnloadEvent ue = new UnloadEvent(configHelper);
        ue.unloadEvent(args[0], s3Path);
    }


}
