package com.ymcyun.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbsDbUtil {
    

    public abstract Connection getConnection();

    public abstract Statement getStatement();

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
