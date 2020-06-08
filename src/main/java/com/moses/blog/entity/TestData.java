package com.moses.blog.entity;

public class TestData {
    String username;
    String password;

    public TestData() {
        
    }

    public TestData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

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
}
