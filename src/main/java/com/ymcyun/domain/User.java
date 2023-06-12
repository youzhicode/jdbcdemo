package com.ymcyun.domain;

public class User {
    
    private String username;
    private String password;
    private int id;
    private int age;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", id=" + id + ", age=" + age + "]";
    }

    
    


}
