package com.hww.es.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hww.es.pojo.WebReports;

/**
 * <p>报告接口</p>
 * <p>Description</p>
 * 2018年10月31日下午3:49:54
 * @author lvjie
 */
public interface IReportsService {
	
	 List<WebReports> getListSearch(WebReports reports);
	 
	 WebReports findReportById(Integer id);
	 
	 int updateReportById(WebReports reports);
	 
	 int deleteReport(Integer id);
	
	 void uploads(HttpServletRequest request,HttpServletResponse response, Integer id) throws Exception;
	
	 int insertReport(WebReports report);



	

}
