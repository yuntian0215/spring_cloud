package com.hww.es.service.es;

import java.util.List;

import com.hww.es.pojo.websource.WebSource;
import com.hww.es.util.PageBean;

public interface EsWebSourceService {

	public void addWebSource(WebSource websource);

	public void addWebSourceList(List<WebSource> list);
	
	public PageBean<WebSource> searchWebSource(WebSource websource,Integer pageNum, Integer pageSize);
	
	public List<WebSource> searchWebSourceNoPage(WebSource websource);
}
