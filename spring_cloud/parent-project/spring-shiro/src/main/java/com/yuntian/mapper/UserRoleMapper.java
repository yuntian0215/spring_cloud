package com.yuntian.mapper;

import com.yuntian.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}