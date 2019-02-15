package com.hww.es.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.es.pojo.WebReports;
import com.hww.es.service.IReportsService;
import com.hww.es.util.R;




/**
 * <p>报告</p>
 * <p>报告查询，上传，下载</p>
 * 2018年10月31日上午9:44:33
 * @author lvjie
 */
@RestController
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired
	private IReportsService reportsService;
	@Value("${upload}")
    private String UPLOAD_FOLDER;
	/**
	 * <p>按条件进行分页查询报告列表</p>
	 * <p>Description</p>
	 * @param pageNum 当前页号
	 * @param pageSize 每页显示几条
	 * @param reports
	 * @return
	 * @throws Exception
	 * 2018年10月31日下午1:37:20
	 * @author lvjie
	 */
	@RequestMapping("/getListSearch")
	@ResponseBody
	public R getListSearch(Integer pageNum, Integer pageSize,WebReports reports) throws Exception {
		try {
			PageHelper.startPage(pageNum, pageSize);
			List<WebReports> list = reportsService.getListSearch(reports);
			PageInfo<WebReports> info = new PageInfo<WebReports>(list);
			return R.ok().put("data", info);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	/**
	 * <p>根据id查询</p>
	 * <p>Description</p>
	 * @return
	 * 2018年11月1日上午9:28:18
	 * @author lvjie
	 */
	@RequestMapping("/findReportById")
	@ResponseBody
	public R findReportById(Integer id){
		WebReports web = reportsService.findReportById(id);
		return R.ok().put("data", web);
	}
	/**
	 * <p>根据id编辑</p>
	 * <p>Description</p>
	 * @param web
	 * @return
	 * 2018年11月1日上午9:43:14
	 * @author lvjie
	 */
	@RequestMapping("/updateReportById")
	public R updateReportById(WebReports web){
		int status = reportsService.updateReportById(web);
		return R.ok().put("data", status);
	}
	/**
	 * <p>根据id删除</p>
	 * <p>Description</p>
	 * @param id
	 * @return
	 * 2018年11月1日上午9:49:47
	 * @author lvjie
	 */
	@RequestMapping("/deleteReport")
	@ResponseBody
	public R deleteReport(Integer id){
		int status = reportsService.deleteReport(id);
		return R.ok().put("data", status);
	}
	/**
	 * <p>文件下载</p>
	 * <p>Description</p>
	 * @param ids
	 * @param request
	 * @param response
	 * 2018年11月1日上午10:00:55
	 * @author lvjie
	 */
	@RequestMapping("/uploads")
	@ResponseBody
	public void uploads(Integer id,HttpServletRequest request,HttpServletResponse response){
		try {
			reportsService.uploads(request,response,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * <p>文件上传</p>
	 * <p>Description</p>
	 * @param file
	 * @param reports
	 * @return
	 * @throws ServletException
	 * @throws Exception
	 * 2018年11月1日上午10:05:59
	 * @author lvjie
	 */
	@RequestMapping("/getUpload")
	public String getUpload(@RequestParam("file") MultipartFile file,WebReports reports)throws ServletException, Exception{
		long currentTime = new Date().getTime();
		
		String path = UPLOAD_FOLDER;  //获取需要上传的路径
		
		String fileName = file.getOriginalFilename();//获取文件名称
		
		String[] split = fileName.split("\\.");
		String title = split[0];//文档名称
		String docType = split[1];//文档类型
		
		String fileNameT= currentTime +"."+docType;
		File targetFile = new File(path);  
		if(!targetFile.exists()){  //判断文件夹是否存在，不存在就创建
		     targetFile.mkdirs();  
		}
		if(!file.isEmpty()){  
            try {  
                FileOutputStream fos = new FileOutputStream(path + fileNameT);  
                InputStream in = file.getInputStream();  
                int b = 0;  
                while ((b = in.read()) != -1) {  
                    fos.write(b);  
                }  
                fos.close();  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
		System.out.println("上传到:" + path + fileName);
		
		String reportType = new String(reports.getReportType().getBytes("ISO-8859-1"),"utf-8");
		
		String docUrl = path + currentTime +"."+docType;
		reports.setTitle(title);
		reports.setDocType(docType);
		reports.setIsRead(0);
		reports.setDocUrl(docUrl);
		reports.setSendingTime(new Date());
		reports.setNum(0);
		reports.setState("0");
		reports.setReportType(reportType);
		reportsService.insertReport(reports);
		return null;
	}
	
}
