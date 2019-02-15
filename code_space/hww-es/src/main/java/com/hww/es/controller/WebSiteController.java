package com.hww.es.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.es.pojo.websource.WebSiteSource;
import com.hww.es.service.websource.IWebSiteService;
import com.hww.es.util.R;
/**
 * <p>自定义爬取站点</p>
 * 2018年12月13日上午8:17:54
 * @author lvjie
 */
@Controller
@RequestMapping("/webSite")
public class WebSiteController {
	@Resource
	private IWebSiteService webSiteService;
	/**
	 * <p>分页查询站点</p>
	 * @param pageNum 第几页
	 * @param pageSize 每页几条
	 * @return
	 * 2018年12月13日上午10:16:59
	 * @author lvjie
	 */
	@RequestMapping("/findPageList")
	@ResponseBody
	private R findPageList(Integer pageNum, Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<WebSiteSource> list = webSiteService.findPageList();
		PageInfo<WebSiteSource> info = new PageInfo<WebSiteSource>(list);
		return R.ok().put("data", info);
	}
	/**
	 * <p>添加站点</p>
	 * @param webSite
	 * @return
	 * 2018年12月13日上午10:27:54
	 * @author lvjie
	 */
	@RequestMapping("/insertWebSite")
	@ResponseBody
	public R insertWebSite(WebSiteSource webSite){
		int status = webSiteService.insertWebSite(webSite);
		return R.ok().put("data", status);
	}
	/**
	 * <p>根据id查询</p>
	 * @param id
	 * @return
	 * 2018年12月13日上午10:31:47
	 * @author lvjie
	 */
	@RequestMapping("/findWebSiteById")
	@ResponseBody
	public R findWebSiteById(Integer id){
		WebSiteSource webSite = webSiteService.findWebSiteById(id);
		return R.ok().put("data", webSite);
	}
	/**
	 * <p>编辑</p>
	 * @param webSite
	 * @return
	 * 2018年12月13日上午10:34:25
	 * @author lvjie
	 */
	@RequestMapping("/updateWebSite")
	@ResponseBody
	public R updateWebSite(WebSiteSource webSite){
		int status = webSiteService.updateWebSite(webSite);
		return R.ok().put("data", status);
	}
	/**
	 * <p>删除</p>
	 * @param id
	 * @return
	 * 2018年12月13日上午10:38:01
	 * @author lvjie
	 */
	@RequestMapping("/deleteWebSite")
	@ResponseBody
	public R deleteWebSite(Integer id){
		int status = webSiteService.deleteWebSite(id);
		return R.ok().put("data", status);
	}
}
