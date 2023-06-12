package com.ymcyun.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbUtilC3P0  extends AbsDbUtil{

    @Override
    public Connection getConnection() {
        DataSource c3p0 = new ComboPooledDataSource();
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
