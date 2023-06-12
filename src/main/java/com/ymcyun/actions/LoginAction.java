package com.ymcyun.actions;

import com.ymcyun.dao.UserDao;
import com.ymcyun.domain.User;

public class LoginAction extends AbsLoginAction{
	
	

	public boolean login(String username, String password) {
		UserDao userDao = new UserDao();
		User user = userDao.findUserByUsernameAndPassword(username, password);
		return user != null;
	}
	
}
