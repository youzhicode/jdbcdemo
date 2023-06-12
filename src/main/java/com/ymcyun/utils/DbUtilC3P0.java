package com.ymcyun.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbUtilC3P0  extends AbsDbUtil{

    private DataSource c3p0;

    private DbUtilC3P0() {
        c3p0 = new ComboPooledDataSource();
    }


    private static class SingleInstance{
        private static DbUtilC3P0 INSTANCE = new DbUtilC3P0();
    }

    public static DbUtilC3P0 getInstance() {
        return SingleInstance.INSTANCE;
    }


    @Override
    public Connection getConnection() {
        try {
            return c3p0.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Statement getStatement() {
        Connection conn = this.getConnection();
        if (conn != null) {
            try {
                return conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    


}
