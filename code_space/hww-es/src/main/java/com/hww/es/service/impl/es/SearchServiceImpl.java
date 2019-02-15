package com.hww.es.service.impl.es;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.hww.es.mapper.WebSourceMapper;
import com.hww.es.pojo.es.EsNews;
import com.hww.es.pojo.es.EsNewsDTO;
import com.hww.es.pojo.es.EsNewsQueryParam;
import com.hww.es.pojo.es.EsNewsQueryParamShould;
import com.hww.es.service.es.SearchService;
import com.hww.es.util.PageBean;

/**
 * 搜索主逻辑方法
 * @author lvjie
 * @2018年1月29日
 */
@Service
public class SearchServiceImpl implements SearchService {
	@Resource
	private Client client;
	@Autowired  
    private Environment env;
	@Autowired  
	WebSourceMapper webSourceMapper;
	
	@Override
	public EsNews searchNewsDetail(String type,String id) {
		
		QueryBuilder qb = QueryBuilders.idsQuery(type).addIds(id);
		SearchResponse resp = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes(type).setQuery(qb).execute().actionGet();
		SearchHit[] hits = resp.getHits().getHits();
		if(hits.length == 1) {
			EsNews news = new EsNews();
			SearchHit hit = hits[0];
			news.setTitle(String.valueOf(hit.getSource().get("title")));// 获取原来的title（没有高亮的title）
			news.setContent(String.valueOf(hit.getSource().get("content")));// 获取原来的content（没有高亮的content）
			news.setUrl(String.valueOf(hit.getSource().get("url")));
			news.setArea(String.valueOf(hit.getSource().get("area")));
			news.setAttribute(String.valueOf(hit.getSource().get("attribute")));
			news.setIntroduction(String.valueOf(hit.getSource().get("introduction")));
			news.setAuthor(String.valueOf(hit.getSource().get("author")));
			news.setLanguage(String.valueOf(hit.getSource().get("language")));
			news.setMediaType(String.valueOf(hit.getSource().get("media_type")));
			news.setPublishDate(String.valueOf(hit.getSource().get("publish_date")));
			Long sourceId = Long.valueOf(String.valueOf(hit.getSource().get("source_id")));
			String sourceName = webSourceMapper.selectFullNameById(sourceId);
			news.setSource(sourceName);
			news.setSourceId(String.valueOf(sourceId));
			news.setSourceUrl(String.valueOf(hit.getSource().get("source_url")));
			news.setSimiCount(String.valueOf(hit.getSource().get("simi_count")));
			news.setPersons(String.valueOf(hit.getSource().get("persons").equals("have nothing")? null:hit.getSource().get("persons")));
			news.setLocations(String.valueOf(hit.getSource().get("locations").equals("have nothing")? null:hit.getSource().get("locations")));
			news.setKeywords(String.valueOf(hit.getSource().get("keywords").equals("have nothing")? null:hit.getSource().get("keywords")));
			news.setLenContent(String.valueOf(hit.getSource().get("len_content")));
			news.setNeg(String.valueOf(hit.getSource().get("neg")));
			news.setPos(String.valueOf(hit.getSource().get("pos")));
			news.setId(hit.getId());
			news.setScore(hit.getScore());
			news.setSimiNewsList((List<Map<String,Object>>)hit.getSource().get("simi_news"));
			return news;
		}else {
			return null;
		}
	}

	@Override
	public void updateEsNewsAttribute(EsNews news) {
		// TODO Auto-generated method stub
		Map<String, Object> dataMap =new HashMap<String, Object>();
		dataMap.put("attribute", news.getAttribute());
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(env.getProperty("elasticsearch.ennew.name"));
		updateRequest.type("htmlbean");
		updateRequest.id(news.getId());
		updateRequest.doc(dataMap);
		client.update(updateRequest);
	}
	
