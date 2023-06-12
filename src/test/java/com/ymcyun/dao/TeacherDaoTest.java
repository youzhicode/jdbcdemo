package com.ymcyun.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.ymcyun.domain.Teacher;

public class TeacherDaoTest {
    
    private TeacherDao dao = new TeacherDao();

    @Test
    public void testFindTeacherById() {
        Teacher teacher = dao.findTeacherById(1);
        assertNotNull(teacher);
    }


    // @Test
    // public void testCreateTeacher() {
    //     Teacher teacher = new Teacher();
    //     teacher.setName("weiki");
    //     teacher.setPassword("123456");
    //     dao.createTeacher(teacher);
    // }
}
