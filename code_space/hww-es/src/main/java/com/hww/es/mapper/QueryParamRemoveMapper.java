package com.hww.es.mapper;

import java.util.List;

import com.hww.es.pojo.es.EsNewsQueryParamRemove;

public interface QueryParamRemoveMapper {

	public void deleteByQueryParamId(Long queryParamId);

	public List<Long> selectListByQueryParamId(Long paramId);

	public void insert(EsNewsQueryParamRemove remove);
}
