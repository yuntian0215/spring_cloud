package com.hww.es.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.es.pojo.websource.WebSource;
import com.hww.es.service.es.EsWebSourceService;
import com.hww.es.service.websource.WebSourceService;
import com.hww.es.util.PageBean;
import com.hww.es.util.R;

/**
 * 
 * @author fuxuan
 *
 */
@RestController
@RequestMapping("/webSource")
public class WebSourceController {
	
	@Autowired
	WebSourceService webSourceService;
	@Autowired
	EsWebSourceService esWebSourceService;
	
	/**
	 * 查询站点详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public R detail(Long id) throws Exception {
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			WebSource web = webSourceService.getById(id);
			data.put("webSource", web);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 根据大洲，国家，语言等基本信息查询带分页站点列表
	 * @param webSource
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public R getList(WebSource websource, Integer pageNum, Integer pageSize) throws Exception {
		try {
			PageBean<WebSource> page = esWebSourceService.searchWebSource(websource, pageNum, pageSize);
			return R.ok().put("data", page);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	/**
	 * 根据大洲，国家，语言等基本信息查询带分页站点列表
	 * @param webSource
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getListNoPage")
	@ResponseBody
	public R getListNoPage(WebSource websource){
		try {
			List<WebSource> list = esWebSourceService.searchWebSourceNoPage(websource);
			System.out.println("数量"+list.size());
			return R.ok().put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 查询站点分类下的站点列表
	 * @param typeId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getListByType")
	@ResponseBody
	public R getListByType(Long typeId, Integer pageNum, Integer pageSize) throws Exception {
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<WebSource> list = webSourceService.getListByTypeId(typeId);
			PageInfo<WebSource> page = new PageInfo<WebSource>(list);
			return R.ok().put("data", page);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 查询站点分类下的站点列表
	 * @param typeId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getListByTypeNoPage")
	@ResponseBody
	public R getListByTypeNoPage(Long typeId) throws Exception {
		try {
			List<WebSource> list = webSourceService.getListByTypeId(typeId);
			return R.ok().put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	
	@RequestMapping("/test")
	@ResponseBody
	public R test(Integer pageNum, Integer pageSize) throws Exception {
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			PageHelper.startPage(pageNum,pageSize);
			List<WebSource> list = webSourceService.getList();
			PageInfo<WebSource> page = new PageInfo<WebSource>(list);
			
			data.put("page", page);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
