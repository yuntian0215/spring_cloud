package com.hww.es.service.websource;

import java.util.List;
import com.hww.es.pojo.websource.WebSource;

public interface WebSourceService {

	public WebSource getById(Long id);

	public List<WebSource> getList(WebSource websource);

	public List<WebSource> getList();
	
	public List<String> getSourceIdListByTypeIdList(List<Long> webTypeIdList);

	public List<WebSource> getListByTypeId(Long typeId);
	
	public String getFullNameById(Long id);

	public WebSource addWebSource(WebSource webSource);

}
