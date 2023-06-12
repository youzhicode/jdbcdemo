package com.ymcyun.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DbutilDruid extends AbsDbUtil implements IDataSource {

    private DataSource ds;

    private DbutilDruid() {
        Properties ps = new Properties();
        InputStream in = DbutilDruid.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            ps.load(in);
            ds = DruidDataSourceFactory.createDataSource(ps);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class SingleInstance{
        private static DbutilDruid INSTANCE = new DbutilDruid();
    }

    public static DbutilDruid getInstance() {
        return SingleInstance.INSTANCE;
    }

    @Override
    public Connection getConnection() {
        try {
            return ds.getConnection();
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

    @Override
    public DataSource getDataSource() {
        return ds;
    }
    
}
