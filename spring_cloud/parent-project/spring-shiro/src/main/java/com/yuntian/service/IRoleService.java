package com.yuntian.service;

import com.yuntian.Role;

import java.util.List;

/**
 * Package: com.yuntian.service
 * <p>
 * Description： TODO
 * <p>
 * Author: lvjie
 * <p>
 * Date: Created in 2019/3/8/0008 15:49
 * <p>
 * Version: 0.0.1
 */
public interface IRoleService {
    public List<Role> findRoleByUserId(Integer userId);
}
