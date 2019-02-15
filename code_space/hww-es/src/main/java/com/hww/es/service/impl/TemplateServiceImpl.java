package com.hww.es.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hww.es.pojo.WebReports;
import com.hww.es.pojo.es.EsNews;
import com.hww.es.pojo.es.EsNewsQueryParam;
import com.hww.es.service.IReportsService;
import com.hww.es.service.ITemplateService;
import com.hww.es.service.es.QueryParamRemoveService;
import com.hww.es.service.es.QueryParamService;
import com.hww.es.service.es.SearchService;
import com.hww.es.service.websource.WebSourceService;
import com.hww.es.util.PageBean;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
/**
 * <p>根据模版生成文档</p>
 * <p>Description</p>
 * 2018年11月12日上午10:35:47
 * @author lvjie
 */
@Service
public class TemplateServiceImpl implements ITemplateService{
	@Autowired
	QueryParamService queryParamService;
	@Autowired
	WebSourceService webSourceService;
	@Autowired
	private SearchService searchService;
	@Autowired
	QueryParamRemoveService queryParamRemoveService;
	@Value("${upload}")
    private String UPLOAD_FOLDER;
	@Autowired
	private IReportsService reportsService;
	/**
	 * <p>按固定模版导出固定word</p>
	 * @param dataMap 传入参数
	 * @throws Exception
	 * 2018年11月12日上午11:27:09
	 * @author lvjie
	 */
	@Override
	public void generateWord(HttpServletResponse response,Map<String, String> dataMap) throws Exception{
		
	    //Configuration用于读取ftl文件  
        Configuration configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
        
        /*以下是两种指定ftl文件所在目录路径的方式, 注意这两种方式都是 
         * 指定ftl文件所在目录的路径,而不是ftl文件的路径 
         */  
        //指定路径的第一种方式(根据某个类的相对路径指定)  
        configuration.setClassForTemplateLoading(this.getClass(),"/templates");  
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(new Date());
        
        // 输出文档路径及名称  以当前日期格式为文件名称
        File outFile = new File("D:/"+date+".doc");  
       
        //以utf-8的编码读取ftl文件  
        //先建立好word模版，然后另存为.XML文件，然后把需要赋值的部位修改${属性}，最后改成.ftl文件
        Template t =  configuration.getTemplate("download.ftl","utf-8");  
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);  
        t.process(dataMap, out);  
        out.close();
        
