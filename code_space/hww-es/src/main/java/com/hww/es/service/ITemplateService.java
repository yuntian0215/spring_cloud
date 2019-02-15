package com.hww.es.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hww.es.pojo.es.EsNewsQueryParam;

/**
 * <p>根据模版生成文档</p>
 * <p>Description</p>
 * 2018年11月12日上午10:35:25
 * @author lvjie
 */
public interface ITemplateService {
	
	public void generateWord(HttpServletResponse response,Map<String,String> dataMap)throws Exception;
	
	public void templateExcel()throws Exception;
	
	public String tempAbstract(HttpServletResponse response,EsNewsQueryParam param)throws Exception;
	
	public void downloadDoc(String base64,HttpServletResponse response);
}
