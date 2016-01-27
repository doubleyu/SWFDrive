package com.dby.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.dby.util.SafeProperties;

public class TestSafeProp {
	public static void main(String[] args) throws IOException {
		FileInputStream input = new FileInputStream("E://IOPUT//OUTPUT//safeProp.properties");
	       SafeProperties safeProp = new SafeProperties();
	       safeProp.load(input);
	       input.close();
	       safeProp.addComment("New Comment");
	       safeProp.put("Redshift.Service.Url=", "one");
	       FileOutputStream output = new FileOutputStream("E://IOPUT//OUTPUT//safeProp.properties");
	       safeProp.store(output, null);
	       output.close();
	}
}
