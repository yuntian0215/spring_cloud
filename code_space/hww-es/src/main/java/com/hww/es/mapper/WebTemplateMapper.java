package com.hww.es.mapper;

import com.hww.es.pojo.WebTemplate;

public interface WebTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebTemplate record);

    int insertSelective(WebTemplate record);

    WebTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebTemplate record);

    int updateByPrimaryKey(WebTemplate record);
}