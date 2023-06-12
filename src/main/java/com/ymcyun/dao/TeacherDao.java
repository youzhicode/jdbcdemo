package com.ymcyun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.ymcyun.domain.Teacher;
import com.ymcyun.utils.DbUtilC3P0;
import com.ymcyun.utils.IDataSource;

public class TeacherDao {

    private IDataSource dataSource = DbUtilC3P0.getInstance();

    public Teacher findTeacherById(int id) {
        JdbcTemplate jt = new JdbcTemplate(dataSource.getDataSource());
        String sql = "SELECT * FROM teachers WHERE id = ?";
        try {
            Teacher teacher = jt.queryForObject(sql, new BeanPropertyRowMapper<>(Teacher.class), 1);
            return teacher;
        } catch(DataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createTeacher(Teacher teacher) {

        JdbcTemplate jt = new JdbcTemplate(dataSource.getDataSource());
        jt.execute(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt =  con.prepareStatement("INSERT INTO teachers(name, password) values(?, ?)");
                pstmt.setString(1, teacher.getName());
                pstmt.setString(2, teacher.getPassword());
                return pstmt;
            }
            
        }, new PreparedStatementCallback<Teacher>() {

            @Override
            public Teacher doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.execute();
                return teacher;
            }
            
        });

        return false;
    }
    
}
