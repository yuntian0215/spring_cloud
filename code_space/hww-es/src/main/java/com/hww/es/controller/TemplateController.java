package com.hww.es.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hww.es.pojo.es.EsNewsQueryParam;
import com.hww.es.service.ITemplateService;
import com.hww.es.util.R;

/**
 * <p>根据模版生成文档</p>
 * <p>Description</p>
 * 2018年11月12日上午10:24:51
 * @author lvjie
 */
@Controller
@RequestMapping("/template")
public class TemplateController {
	@Autowired
	private ITemplateService templateService;
	
	static String MODELPATH = "/uploadFiles/…";
	/**
	 * <p>按固定模版导出固定word</p>
	 * <p>Description</p>
	 * @throws Exception
	 * 2018年11月12日上午10:19:44
	 * @author lvjie
	 */
	@RequestMapping("/word")
	@ResponseBody
	public void templateWord(HttpServletResponse response) throws Exception{
		// 要填充的数据, 注意map的key要和word中${xxx}的xxx一致  
	      Map<String,String> dataMap = new HashMap<String,String>();  
	      dataMap.put("userName", "张三");  
	      dataMap.put("age", "18");  
	      templateService.generateWord(response,dataMap);
	}
	/**
	 * <p>根据模版按表格形式生成文档</p>
	 * @throws Exception
	 * 2018年11月13日下午1:37:05
	 * @author lvjie
	 */
	@RequestMapping("/excel")
	@ResponseBody
	public void templateExcel()throws Exception{
		templateService.templateExcel();
		
	}
	/**
	 * <p>摘要版</p>
	 * @param response
	 * @param param 搜索条件
	 * 2018年11月14日上午9:27:32
	 * @author lvjie
	 */
	@RequestMapping("/abstract")
	@ResponseBody
	public R tempAbstract(HttpServletResponse response,EsNewsQueryParam param)throws Exception{
		String url = templateService.tempAbstract(response,param);
		return R.ok().put("data", url);
	}
	/**
	 * <p>测试导出文档含图片</p>
	 * @param request
	 * @param response
	 * @throws Exception
	 * 2018年11月23日上午11:05:14
	 * @author lvjie
	 */
	@RequestMapping("/downloadDoc")
	@ResponseBody
	public void downloadDoc(String base64,HttpServletRequest request, HttpServletResponse response) throws Exception {
		templateService.downloadDoc(base64,response);
	}
}
