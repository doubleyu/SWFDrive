package com.dby.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class PropEditor {
	static Properties prop = new Properties();// 属性集合对象 
	
	public static String getProp(String propName){
		
		
		try {
			// 属性文件输入流 
			FileInputStream fis = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath()+"prop.properties");
			prop.load(fis);// 将属性文件流装载到Properties对象中   
	        fis.close();// 关闭流   
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop.getProperty(propName);
	}
	
	public static void setProp(String propName, String value){
		prop.setProperty(propName, value);
		
		try {
			FileOutputStream fos = new FileOutputStream(Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath()+"prop.properties");
			 // 将Properties集合保存到流中   
	        prop.store(fos, "Copyright (c) Boxcode Studio");   
	        fos.close();// 关闭流   
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
       
	}
	
	public static void main(String args[]){
		System.out.println(getProp(PublicStr.JDBC_URL));
		setProp(PublicStr.JDBC_URL,"jdbc:redshift://redshift-cluster3.cmbuy13yulw4.us-west-2.redshift.amazonaws.com:5439/dev");
		System.out.println(getProp(PublicStr.JDBC_URL));
	}
}
