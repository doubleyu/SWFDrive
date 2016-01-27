package com.dby.redshift.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.swf.common.ConfigHelper;

/**
 *
 * @author Hongten</br>
 * @date 2012-7-16
 *
 */
public class JDBCExecuteUpdate {
	
	private String passwrod;
    private String userName;
    private String url;
	public JDBCExecuteUpdate(ConfigHelper configHelper){
		this.passwrod = configHelper.getRedshiftDbPasswd();
		this.userName = configHelper.getRedshiftDbUser();
		this.url = configHelper.getRedshiftJdbcUrl();
	}
	
    public void executeUpdate(String sql) {
    	
    	
    	
//    	try {
//			dk = new DescribeKeys("abc");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
    	
        String driver = "com.amazon.redshift.jdbc41.Driver";
//        String dbName = "dev";
        
//        System.out.println(url+"   "+userName+"   operating...");
        
 
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, userName,
                    passwrod);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
 
 
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
 
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}