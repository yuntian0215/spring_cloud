package com.hww.es.mapper;

import java.util.List;

import com.hww.es.pojo.WebReports;

public interface WebReportsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WebReports record);

    int insertSelective(WebReports record);

    WebReports selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebReports record);

    int updateByPrimaryKey(WebReports record);
    
    List<WebReports> getListSearch(WebReports record);
}