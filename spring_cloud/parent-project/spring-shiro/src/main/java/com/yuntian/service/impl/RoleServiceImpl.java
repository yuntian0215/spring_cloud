package com.yuntian.service.impl;

import com.yuntian.Role;
import com.yuntian.mapper.RoleMapper;
import com.yuntian.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>内容</p>
 * 2019/3/8/0008 15:49
 *
 * @author lvjie
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper roleMapper;
    public List<Role> findRoleByUserId(Integer userId){
        List<Role> list = roleMapper.findRoleByUserId(userId);
        return list;
    }
}
