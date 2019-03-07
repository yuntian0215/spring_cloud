package com.yuntian.parentproject.pojo;

import java.util.Set;

/**
 * <p>内容</p>
 * 2019/2/18/0018 9:05
 *
 * @author lvjie
 */
public class Module {
    private Integer mid;
    private String mname;
    private Set<Role> roles;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
