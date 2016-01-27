package com.dby.redshift.emr;

import java.io.IOException;

import com.swf.common.ConfigHelper;

/**
 * Created by Admin on 2016/1/11.
 */
public class RedshiftLoadMain {

    public static void main(String[] args){
        if(args.length<1){
            System.err.println("input a result Path please");
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

        String resultPath = "s3://hadoop-lyy/output/output1";
        LoadEMResult remr = new LoadEMResult(configHelper);
        remr.loadResult(args[0]);
    }
}
