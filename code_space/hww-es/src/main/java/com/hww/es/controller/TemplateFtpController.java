package com.hww.es.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hww.es.pojo.es.EsNewsQueryParam;
import com.hww.es.service.ITemplateFtpService;
import com.hww.es.util.R;
/**
 * <p>按模版生成FTP文件</p>
 * 2018年11月26日上午8:45:21
 * @author lvjie
 */
@Controller
@RequestMapping("/templateFtp")
public class TemplateFtpController {
	@Resource
	private ITemplateFtpService templateFtpService;
	/**
	 * <p>按模版生成ftp文件</p>
	 * 2018年11月26日上午9:30:30
	 * @author lvjie
	 */
	@RequestMapping("/ftp")
	@ResponseBody
	public void templateFtp(){
		templateFtpService.templateFtp();
	}
	/**
	 * <p>根据模版table生成FTP</p>
	 * 2018年11月29日下午2:30:55
	 * @author lvjie
	 */
	@RequestMapping("/ftpTable")
	@ResponseBody
	public R templateFtpTable(EsNewsQueryParam param){
		
		String url = templateFtpService.templateFtpTable(param);
		
		return R.ok().put("data", url);
	}
	/**
	 * <p>后台自动生成表格</p>
	 * @return
	 * 2018年12月4日下午1:37:52
	 * @author lvjie
	 */
	@RequestMapping("/tftpTable")
	@ResponseBody
	public R templateTable(){
		try {
			templateFtpService.templateTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return R.ok().put("data", "ok");
	}
}
