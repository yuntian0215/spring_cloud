package com.hww.es.mapper;

import java.util.List;

import com.datastax.driver.mapping.annotations.Param;
import com.hww.es.pojo.ucenter.RoleAndResource;
import com.hww.es.pojo.ucenter.UcenterResources;

public interface UcenterResourcesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UcenterResources record);

    int insertSelective(UcenterResources record);

    UcenterResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UcenterResources record);

    int updateByPrimaryKey(UcenterResources record);
    
    List<UcenterResources> findListByPraentId(@Param("parentId") int parentId);
    
    List<UcenterResources> findListByPraentId1(RoleAndResource rr);
    
    List<UcenterResources> findSelect();
    
    int deleteByParentId(@Param("parentId") int parentId);
    
    List<UcenterResources> findListResourcesByRoleId(@Param("roleId") int roleId);
}