package com.hww.es.mapper;

import java.util.List;

import com.hww.es.pojo.websource.WebSiteSource;

public interface WebSiteSourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebSiteSource record);

    int insertSelective(WebSiteSource record);

    WebSiteSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebSiteSource record);

    int updateByPrimaryKeyWithBLOBs(WebSiteSource record);

    int updateByPrimaryKey(WebSiteSource record);
    
    List<WebSiteSource> selectPageList();
}