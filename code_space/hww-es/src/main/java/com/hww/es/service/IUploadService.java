package com.hww.es.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:  IUploadService   
 * @Description:文件导出 
 * @author: lvjie
 * @date:   2018年10月24日 上午9:53:00  
 * @Copyright: 2018
 */
public interface IUploadService {
	public String exportExcel(HttpServletResponse response,String ids) throws Exception;
	public void exportWord(HttpServletResponse response,String id) throws Exception;
	public String exportCsv(HttpServletResponse response,String ids) throws Exception;
}
