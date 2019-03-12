package com.yuntian;

import java.io.Serializable;

/**
 * <p>RabbitMQ实体类传输必须实现序列化接口</p>
 * 2019/3/12/0012 16:26
 *
 * @author lvjie
 */
public class RabbitUser implements Serializable {
    private String name;
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
