package com.ymcyun.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ymcyun.domain.User;

public class UserDaoTest {
    

    @Test
    public void testFindUserById() {
        int id = 1;
        UserDao userDao = new UserDao();
        User user = userDao.findUserById(id);
        assertEquals(user.getId(), id);
    }

    @Test
    public void testFindUserByUsername() {
        UserDao userDao = new UserDao();
        List<User> users = userDao.findUserByUsername("weiki");
        assertEquals(users.size(), 1);
    }

    @Test
    public void testFindUserByUsernameAndPassword() {
        UserDao userDao = new UserDao();
        User user = userDao.findUserByUsernameAndPassword("weiki", "123456");
        assertEquals(user.getId(), 1);
    }

}
