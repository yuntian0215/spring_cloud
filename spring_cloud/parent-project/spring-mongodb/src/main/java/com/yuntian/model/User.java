package com.yuntian.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * <p>内容</p>
 * 2019/4/16/0016 16:42
 *
 * @author lvjie
 *
 *@Document（collection = "xxx"）实体类将会映射到数据库中名为xxx的collection中
 *	此注解可不写，不写的话会在数据库中创建以类名小写为名的collection
*/
@Document(collection="t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 加上@Id 标注在属性上，映射到collection的_id属性上mongodb自己维护此字段
     * 不加的话就当着一个字段去创建了
     */
    @Id
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
