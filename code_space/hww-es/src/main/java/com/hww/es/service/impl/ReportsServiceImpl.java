package com.hww.es.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hww.es.mapper.WebReportsMapper;
import com.hww.es.pojo.WebReports;
import com.hww.es.service.IReportsService;
/**
 * <p>报告</p>
 * <p>Description</p>
 * 2018年11月1日上午10:55:12
 * @author lvjie
 */
@Service
public class ReportsServiceImpl implements IReportsService{

	@Autowired
	WebReportsMapper reportsMapper;
	/**
	 * 搜索
	 */

	@Override
	public List<WebReports> getListSearch(WebReports reports) {
		List<WebReports> reList = reportsMapper.getListSearch(reports);
		
		return reList;
	}
	@Override
	public WebReports findReportById(Integer id) {
		WebReports web = reportsMapper.selectByPrimaryKey(id);
		return web;
	}
	@Override
	public int updateReportById(WebReports reports) {
		int status = reportsMapper.updateByPrimaryKeySelective(reports);
		return status;
	}
	
	@Override
	public int deleteReport(Integer id) {
		WebReports report = reportsMapper.selectByPrimaryKey(id);
		int status = reportsMapper.deleteByPrimaryKey(id);
		
		File file = new File(report.getDocUrl());
		file.delete();
		return status;
	}

	@Override
	public void uploads(HttpServletRequest request,HttpServletResponse response, Integer id) throws Exception {
		
		OutputStream os=response.getOutputStream();;
		 
		WebReports report = reportsMapper.selectByPrimaryKey(id);//获取下载路径
		
		WebReports  rep = new WebReports();
		rep.setId(id);
		rep.setNum(report.getNum()+1);//下载数加1
		rep.setIsRead(1);//阅读
		rep.setState("1");
		reportsMapper.updateByPrimaryKeySelective(rep);
       
        String path = report.getDocUrl();
        
        File file=new File(path);
       
        String fileName=file.getName();
        String ext=fileName.substring(fileName.lastIndexOf(".")+1);
        String agent=(String)request.getHeader("USER-AGENT"); //判断浏览器类型
        try {  
        	if(agent!=null && agent.indexOf("Fireforx")!=-1) {
        		fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");   //UTF-8编码，防止输出文件名乱码
        	}
        	else {
        		fileName=URLEncoder.encode(fileName,"UTF-8");
        	}
        } catch (Exception e) {  
        	e.printStackTrace();  
        }
        
        BufferedInputStream bis=null;
        
        response.reset();
    	response.setCharacterEncoding("utf-8"); 
    	if(ext=="docx") {
    		response.setContentType("application/msword"); // word格式
    	}else if(ext=="pdf") {
    		response.setContentType("application"); // word格式
    	}  
        response.setHeader("Content-Disposition", "attachment; filename=" +report.getTitle()+"."+report.getDocType() );
        
        try {
	        bis=new BufferedInputStream(new FileInputStream(file));
		byte[] b=new byte[bis.available()+1000];
		int i=0;
        	while((i=bis.read(b))!=-1) {
        		os.write(b, 0, i);
        	}
            os.flush();
        } catch (Exception e) {  
           
        }
        os.close();
	}
	@Override
	public int insertReport(WebReports report) {
		
		return reportsMapper.insert(report);
	}
}
