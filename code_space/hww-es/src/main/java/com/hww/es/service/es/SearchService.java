package com.hww.es.service.es;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import com.hww.es.pojo.es.EsNews;
import com.hww.es.pojo.es.EsNewsQueryParam;
import com.hww.es.util.PageBean;

/**
 * 搜索接口
 * @author lvjie
 *
 */
public interface SearchService {

	/**
	 * 通过es查询文章详情
	 * @param type
	 * @param id
	 * @return
	 */
	public EsNews searchNewsDetail(String type, String id);
	
	/**
	 * 更新ES新闻内容
	 * @param news
	 */
	void updateEsNewsAttribute(EsNews news);

	/**
	 * 高级搜索
	 * @param param
	 * @param sourceIdList
	 * @param removeList
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageBean<EsNews> advancedSearch(EsNewsQueryParam param, BoolQueryBuilder builder, List<String> sourceIdList, List<Long> removeList,
			Integer pageNum, Integer pageSize);

	public HighlightBuilder getHighlightBuilder(Integer fragmentSize, Integer numOfFragments);
	
	public BoolQueryBuilder getAdvancedBuilder(EsNewsQueryParam param, List<String> sourceIdList, List<Long> removeList);
	//简报专用
	public PageBean<EsNews> getSearch(EsNewsQueryParam param, List<String> sourceIdList, List<Long> removeList,
			Integer pageNum, Integer pageSize);
}
