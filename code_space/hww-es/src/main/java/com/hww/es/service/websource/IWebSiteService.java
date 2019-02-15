package com.hww.es.service.websource;

import java.util.List;

import com.hww.es.pojo.websource.WebSiteSource;

/**
 * <p>自定义爬取站点</p>
 * 2018年12月13日上午8:19:31
 * @author lvjie
 */
public interface IWebSiteService {
	List<WebSiteSource> findPageList();
	
	int insertWebSite(WebSiteSource webSite);
	
	WebSiteSource findWebSiteById(Integer id);
	
	int updateWebSite(WebSiteSource webSite);
	
	int deleteWebSite(Integer id);
}
