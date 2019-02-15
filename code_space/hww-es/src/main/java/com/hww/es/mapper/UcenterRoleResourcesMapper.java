package com.hww.es.mapper;

import java.util.List;

import com.datastax.driver.mapping.annotations.Param;
import com.hww.es.pojo.ucenter.UcenterRoleResources;

public interface UcenterRoleResourcesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UcenterRoleResources record);

    int insertSelective(UcenterRoleResources record);

    UcenterRoleResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UcenterRoleResources record);

    int updateByPrimaryKey(UcenterRoleResources record);
    
    List<UcenterRoleResources> fingResourcesByRoleId(@Param("roleId") Integer roleId);
    
    int deleteByRoleId(@Param("roleId") Integer roleId);
    
    int deleteByResourcesId(@Param("resourcesId") Integer resourcesId);
}