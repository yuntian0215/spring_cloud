package com.hww.es.service.es;

import java.util.List;

import com.hww.es.pojo.es.EsNewsQueryParamRemove;

public interface QueryParamRemoveService {

	public List<Long> getListByQueryParamId(Long paramId);
	
	public void add(EsNewsQueryParamRemove remove);
}
