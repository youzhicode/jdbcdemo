package com.ymcyun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ymcyun.domain.User;
import com.ymcyun.utils.AbsDbUtil;
import com.ymcyun.utils.DbUtilC3P0;
import com.ymcyun.utils.DbUtils;

public class UserDao {

    private AbsDbUtil db = new DbUtilC3P0();  // use c3p0
    // private AbsDbUtil db = DbUtils.getInstance(); // use traditional jdbc

    public User findUserById(int id) {
        User user = null;
        String sql = "select * from users where id = ?";
        Connection conn = db.getConnection();
        if (conn != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setId(id);
                    user.setAge(rs.getInt("age"));
                    user.setPassword(rs.getString("password"));
                    user.setUsername(rs.getString("username"));
                } 

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                db.close(pstmt, rs, conn);
            }
        }
        return user;
    }

    public List<User> findUserByUsername(String username) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement pstmt = null;
        ResultSet rs =  null;
        Connection conn = db.getConnection();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setAge(rs.getInt("age"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(rs, pstmt, conn);
        }
        return users;
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = db.getConnection();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (password.equals(rs.getString("password"))) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setAge(rs.getInt("age"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(rs, pstmt, conn);
        }
        return user;
    }

    public boolean singUpUser(User user) {
        String sql = "INSERT INTO users(username, password, age) values(?,?,?)";
        Connection conn = db.getConnection();
        PreparedStatement pstmt = null;
        boolean success = false;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getAge());
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                success = true;
                conn.commit();
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            db.close(pstmt, conn);
        }
        return success;
    }

}
