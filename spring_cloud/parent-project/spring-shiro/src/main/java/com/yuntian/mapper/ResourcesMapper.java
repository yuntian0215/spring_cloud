package com.yuntian.mapper;

import com.yuntian.Resources;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourcesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resources record);

    int insertSelective(Resources record);

    Resources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);

    List<Resources> selectResourcesByUserId(@Param("userId") Integer userId);
}