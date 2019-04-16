package com.yuntian.model;

import java.io.Serializable;

/**
 * <p>内容</p>
 * 2019/4/16/0016 16:42
 *
 * @author lvjie
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String userName;
    private String age;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public User() {
        super();
    }
    public User(Integer userId, String userName, String age) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", age="
                + age + "]";
    }
}
