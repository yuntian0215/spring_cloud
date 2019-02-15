package com.hww.es.mapper;

import java.util.List;
import java.util.Map;

import com.hww.es.pojo.websource.WebType;



public interface WebTypeMapper {

	public List<String> selectSourceIdListBySourceTypeIdList(List<String> typeIdList);

	public void insert(WebType type);

	public void insertSourceIdType(Map<String, Object> map);

	public void delete(Long typeId);

	public void deleteSourceIdType(Long typeId);

	public List<WebType> selectWebTypeListByCompanyId(Long companyId);

}
