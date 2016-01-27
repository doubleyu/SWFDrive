package com.dby.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class PropEditor {
	static Properties prop = new Properties();// ���Լ��϶��� 
	
	public static String getProp(String propName){
		
		
		try {
			// �����ļ������� 
			FileInputStream fis = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath()+"prop.properties");
			prop.load(fis);// �������ļ���װ�ص�Properties������   
	        fis.close();// �ر���   
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
			 // ��Properties���ϱ��浽����   
	        prop.store(fos, "Copyright (c) Boxcode Studio");   
	        fos.close();// �ر���   
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
