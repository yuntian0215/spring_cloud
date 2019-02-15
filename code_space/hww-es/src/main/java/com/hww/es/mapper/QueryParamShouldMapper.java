package com.hww.es.mapper;

import com.hww.es.pojo.es.EsNewsQueryParamShould;

public interface QueryParamShouldMapper {

	public void insert(EsNewsQueryParamShould shoulds);

	public void deleteByQueryParamId(Long id);

}