	@Override
	public PageBean<EsNews> advancedSearch(EsNewsQueryParam param,BoolQueryBuilder builder, List<String> sourceIdList, List<Long> removeList,
			Integer pageNum, Integer pageSize) {
		PageBean<EsNews> pageBean = new PageBean<EsNews>();// 默认pageNum=1，pageSize=10
		if (pageNum != null && pageNum > 1) {
			pageBean.setPageNum(pageNum);
		}
		if (pageSize != null && pageSize > 1) {
			pageBean.setPageSize(pageSize);
		}
		if(builder == null) {
			builder = getAdvancedBuilder(param, sourceIdList, removeList);
		}
		HighlightBuilder highlightBuilder = getHighlightBuilder(100, 3);
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean")
				.setQuery(builder).setFrom(pageBean.getStartRow()).setSize(pageBean.getPageSize()).highlighter(highlightBuilder);
		
		if(StringUtils.isNotBlank(param.getSortBy())) {
			searchRequestBuilder.addSort("publish_date", SortOrder.DESC);
		}
		
		SearchResponse resp = searchRequestBuilder.execute().actionGet();
		SearchHits hits = resp.getHits();
		pageBean.setTotal((int) hits.getTotalHits());
		for (SearchHit hit : hits.getHits()) {
			EsNewsDTO news = new EsNewsDTO();
			
			if (hit.getHighlightFields().get("title") == null) {// title中没有包含关键字
				 news.setTitle(String.valueOf(hit.getSource().get("title")));//
		 //获取原来的title（没有高亮的title）
			 } else {
				 //news.setTitle(hit.getHighlightFields().get("title").getFragments()[0].toString());
				 StringBuilder sb = new StringBuilder();
				 
				 for (Text text : hit.getHighlightFields().get("title").getFragments()) {
					 sb.append(text.toString());
				 }
				 news.setTitle(sb.toString());
			 }
			
			if (hit.getHighlightFields().get("content") == null) {//
				 //content中没有包含关键字
					 news.setContent(String.valueOf(hit.getSource().get("content")).substring(100));//
				 //获取原来的content（没有高亮的content）
			} else {
				 StringBuilder sb = new StringBuilder();
				 
				 for (Text text : hit.getHighlightFields().get("content").getFragments()) {
					 sb.append(text.toString() + "...");
				 }
				 news.setContent(sb.toString());
			}
			int totalCount = 0;
			String string = String.valueOf(hit.getSource().get("title")) + String.valueOf(hit.getSource().get("content"));
			if(StringUtils.isNotBlank(param.getMust())) {
				String[] keywordArray = param.getMust().split(",");
				for(String k : keywordArray) {
					int countMatches = StringUtils.countMatches(string.toLowerCase(), k.toLowerCase());
					totalCount = totalCount + countMatches;
				}
			}
			if(param.getShouldsList() != null && param.getShouldsList().size() > 0) {
				for(EsNewsQueryParamShould shoulds : param.getShouldsList()) {
					String[] shouldsArray = shoulds.getShoulds().split(",");
					for(String should : shouldsArray) {
						int countMatches = StringUtils.countMatches(string.toLowerCase(), should.toLowerCase());
						totalCount = totalCount + countMatches;
					}
				}
			}
			news.setTotalMatchCount(totalCount);
			news.setUrl(String.valueOf(hit.getSource().get("url")));
			news.setArea(String.valueOf(hit.getSource().get("area")));
			news.setAttribute(String.valueOf(hit.getSource().get("attribute")));
			news.setIntroduction(String.valueOf(hit.getSource().get("introduction")));
			news.setAuthor(String.valueOf(hit.getSource().get("author")));
			news.setLanguage(String.valueOf(hit.getSource().get("language")));
			news.setMediaType(String.valueOf(hit.getSource().get("media_type")));
			news.setPublishDate(String.valueOf(hit.getSource().get("publish_date")));
			Long sourceId = Long.valueOf(String.valueOf(hit.getSource().get("source_id")));
			String sourceName = webSourceMapper.selectFullNameById(sourceId);
			news.setSource(sourceName);
			news.setSourceId(String.valueOf(sourceId));
			news.setSimiCount(String.valueOf(hit.getSource().get("simi_count")));
			news.setLenContent(String.valueOf(hit.getSource().get("len_content")));
			news.setId(hit.getId());
			news.setScore(hit.getScore());
			pageBean.setBean(news);
		}
		return pageBean;
	}

	@Override
	public HighlightBuilder getHighlightBuilder(Integer fragmentSize, Integer numOfFragments) {
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder
			.field("title")
			.field("content")
			.preTags("<font color=\"red\">")
			.postTags("</font>")
			.fragmentSize(fragmentSize)
			.numOfFragments(numOfFragments);
		return highlightBuilder;
	}

