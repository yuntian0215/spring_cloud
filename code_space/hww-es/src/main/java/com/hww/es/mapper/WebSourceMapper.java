package com.hww.es.mapper;

import java.util.List;

import com.hww.es.pojo.websource.WebSource;

public interface WebSourceMapper {

	public WebSource selectById(Long id);

	public List<WebSource> selectList(WebSource websource);

	public List<WebSource> getList();

	public List<String> selectSourceIdListByTypeId(Long typeId);

	public List<String> selectSourceIdListByTypeIdList(List<Long> webTypeIdList);

	public List<WebSource> selectListByTypeId(Long typeId);

	public String selectFullNameById(Long id);

	public void insert(WebSource webSource);
}