        /**
         * 下面是直接返回文档
         */
//        try {
//        	String fileName = date+".doc";
//			response.setContentType("application/octet-stream");
//			response.setHeader("Content-Disposition", "attachment;filename="
//					+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));
//			response.setCharacterEncoding("utf-8");//这句很重要，不加word里都是乱码
//			t.process(dataMap, response.getWriter());
//		}catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			response.flushBuffer();
//		}
	}
	/**
	 * <p>根据模版按表格形式生成文档</p>
	 * @throws Exception
	 * 2018年11月13日下午1:42:06
	 * @author lvjie
	 */
	@Override
	public void templateExcel() throws Exception {
		try {
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap = this.getDbInfo();
			
			Configuration configuration = new Configuration();
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			//加载需要装填的模板
			configuration.setClassForTemplateLoading(this.getClass(),"/templates");
			//设置对象包装器
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			//设置异常处理器
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			//定义Template对象
			Template template = configuration.getTemplate("tempExcel.ftl");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String date = sdf.format(new Date());
	        // 输出文档路径及名称  以当前日期格式为文件名称
	        File outFile = new File("D:/"+date+".doc"); 
	        
	        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);  
	        template.process(dataMap, out);  
	        out.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * <p>获取填充数据</p>
	 * @return
	 * 2018年11月13日下午1:47:32
	 * @author lvjie
	 */
	public Map<String,Object> getDbInfo(){
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("xzname", "舆情数据组");
		dataMap.put("cldate", "2018-10-01");
		dataMap.put("ktname", "舆情数据分析");
		dataMap.put("kttype", "海外数据");
		dataMap.put("sjzy", "数据分析师");
		dataMap.put("hdzd", "大数据组");
		dataMap.put("num","10010");
		List<Map<String ,Object>> members = new ArrayList<>();
		for(int i=0;i<10;i++){
			Map<String ,Object> memMap = new HashMap<>();
			memMap.put("name", "张三-"+i);
			memMap.put("sex", "男-"+i);
			memMap.put("age", "22-"+i);
			memMap.put("level", "二级-"+i);
			memMap.put("labor", "数据分析-"+i);
			members.add(memMap);
		}
		dataMap.put("user", members);
		return dataMap;
	}
	/**
	 * <p>摘要版</p>
	 * @param response
	 * @param param 搜索条件
	 * @throws Exception
	 * 2018年11月14日上午9:29:46
	 * @author lvjie
	 * @return 
	 */
	@Override
	public String tempAbstract(HttpServletResponse response,
			EsNewsQueryParam param) throws Exception {
		String url = null;
		try {
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap = this.getAbstractInfo(param);
			
			Configuration configuration = new Configuration();
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			//加载需要装填的模板
			configuration.setClassForTemplateLoading(this.getClass(),"/templates");
			//设置对象包装器
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			//设置异常处理器
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			//定义Template对象
			Template template = configuration.getTemplate("tempAbstract.ftl");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String date = sdf.format(new Date());
	        /**
	         * 生成保存文档
	         */
	        String docUrl = UPLOAD_FOLDER + date +".doc";
	        // 输出文档路径及名称  以当前日期格式为文件名称
	        File outFile = new File(docUrl); 
	        
	        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);  
	        template.process(dataMap, out);  
	        out.close();
	        
	        WebReports reports = new WebReports();
	        reports.setUserId(param.getUserId());//用户id
	        reports.setTitle(param.getTitle());
			reports.setDocType("doc");
			reports.setIsRead(0);
			reports.setDocUrl(docUrl);
			reports.setSendingTime(new Date());
			reports.setNum(0);
			reports.setState("0");
			reportsService.insertReport(reports);
	        
			url = "/file/"+date +".doc";
	        /**
	         * 下面是直接返回文档
	         */
//	        try {
//	        	String fileName = date+".doc";
//				response.setContentType("application/octet-stream");
//				response.setHeader("Content-Disposition", "attachment;filename="
//						+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));
//				response.setCharacterEncoding("utf-8");//这句很重要，不加word里都是乱码
//				template.process(dataMap, response.getWriter());
//			}catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				response.flushBuffer();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	/**
	 * <p>根据条件查询</p>
	 * @param param 搜索条件
	 * @return
	 * 2018年11月14日上午9:31:31
	 * @author lvjie
	 */
	public Map<String,Object> getAbstractInfo(EsNewsQueryParam param){
		Map<String,Object> dataMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		dataMap.put("newDate", sdf.format(new Date()));
		dataMap.put("date", sdf1.format(new Date()));
		List<Map<String ,Object>> members = new ArrayList<>();
//		for(int i=0;i<10;i++){
//			Map<String ,Object> memMap = new HashMap<>();
//			memMap.put("num", (i+1));//序列
//			memMap.put("inserDate", sdf1.format(new Date()));//消息时间
//			memMap.put("source", "人民日报海外网");//来源
//			memMap.put("title", "叙利亚空袭问题跟踪"+i);//标题
//			memMap.put("abstract", "叙利亚昨日遭到导弹突然袭击，很多平民受伤，急需救治"+i);//简介
//			members.add(memMap);
//		}
		
		List<EsNews> list = new ArrayList<EsNews>();
		if(param.getIds()!=null&&param.getIds().trim()!=""){//当ids集合不为空时，说明为选择导出
			String[] split = param.getIds().split(",");
			for(int i=0;i<split.length;i++){
				String id = split[i];
				EsNews news = searchService.searchNewsDetail("htmlbean", id);//查询新闻
				list.add(news);
			}
		}else{//否则为搜索
			int pageNum = 1;
			int pageSize = 20;
			PageBean<EsNews> page;
			List<Long> removeList = null;//排除新闻列表
			List<String> sourceIdList = null;
			List<Long> webTypeIdList = param.getWebTypeIdList();
			if(param.getId() != null) {//如果id不是null，是已经定制好的搜索集合
				param = queryParamService.getById(param.getId());//根据id查出详情
				removeList = queryParamRemoveService.getListByQueryParamId(param.getId());//查出删除列表
			}
			if(webTypeIdList != null && webTypeIdList.size() > 0) {
				sourceIdList = webSourceService.getSourceIdListByTypeIdList(webTypeIdList);//查询站点
			}
			page = searchService.getSearch(param,sourceIdList,removeList,pageNum, pageSize);//最后多条件分页查询
			
			list = page.getList();//数据集合
		}
		
		for(int i=0;i<list.size();i++){
			Map<String ,Object> memMap = new HashMap<>();
			memMap.put("num", (i+1));//序列
			memMap.put("inserDate", list.get(i).getPublishDate());//消息时间
			memMap.put("source", list.get(i).getSource());//来源
			memMap.put("title", list.get(i).getTitle());//标题
			memMap.put("abstract", list.get(i).getIntroduction());//简介
			members.add(memMap);
		}
		
		dataMap.put("page", members);
		return dataMap;
	}
	/**
	 * <p>导出文档含图片测试</p>
	 * @param base64
	 * @param response
	 * 2018年11月26日上午8:42:05
	 * @author lvjie
	 */
	@Override
	public void downloadDoc(String base64, HttpServletResponse response) {
		try {
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("title", "舆情数据组");
			dataMap.put("base64", "舆情数据组");
			
			Configuration configuration = new Configuration();
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			//加载需要装填的模板
			configuration.setClassForTemplateLoading(this.getClass(),"/templates");
			//设置对象包装器
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			//设置异常处理器
			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			//定义Template对象
			Template template = configuration.getTemplate("tempAbstract.ftl");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String date = sdf.format(new Date());
	        
	        /**
	         * 下面是直接返回文档
	         */
	        try {
	        	String fileName = date+".doc";
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename="
						+ new String(fileName.getBytes("GBK"), "ISO-8859-1"));
				response.setCharacterEncoding("utf-8");//这句很重要，不加word里都是乱码
				template.process(dataMap, response.getWriter());
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				response.flushBuffer();
			}
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
