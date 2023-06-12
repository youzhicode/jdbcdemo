package com.ymcyun.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.InputStream;

public class DbUtils {

	private String user;
	private String password;
	private String host;
	private String port;
	private String database;


	private DbUtils() {}

	private static class SingleInstance {
		private static final DbUtils INSTANCE = new DbUtils();

		static {
			
			Properties properties = new Properties();
			InputStream in = DbUtils.class.getClassLoader().getResourceAsStream("db.properties");
			try {
				properties.load(in);
				INSTANCE.user = properties.getProperty("user");
				INSTANCE.password = properties.getProperty("password");
				INSTANCE.host = properties.getProperty("host");
				INSTANCE.port = properties.getProperty("port");
				INSTANCE.database = properties.getProperty("database");
				Class.forName(properties.getProperty("driver"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static DbUtils getInstance() {
		return SingleInstance.INSTANCE;
	}

	public Connection getConnection(){
		Connection conn = null;		
		try {
			conn =DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.database
			+"?useUnicode=true&characterEncoding=UTF-8", 
			this.user, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Statement getStatement(){
		Connection conn = this.getConnection();
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void close(Object... objs) {
		for (Object obj : objs) {
			if (obj instanceof Connection) {
				try {
					((Connection)obj).close();
					continue;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (obj instanceof Statement) {
				try {
					((Statement)obj).close();
					continue;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (obj instanceof PreparedStatement) {
				try {
					((PreparedStatement)obj).close();
					continue;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (obj instanceof ResultSet) {
				try {
					((ResultSet)obj).close();
					continue;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
