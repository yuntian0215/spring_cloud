package com.hww.es.service.impl.es;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hww.es.pojo.websource.WebSource;
import com.hww.es.service.es.EsWebSourceService;
import com.hww.es.util.PageBean;

@Service
public class EsWebSourceServiceImpl implements EsWebSourceService {

	@Autowired
	private Client client;
	@Autowired  
    private Environment env;
	
	@Override
	public void addWebSource(WebSource websource) {
		client
			.prepareIndex(env.getProperty("elasticsearch.websource.name"), env.getProperty("elasticsearch.websource.name"))
			.setId(websource.getSourceId().toString())
			.setSource(WebSourceToDocMap(websource))
			.get();
	}
	
	@Override
	public void addWebSourceList(List<WebSource> list) {
		// TODO Auto-generated method stub
	    BulkRequestBuilder bulkRequest = client.prepareBulk();
	    
	    for(WebSource websource : list) {
		    bulkRequest
		    	.add(client.prepareIndex(env.getProperty("elasticsearch.websource.name"), env.getProperty("elasticsearch.websource.name"))
		    	.setId(websource.getSourceId().toString())
		    	.setSource(WebSourceToDocMap(websource)));
	    }
	    bulkRequest.get();
	}
	
	private Map<String,Object> WebSourceToDocMap(WebSource websource){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("full_name", websource.getFullName());
		map.put("continent", websource.getContinent());
		map.put("area", websource.getCountry());
		map.put("home_page", websource.getHomePage());
		map.put("language", websource.getLanguage());
		map.put("icon", websource.getLogo());
		map.put("media_type", websource.getMediaType());
		map.put("province", websource.getProvince());
		return map;
	}

	@Override
	public PageBean<WebSource> searchWebSource(WebSource websource,Integer pageNum, Integer pageSize) {
		PageBean<WebSource> pageBean = new PageBean<WebSource>();// 默认pageNum=1，pageSize=10
		if (pageNum != null && pageNum > 1) {
			pageBean.setPageNum(pageNum);
		}
		if (pageSize != null && pageSize > 1) {
			pageBean.setPageSize(pageSize);
		}

		BoolQueryBuilder builder = QueryBuilders.boolQuery();

		if(StringUtils.isNotBlank(websource.getFullName())) {
			builder.must(QueryBuilders.matchPhraseQuery("full_name", websource.getFullName()));
		}
		if(StringUtils.isNotBlank(websource.getHomePage())) {
			builder.must(QueryBuilders.matchPhraseQuery("home_page", websource.getHomePage()));
		}
		
		BoolQueryBuilder filterQueryBuilder = new BoolQueryBuilder();
		
		if(StringUtils.isNotBlank(websource.getCountry())) {
			filterQueryBuilder.must(QueryBuilders.termQuery("area", websource.getCountry()));
		}
		if(StringUtils.isNotBlank(websource.getLanguage())) {
			filterQueryBuilder.must(QueryBuilders.termQuery("language", websource.getLanguage()));
		}
		if(StringUtils.isNotBlank(websource.getMediaType())) {
			filterQueryBuilder.must(QueryBuilders.termQuery("media_type", websource.getMediaType()));
		}
		if(StringUtils.isNotBlank(websource.getProvince())) {
			filterQueryBuilder.must(QueryBuilders.termQuery("province", websource.getProvince()));
		}
		builder.filter(filterQueryBuilder);
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.websource.name")).setTypes(env.getProperty("elasticsearch.websource.name"))
				.setQuery(builder).setFrom(pageBean.getStartRow()).setSize(pageBean.getPageSize());
		SearchResponse resp = searchRequestBuilder.execute().actionGet();
		SearchHits hits = resp.getHits();
		pageBean.setTotal((int) hits.getTotalHits());
		for (SearchHit hit : hits.getHits()) {
			WebSource web = new WebSource();
			web.setContinent(String.valueOf(hit.getSource().get("continent")));
			web.setCountry(String.valueOf(hit.getSource().get("area")));
			web.setFullName(String.valueOf(hit.getSource().get("full_name")));
			web.setHomePage(String.valueOf(hit.getSource().get("home_page")));
			web.setLanguage(String.valueOf(hit.getSource().get("language")));
			web.setLogo(String.valueOf(hit.getSource().get("icon")));
			web.setMediaType(String.valueOf(hit.getSource().get("media_type")));
			web.setProvince(String.valueOf(hit.getSource().get("province")));
			web.setSourceId(Long.valueOf(hit.getId()));
			pageBean.setBean(web);
		}
		return pageBean;
	}

	@Override
	public List<WebSource> searchWebSourceNoPage(WebSource websource) {
		List<WebSource> list = new ArrayList<WebSource>();

		BoolQueryBuilder builder = QueryBuilders.boolQuery();

		if(StringUtils.isNotBlank(websource.getFullName())) {
			builder.must(QueryBuilders.matchPhraseQuery("full_name", websource.getFullName()));
		}
		if(StringUtils.isNotBlank(websource.getHomePage())) {
			builder.must(QueryBuilders.matchPhraseQuery("home_page", websource.getHomePage()));
		}
		
		BoolQueryBuilder filterQueryBuilder = new BoolQueryBuilder();
		
		if(StringUtils.isNotBlank(websource.getCountry())) {
			filterQueryBuilder.must(QueryBuilders.termQuery("area", websource.getCountry()));
		}
		if(StringUtils.isNotBlank(websource.getLanguage())) {
			filterQueryBuilder.must(QueryBuilders.termQuery("language", websource.getLanguage()));
		}
		if(StringUtils.isNotBlank(websource.getMediaType())) {
			filterQueryBuilder.must(QueryBuilders.termQuery("media_type", websource.getMediaType()));
		}
		if(StringUtils.isNotBlank(websource.getProvince())) {
			filterQueryBuilder.must(QueryBuilders.termQuery("province", websource.getProvince()));
		}
		builder.filter(filterQueryBuilder);
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.websource.name")).setTypes(env.getProperty("elasticsearch.websource.name"))
				.setQuery(builder).setFrom(0).setSize(1000);
		SearchResponse resp = searchRequestBuilder.execute().actionGet();
		SearchHits hits = resp.getHits();
		for (SearchHit hit : hits.getHits()) {
			WebSource web = new WebSource();
			web.setContinent(String.valueOf(hit.getSource().get("continent")));
			web.setCountry(String.valueOf(hit.getSource().get("area")));
			web.setFullName(String.valueOf(hit.getSource().get("full_name")));
			web.setHomePage(String.valueOf(hit.getSource().get("home_page")));
			web.setLanguage(String.valueOf(hit.getSource().get("language")));
			web.setLogo(String.valueOf(hit.getSource().get("icon")));
			web.setMediaType(String.valueOf(hit.getSource().get("media_type")));
			web.setProvince(String.valueOf(hit.getSource().get("province")));
			web.setSourceId(Long.valueOf(hit.getId()));
			list.add(web);
		}
		return list;
	}
}
