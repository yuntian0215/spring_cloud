package com.hww.es.mapper;

import java.util.List;
import java.util.Map;

import com.hww.es.pojo.es.EsNewsQueryParam;

public interface QueryParamMapper {

	public void insert(EsNewsQueryParam param);

	public void delete(Long id);

	public EsNewsQueryParam selectById(Long id);

	public List<EsNewsQueryParam> selectListByCompanyId(Long companyId);

	public void insertWebType(Map<String, Object> map);

	public void deleteWebTypeByParamId(Long paramId);

	public void update(EsNewsQueryParam param);

	public void deleteQueryWebTypeByWebTypeId(Long webTypeId);

}
