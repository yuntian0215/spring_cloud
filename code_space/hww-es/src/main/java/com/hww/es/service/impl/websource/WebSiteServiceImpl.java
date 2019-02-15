package com.hww.es.service.impl.websource;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hww.es.mapper.WebSiteSourceMapper;
import com.hww.es.pojo.websource.WebSiteSource;
import com.hww.es.service.websource.IWebSiteService;
/**
 * <p>自定义爬取站点</p>
 * 2018年12月13日上午8:20:23
 * @author lvjie
 */
@Service
public class WebSiteServiceImpl implements IWebSiteService{
	@Resource
	private WebSiteSourceMapper wbSiteSourceMapper;

	@Override
	public List<WebSiteSource> findPageList() {
		List<WebSiteSource> list = wbSiteSourceMapper.selectPageList();
		return list;
	}

	@Override
	public int insertWebSite(WebSiteSource webSite) {
		webSite.setSiteStatus(0);
		webSite.setCreateDate(new Date());
		webSite.setUpdateDate(new Date());
		int status = wbSiteSourceMapper.insert(webSite);
		return status;
	}

	@Override
	public WebSiteSource findWebSiteById(Integer id) {
		WebSiteSource website = wbSiteSourceMapper.selectByPrimaryKey(id);
		return website;
	}

	@Override
	public int updateWebSite(WebSiteSource webSite) {
		webSite.setUpdateDate(new Date());
		int status = wbSiteSourceMapper.updateByPrimaryKeySelective(webSite);
		return status;
	}

	@Override
	public int deleteWebSite(Integer id) {
		int status = wbSiteSourceMapper.deleteByPrimaryKey(id);
		return status;
	}
}
