package com.hww.es.service.websource;

import java.util.List;
import com.hww.es.pojo.websource.WebType;


public interface WebTypeService {

	public List<String> getSourceIdListBySourceTypeIdList(List<String> typeIdList);

	public void addWebType(WebType type);

	public void addWebSourceIdType(Long typeId, Long sourceId);

	public void deleteWebType(Long typeId);

	public List<WebType> getWebTypeList(Long companyId); 
}

