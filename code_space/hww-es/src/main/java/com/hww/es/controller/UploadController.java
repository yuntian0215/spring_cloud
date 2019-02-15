package com.hww.es.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hww.es.service.IUploadService;
import com.hww.es.util.R;
/**
 * @ClassName:  UploadController   
 * @Description:文件导出   
 * @author: lvjie
 * @date:   2018年10月24日 上午9:39:34  
 * @Copyright: 2018
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
	@Resource
	private IUploadService uploadService;
	/**
	 * 导出excel
	 * @return
	 * 2018年10月24日上午9:54:53
	 */
	@RequestMapping("/excel")
	@ResponseBody
	public R exportExcel(HttpServletResponse response,String ids){
		String url = null;
		try {
			url = uploadService.exportExcel(response,ids);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return R.ok().put("data", url);
	}
	/**
	 * 导出word
	 * @param response
	 * @param id
	 * 2018年10月24日上午9:05:58
	 */
	@RequestMapping("/word")
	public void exportWord(HttpServletResponse response,String id){
		try {
			uploadService.exportWord(response,id);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 导出Csv
	 * @param response
	 * @param id
	 * 2018年10月24日上午9:56:36
	 */
	@RequestMapping("/csv")
	@ResponseBody
	public R exportCsv(HttpServletResponse response,String ids){
		String url = null;
		try {
			url = uploadService.exportCsv(response,ids);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return R.ok().put("data", url);
	}
}
