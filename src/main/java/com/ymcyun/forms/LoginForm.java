package com.ymcyun.forms;

import com.ymcyun.actions.AbsLoginAction;

public class LoginForm {

    private AbsLoginAction loginAction;

    public LoginForm() {}

    public LoginForm(AbsLoginAction action) {
        this.loginAction = action;
    }

    public void init() {
        System.out.println("Init login form...");
    }

    public void display() {
        System.out.println("Display login form...");
    }

    public void validate() {
        // Receive username and password from username input field and 
        // password input field
        String username = "weiki";
        String password = "123456";
        if (loginAction.login(username, password)) {
            System.out.println("Login success");
        } else {
            System.out.println("Login fail");
        }
    }
    
}
