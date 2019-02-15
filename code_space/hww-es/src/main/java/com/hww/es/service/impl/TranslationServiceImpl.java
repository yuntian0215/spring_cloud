package com.hww.es.service.impl;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hww.es.pojo.es.EsNews;
import com.hww.es.service.ITranslationService;
import com.hww.es.util.TranslateUtil;

@Service
public class TranslationServiceImpl implements ITranslationService {
	@Resource
	private Client client;
	@Autowired  
    private Environment env;
	
	public String translate(String word) {
		return TranslateUtil.result(word);
	}
	
	
	@Override
	public String translateEN(String word) {
		return TranslateUtil.resultEN(word);
	}
	
//TODO后续接着写	
//	public EsNews translateByEsID(String id) {
//		
//		QueryBuilder qb = QueryBuilders.idsQuery("htmlbean").ids(id);
//		SearchResponse resp = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean").setQuery(qb).execute().actionGet();
//		SearchHit[] hits = resp.getHits().hits();
//		if(hits.length == 1) {
//			EsNews news = new EsNews();
//		}
//		return null;
//		
//	}


}
