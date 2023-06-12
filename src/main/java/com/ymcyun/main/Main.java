package com.ymcyun.main;

import com.ymcyun.actions.LoginAction;
import com.ymcyun.forms.LoginForm;

public class Main {
	
	

	public static void main(String[] args) {
		LoginForm form = new LoginForm(new LoginAction());
		form.init();
		form.display();
		form.validate();
	}
	
}
