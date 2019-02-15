package com.hww.es.mapper;

import java.util.List;

import com.hww.es.pojo.ucenter.UcenterRole;

public interface UcenterRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UcenterRole record);

    int insertSelective(UcenterRole record);

    UcenterRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UcenterRole record);

    int updateByPrimaryKey(UcenterRole record);
    
    List<UcenterRole> findList();
}