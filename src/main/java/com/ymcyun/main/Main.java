package com.ymcyun.main;

import com.ymcyun.actions.AbsLoginAction;
import com.ymcyun.actions.LoginAction;

public class Main {
	
	private String username;
	private String password;


	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean login(AbsLoginAction action) {
		return action.login(this.username, this.password);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.setUsername("weiki");
		main.setPassword("123456");
		if (main.login(new LoginAction())) {
			System.out.println("login success");
		} else {
			System.out.println("login fail");
		}
	}
	
}
