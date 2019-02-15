package com.hww.es.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hww.es.pojo.ucenter.UCenterCompany;

public interface UcenterCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UCenterCompany record);

    int insertSelective(UCenterCompany record);

    UCenterCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UCenterCompany record);

    int updateByPrimaryKey(UCenterCompany record);
    
    List<UCenterCompany> selectByKeyword(@Param("keyword")String keyword);
}