package com.hww.es.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hww.es.pojo.ucenter.UcenterResources;
import com.hww.es.service.ucenter.IUcenterResourcesService;
import com.hww.es.util.R;
/**
 * <p>资源信息</p>
 * <p>Description</p>
 * 2018年10月12日
 * @author lvjie
 */
@Controller
@RequestMapping("/resources")
public class ResourcesController {
	@Resource
	private IUcenterResourcesService resourcesService;
	/**
	 * 查询所有资源
	 * @return
	 * 2018年10月11日下午2:30:46
	 */
	@RequestMapping("/findList")
	@ResponseBody
	public R findList(){
		List<UcenterResources> list = resourcesService.findList();
		return R.ok().put("data", list);
	}
	/**
	 * 根据Id获取资源信息
	 * @param id
	 * @return
	 * 2018年10月11日下午2:36:59
	 */
	@RequestMapping("/findResourcesById")
	@ResponseBody
	public R findResourcesById(Integer id){
		UcenterResources resources = resourcesService.findResourcesById(id);
		return R.ok().put("data", resources);
	}
	/**
	 * 根据id修改资源信息
	 * @param resources
	 * @return
	 * 2018年10月11日下午2:40:14
	 */
	@RequestMapping("/updateResourcesById")
	@ResponseBody
	public R updateResourcesById(UcenterResources resources){
		int status = resourcesService.updateResourcesById(resources);
		return R.ok().put("data", status);
	}
	/**
	 * 添加资源
	 * @param resources
	 * @return
	 * 2018年10月11日下午2:43:02
	 */
	@RequestMapping("/insertResources")
	@ResponseBody
	public R insertResources(UcenterResources resources){
		int status = resourcesService.insertResources(resources);
		return R.ok().put("data", status);
	}
	/**
	 * <p>删除资源</p>
	 * <p>Description</p>
	 * @param id
	 * @return
	 * 2018年10月17日下午3:15:28
	 * @author lvjie
	 */
	@RequestMapping("/deleteResources")
	@ResponseBody
	public R deleteResources(Integer id){
		int status = resourcesService.deleteResources(id);
		return R.ok().put("data", status);
	}
	/**
	 * <p>查询父级选项</p>
	 * <p>Description</p>
	 * @return
	 * 2018年10月19日上午11:04:30
	 * @author lvjie
	 */
	@RequestMapping("/toSelect")
	@ResponseBody
	public R toSelect(){
		List<UcenterResources> list = resourcesService.findSelect();
		return R.ok().put("data", list);
	}
}
