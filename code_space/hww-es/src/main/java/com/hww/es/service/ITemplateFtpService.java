package com.hww.es.service;

import com.hww.es.pojo.es.EsNewsQueryParam;

/**
 * <p>按模版生成FTP文件</p>
 * 2018年11月26日上午9:28:15
 * @author lvjie
 */
public interface ITemplateFtpService {
	public void templateFtp();
	
	public String templateFtpTable(EsNewsQueryParam param);
	
	public void templateTable()throws Exception;
}
