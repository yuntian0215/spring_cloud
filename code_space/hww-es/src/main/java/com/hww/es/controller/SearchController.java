package com.hww.es.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hww.es.pojo.es.EsNews;
import com.hww.es.pojo.es.EsNewsQueryParam;
import com.hww.es.pojo.es.EsNewsQueryParamRemove;
import com.hww.es.service.ITranslationService;
import com.hww.es.service.es.ChartService;
import com.hww.es.service.es.QueryParamRemoveService;
import com.hww.es.service.es.QueryParamService;
import com.hww.es.service.es.SearchService;
import com.hww.es.service.websource.WebSourceService;
import com.hww.es.service.websource.WebTypeService;
import com.hww.es.util.PageBean;
import com.hww.es.util.R;
import com.hww.es.util.TranslateUtil;

/**
 * 搜索主方法控制类
 * @author lvjie
 * @2018年1月29日
 */
@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	@Autowired
	ChartService chartService;
	@Autowired
	private ITranslationService translationService;
	@Autowired
	QueryParamService queryParamService;
	@Autowired
	QueryParamRemoveService queryParamRemoveService;
	@Autowired
	WebTypeService webTypeService;
	@Autowired
	WebSourceService webSourceService;
	/**
	 * 高级搜索
	 * @param pageNum 当前第几页
	 * @param pageSize 每页多少条
	 * @param param 搜索条件类
	 * @return R
	 * @throws Exception
	 */
	@RequestMapping("/advanced")
	@ResponseBody
	public R advanced(EsNewsQueryParam param, Integer pageNum, Integer pageSize,String mediaTypes, String languages,Integer firstSearch,String sortBy) throws Exception {
		PageBean<EsNews> page;
		Map<String,Object> data = new HashMap<String,Object>();
		try {
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
			
			if(StringUtils.isNotBlank(sortBy)) {
				param.setSortBy(sortBy);
			}
			if(StringUtils.isNotBlank(mediaTypes)) {
				param.setMediaTypes(mediaTypes);
			}
			if(StringUtils.isNotBlank(languages)) {
				param.setLanguages(languages);
			}
			
			BoolQueryBuilder builder = searchService.getAdvancedBuilder(param, sourceIdList, removeList);
			page = searchService.advancedSearch(param,builder,sourceIdList,removeList,pageNum, pageSize);//最后多条件分页查询
			data.put("page", page);
			
			if(firstSearch != null && firstSearch.equals(1) ) {
				List<Map<String, Object>> mediaTypeCount = chartService.getFieldCount(builder, "media_type");
				data.put("mediaTypeCount", mediaTypeCount);
			}
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	/**
	 * 更新舆情属性(根据id更新数据属性)
	 * @param news 新闻属性类
	 * @return R
	 * @throws Exception
	 */
	@RequestMapping("/updateNewsAttribute")
	@ResponseBody
	public R updateNewsAttribute(EsNews news) throws Exception {
		try {
			searchService.updateEsNewsAttribute(news);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 删除查询参数集合所查出的列表中的一个条新闻
	 * @param remove
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/removeNews")
	@ResponseBody
	public R updateNewsAttribute(EsNewsQueryParamRemove remove) throws Exception {
		try {
			queryParamRemoveService.add(remove);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 新闻详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/news")
	@ResponseBody
	public R index(String id) throws Exception {
		
		EsNews news;
		try {
			Map<String,Object> result = new HashMap<String,Object>();
			news = searchService.searchNewsDetail("htmlbean", id);
			if(news == null) {
				return R.error("没有文章");
			}
			result.put("news", news);
			return R.ok().put("data", result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return R.error();
		}
	}
	/**
	 * <p>翻译</p>
	 * @param title
	 * @param introduction
	 * @param content
	 * @param trans
	 * @return
	 * @throws Exception
	 * 2018年12月5日下午2:29:30
	 * @author lvjie
	 */
	@RequestMapping("/news/translate")
	@ResponseBody
	public R translate(String title,String introduction,String content,Integer trans,String newsId) throws Exception {
		try {
			Map<String,Object> result = new HashMap<String,Object>();
			String title_translated = "";
			String introduction_translated = "";
			String content_translated = "";
			
			if(trans == 1) {//翻译
				if(StringUtils.isNotBlank(title)) {
					title_translated = translationService.translate(title);
				}
				if(StringUtils.isNotBlank(introduction)) {
					introduction_translated = translationService.translate(introduction);
				}
				if(StringUtils.isNotBlank(content)) {
					content_translated = translationService.translate(content);
				}
			}
			if(trans == 2) {//翻译成英语
				EsNews news = searchService.searchNewsDetail("htmlbean", newsId);
				if(StringUtils.isNotBlank(title)) {
//					title_translated = translationService.translateEN(title);
					title_translated = news.getTitle();
				}
				if(StringUtils.isNotBlank(introduction)) {
//					introduction_translated = translationService.translateEN(introduction);
					introduction_translated = news.getIntroduction();
				}
				if(StringUtils.isNotBlank(content)) {
//					content_translated = translationService.translateEN(content);
					content_translated = news.getContent();
				}
				
			}
			result.put("title", title_translated);
			result.put("introduction", introduction_translated);
			result.put("content", content_translated);
			return R.ok().put("data", result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return R.error();
		}
	}
	
	@RequestMapping("/simple")
	@ResponseBody
	public R simple(EsNewsQueryParam param, Integer pageNum, Integer pageSize) throws Exception {
		PageBean<EsNews> page;
		Map<String,Object> data = new HashMap<String,Object>();
		try {
			param.setDateRange("now-30d");
			page = searchService.advancedSearch(param,null,null,null,pageNum, pageSize);//最后多条件分页查询
			data.put("page", page);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