	@Override
	public BoolQueryBuilder getAdvancedBuilder(EsNewsQueryParam param, List<String> sourceIdList,
			List<Long> removeList) {

		String musts = param.getMust();
		String mustNots = param.getMustNot();
		List<EsNewsQueryParamShould> shouldsList = param.getShouldsList();
		
		BoolQueryBuilder builder = null;
		builder = QueryBuilders.boolQuery();
		
		if(StringUtils.isNotBlank(mustNots)) {
			String[] mustNotsArray = mustNots.split("\\,");
			for(String mustNot : mustNotsArray) {
				if(StringUtils.isBlank(param.getSearchRange())) {
					builder.mustNot(QueryBuilders.matchPhraseQuery("title", mustNot));
					builder.mustNot(QueryBuilders.matchPhraseQuery("content", mustNot));
				}else {
					builder.mustNot(QueryBuilders.matchPhraseQuery("title", mustNot));
				}
			}
		}
		
		if(StringUtils.isNotBlank(musts)) {
			String[] mustsArray = musts.split("\\,");
			for(String must : mustsArray) {
				if(StringUtils.isBlank(param.getSearchRange())) {
					builder.must(QueryBuilders.boolQuery()
							.should(QueryBuilders.matchPhraseQuery("title", must))
							.should(QueryBuilders.matchPhraseQuery("content", must))
							.minimumShouldMatch(1)
							);
				}else {
					builder.must(QueryBuilders.matchPhraseQuery("title", must));
				}
			}	
		}
		
		if(shouldsList != null && shouldsList.size() > 0) {
			for(EsNewsQueryParamShould queryParamShould : shouldsList) {
				String shoulds = queryParamShould.getShoulds();
				String[] shouldsArray = shoulds.split("\\,");
				BoolQueryBuilder shouldsBoolQuery = QueryBuilders.boolQuery();
				
				for(String should : shouldsArray) {
					BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
					if(StringUtils.isBlank(param.getSearchRange())) {
						boolQuery.should(QueryBuilders.matchPhraseQuery("title", should));
						boolQuery.should(QueryBuilders.matchPhraseQuery("content", should));
					}else {
						boolQuery.should(QueryBuilders.matchPhraseQuery("title", should));
					}
					boolQuery.minimumShouldMatch(1);
					shouldsBoolQuery.should(boolQuery).minimumShouldMatch(queryParamShould.getMinMatch());
				}
				builder.must(shouldsBoolQuery);
			}
		}		
		
		BoolQueryBuilder filterQueryBuilder = new BoolQueryBuilder();//过滤用builder
		//将关键字以外的入参全部作为过滤条件，而不再是查询条件
		if (StringUtils.isNotBlank(param.getAttributes())) {
			List<String> AttributeList = Arrays.asList(param.getAttributes().split(","));
			filterQueryBuilder.must(QueryBuilders.termsQuery("attribute",AttributeList));
		}
		if (StringUtils.isNotBlank(param.getLanguages())) {
			List<String> languageList = Arrays.asList(param.getLanguages().split(","));
			filterQueryBuilder.must(QueryBuilders.termsQuery("language", languageList));
		}
		if (StringUtils.isNotBlank(param.getMediaTypes())) {
			List<String> mediaTypeList = Arrays.asList(param.getMediaTypes().split(","));
			filterQueryBuilder.must(QueryBuilders.termsQuery("media_type", mediaTypeList));
		}
		if (StringUtils.isNotBlank(param.getStartDate()) && StringUtils.isNotBlank(param.getEndDate())) {//同时设置开始时间和结束时间
			filterQueryBuilder.must(QueryBuilders.rangeQuery("publish_date").format("yyyy-MM-dd").gte(param.getStartDate()).lte(param.getEndDate()));
		}
		
		if(StringUtils.isNotBlank(param.getDateRange())) {
			filterQueryBuilder.must(QueryBuilders.rangeQuery("publish_date").gte(param.getDateRange()));
		}
		
		if(sourceIdList != null && sourceIdList.size()>0) {
			filterQueryBuilder.must(QueryBuilders.termsQuery("source_id", sourceIdList));
		}
		//排除当前高级搜索条件下的删除新闻
		if(removeList != null && removeList.size() > 0) {
			filterQueryBuilder.mustNot(QueryBuilders.termsQuery("_id", removeList));
		}
		
		return builder.filter(filterQueryBuilder);
	}
	/**
	 * <p>简报专用</p>
	 * @param param
	 * @param sourceIdList
	 * @param removeList
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * 2018年11月22日下午3:15:26
	 * @author lvjie
	 */
	@Override
	public PageBean<EsNews> getSearch(EsNewsQueryParam param, List<String> sourceIdList, List<Long> removeList,
			Integer pageNum, Integer pageSize) {
		PageBean<EsNews> pageBean = new PageBean<EsNews>();// 默认pageNum=1，pageSize=10
		if (pageNum != null && pageNum > 1) {
			pageBean.setPageNum(pageNum);
		}
		if (pageSize != null && pageSize > 1) {
			pageBean.setPageSize(pageSize);
		}
		
		BoolQueryBuilder builder = getAdvancedBuilder(param, sourceIdList, removeList);
		HighlightBuilder highlightBuilder = getHighlightBuilder(100, 3);
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean")
				.setQuery(builder).setFrom(pageBean.getStartRow()).setSize(pageBean.getPageSize()).highlighter(highlightBuilder);
		
		if(StringUtils.isNotBlank(param.getSortBy())) {
			searchRequestBuilder.addSort("publish_date", SortOrder.DESC);
		}
		
		SearchResponse resp = searchRequestBuilder.execute().actionGet();
		SearchHits hits = resp.getHits();
		pageBean.setTotal((int) hits.getTotalHits());
		for (SearchHit hit : hits.getHits()) {
			EsNewsDTO news = new EsNewsDTO();
			
			if (hit.getHighlightFields().get("title") == null) {// title中没有包含关键字
				 news.setTitle(String.valueOf(hit.getSource().get("title")));//
		 //获取原来的title（没有高亮的title）
			 } else {
				 //news.setTitle(hit.getHighlightFields().get("title").getFragments()[0].toString());
				 StringBuilder sb = new StringBuilder();
				 
				 for (Text text : hit.getHighlightFields().get("title").getFragments()) {
					 sb.append(text.toString());
				 }
				 news.setTitle(sb.toString());
			 }
			
			if (hit.getHighlightFields().get("content") == null) {//
				 //content中没有包含关键字
					 news.setContent(String.valueOf(hit.getSource().get("content")).substring(100));//
				 //获取原来的content（没有高亮的content）
			} else {
				 StringBuilder sb = new StringBuilder();
				 
				 for (Text text : hit.getHighlightFields().get("content").getFragments()) {
					 sb.append(text.toString() + "...");
				 }
				 news.setContent(sb.toString());
			}
			int totalCount = 0;
			String string = String.valueOf(hit.getSource().get("title")) + String.valueOf(hit.getSource().get("content"));
			if(StringUtils.isNotBlank(param.getMust())) {
				String[] keywordArray = param.getMust().split(",");
				for(String k : keywordArray) {
					int countMatches = StringUtils.countMatches(string.toLowerCase(), k.toLowerCase());
					totalCount = totalCount + countMatches;
				}
			}
			if(param.getShouldsList() != null && param.getShouldsList().size() > 0) {
				for(EsNewsQueryParamShould shoulds : param.getShouldsList()) {
					String[] shouldsArray = shoulds.getShoulds().split(",");
					for(String should : shouldsArray) {
						int countMatches = StringUtils.countMatches(string.toLowerCase(), should.toLowerCase());
						totalCount = totalCount + countMatches;
					}
				}
			}
			news.setTotalMatchCount(totalCount);
			news.setUrl(String.valueOf(hit.getSource().get("url")));
			news.setArea(String.valueOf(hit.getSource().get("area")));
			news.setAttribute(String.valueOf(hit.getSource().get("attribute")));
			news.setIntroduction(String.valueOf(hit.getSource().get("introduction")));
			news.setAuthor(String.valueOf(hit.getSource().get("author")));
			news.setLanguage(String.valueOf(hit.getSource().get("language")));
			news.setMediaType(String.valueOf(hit.getSource().get("media_type")));
			news.setPublishDate(String.valueOf(hit.getSource().get("publish_date")));
			Long sourceId = Long.valueOf(String.valueOf(hit.getSource().get("source_id")));
			String sourceName = webSourceMapper.selectFullNameById(sourceId);
			news.setSource(sourceName);
			news.setSourceId(String.valueOf(sourceId));
			news.setSimiCount(String.valueOf(hit.getSource().get("simi_count")));
			news.setLenContent(String.valueOf(hit.getSource().get("len_content")));
			news.setId(hit.getId());
			news.setScore(hit.getScore());
			pageBean.setBean(news);
		}
		return pageBean;
	}

}
