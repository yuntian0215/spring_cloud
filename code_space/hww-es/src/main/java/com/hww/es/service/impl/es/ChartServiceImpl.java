package com.hww.es.service.impl.es;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.hww.es.service.es.ChartService;

@Service
public class ChartServiceImpl implements ChartService {

	@Autowired
	private Client client;
	@Autowired  
    private Environment env;

//	@Override
//	public List<Map<String, Object>> getHotMediaList(EsNewsDTO bean) {
//		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean");
//		TermsAggregationBuilder termsBuilder = AggregationBuilders.terms("sourceaggr").field("source").size(7).order(Terms.Order.count(false));
//    	searchRequestBuilder.addAggregation(termsBuilder);
//    	
//		BoolQueryBuilder builder = null;
//		builder = QueryBuilders.boolQuery();
//		builder.should(QueryBuilders.matchAllQuery());
//		
//		if (bean.getStartTime() != null && bean.getEndTime() == null) {//只设置开始时间
//			builder.filter(QueryBuilders.rangeQuery("publish_time").format("yyyy-MM-dd HH:mm:ss").gte(bean.getStartTime()));
//		}
//		if (bean.getStartTime() != null && bean.getEndTime() != null) {//同时设置开始时间和结束时间
//			builder.filter(QueryBuilders.rangeQuery("publish_time").format("yyyy-MM-dd HH:mm:ss").gte(bean.getStartTime()).lte(bean.getEndTime()));
//		}
//		
//		searchRequestBuilder.setQuery(builder).setSize(0);
//    	SearchResponse sr = searchRequestBuilder.execute().actionGet();
//    	Map<String, Aggregation> aggMap = sr.getAggregations().asMap();
//    	StringTerms terms = (StringTerms) aggMap.get("sourceaggr");
//    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
//    	Iterator<org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket> bucketIt = terms.getBuckets().iterator();
//    	while(bucketIt.hasNext()){
//        	Bucket bucket = bucketIt.next();
//        	Map<String,Object> map = new HashMap<String,Object>();
//        	map.put("key", bucket.getKeyAsString());
//        	map.put("value", bucket.getDocCount());
//        	if(!bucket.getKeyAsString().equals("unknown") && list.size() < 6) {
//        		list.add(map);
//        	}
//    	}
//		return list;
//	}

	@Override
	public Long getTotalCount(BoolQueryBuilder builder) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean")
				.setQuery(builder).setSize(0);
		SearchResponse resp = searchRequestBuilder.execute().actionGet();
		SearchHits hits = resp.getHits();
		return hits.getTotalHits();
	}

	@Override
	public List<Map<String, Object>> getFieldCount(BoolQueryBuilder builder, String field) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean");
		TermsAggregationBuilder termsBuilder = AggregationBuilders.terms(field).field(field).order(Order.count(false));
    	searchRequestBuilder.addAggregation(termsBuilder);
    	
		searchRequestBuilder.setQuery(builder).setSize(0);
		
    	SearchResponse sr = searchRequestBuilder.execute().actionGet();
    	
    	Map<String, Aggregation> aggMap = sr.getAggregations().asMap();
    	StringTerms terms = (StringTerms) aggMap.get(field);
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	Iterator<org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket> bucketIt = terms.getBuckets().iterator();
    	while(bucketIt.hasNext()){
        	Bucket bucket = bucketIt.next();
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("key", bucket.getKeyAsString());
        	map.put("value", bucket.getDocCount());
        	list.add(map);
    	}
		return list;
	}


/*	@Override
	public List<Map<String, Object>> getTimeFieldCount(BoolQueryBuilder builder, String field, String subField,
			String startDate, String endDate) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean");
		DateHistogramAggregationBuilder termsBuilder = AggregationBuilders
    			.dateHistogram(field)
    			.field("publish_date").dateHistogramInterval(DateHistogramInterval.DAY)
    			.format("yyyy-MM-dd")
    			.minDocCount(0L)
    			.extendedBounds(new ExtendedBounds(startDate, endDate));
    	TermsAggregationBuilder subTermsBuilder = AggregationBuilders.terms(subField).field(subField);
    	termsBuilder.subAggregation(subTermsBuilder);
    	searchRequestBuilder.addAggregation(termsBuilder);
    	
		searchRequestBuilder.setQuery(builder).setSize(0);
    	SearchResponse sr = searchRequestBuilder.execute().actionGet();
    	
    	Histogram aggregation = sr.getAggregations().get(field); 
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	for (Histogram.Bucket bucket : aggregation.getBuckets()) {  
    		Map<String,Object> map = new HashMap<String,Object>();
        	map.put("key", bucket.getKeyAsString().split(" ")[0]);
        	map.put("value", bucket.getDocCount());
        	List<Map<String, Object>> subList = new ArrayList<Map<String,Object>>();
        	map.put("subList",subList);
        	//StringTerms subTerms = (StringTerms) bucket.getAggregations().asMap().get(subField);
        	StringTerms subTerms = bucket.getAggregations().get(subField);
        	//Iterator<Bucket> subBucketIt = subTerms.getBuckets().iterator();
        	Iterator<org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket> subBucketIt = subTerms.getBuckets().iterator();
        	while(subBucketIt.hasNext()){
            	Bucket subBucket = subBucketIt.next();
            	Map<String,Object> subMap = new HashMap<String,Object>();
            	subMap.put("key", subBucket.getKeyAsString());
            	subMap.put("value", subBucket.getDocCount());
            	subList.add(subMap);
            }
        	list.add(map);
        }  
		return list;
	}*/

	@Override
	public List<Map<String, Object>> getFieldTimeCount(BoolQueryBuilder builder, String field, String subField,
			String subsubField, String startDate, String endDate) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean");
		TermsAggregationBuilder termsBuilder = AggregationBuilders.terms(field).field(field);
    	DateHistogramAggregationBuilder subTermsBuilder = AggregationBuilders
    			.dateHistogram(subField)
    			.field("publish_date").dateHistogramInterval(DateHistogramInterval.DAY)
    			.format("yyyy-MM-dd")
    			.minDocCount(0L)
    			.extendedBounds(new ExtendedBounds(startDate, endDate));
    	TermsAggregationBuilder subsubTermsBuilder = AggregationBuilders.terms(subsubField).field(subsubField);
    	subTermsBuilder.subAggregation(subsubTermsBuilder);
    	termsBuilder.subAggregation(subTermsBuilder);
    	searchRequestBuilder.addAggregation(termsBuilder);
		searchRequestBuilder.setQuery(builder).setSize(0);
    	SearchResponse sr = searchRequestBuilder.execute().actionGet();
    	StringTerms terms = sr.getAggregations().get(field);
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	Iterator<org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket> bucketIt = terms.getBuckets().iterator();
    	while(bucketIt.hasNext()){
        	Bucket bucket = bucketIt.next();
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("key", bucket.getKeyAsString());
        	map.put("value", bucket.getDocCount());
        	List<Map<String, Object>> subList = new ArrayList<Map<String,Object>>();
        	map.put("subList",subList);
        	Histogram aggregation = bucket.getAggregations().get(subField); 
        	for(Histogram.Bucket subBucket: aggregation.getBuckets()) {
            	Map<String,Object> subMap = new HashMap<String,Object>();
            	subMap.put("key", subBucket.getKeyAsString().split(" ")[0]);
            	subMap.put("value", subBucket.getDocCount());
            	List<Map<String, Object>> subsubList = new ArrayList<Map<String,Object>>();
            	subMap.put("subList",subsubList);
            	StringTerms subsubTerms = subBucket.getAggregations().get(subsubField);
            	Iterator<org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket> subsubBucketIt = subsubTerms.getBuckets().iterator();
            	while(subsubBucketIt.hasNext()){
            		Bucket subsubBucket = subsubBucketIt.next();
                	Map<String,Object> subsubMap = new HashMap<String,Object>();
                	subsubMap.put("key", subsubBucket.getKeyAsString());
                	subsubMap.put("value", subsubBucket.getDocCount());
                	subsubList.add(subsubMap);
            	}
            	subList.add(subMap);
        	}
        	list.add(map);
    	}
		return list;
	}

	@Override
	public List<Map<String, Object>> getFieldTimeCount(BoolQueryBuilder builder, String field, String subField,
			String startDate, String endDate) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean");
		DateHistogramAggregationBuilder subTermsBuilder = AggregationBuilders
    			.dateHistogram(subField)
    			.field("publish_date").dateHistogramInterval(DateHistogramInterval.DAY)
    			.format("yyyy-MM-dd")
    			.minDocCount(0L)
    			.extendedBounds(new ExtendedBounds(startDate, endDate));
				//.extendedBounds(new ExtendedBounds("2018-08-25" ,"2018-08-30"));
    	TermsAggregationBuilder termsBuilder = AggregationBuilders.terms(field).field(field);
    	termsBuilder.subAggregation(subTermsBuilder);
    	searchRequestBuilder.addAggregation(termsBuilder);
    	
		searchRequestBuilder.setQuery(builder).setSize(0);
    	SearchResponse sr = searchRequestBuilder.execute().actionGet();
    	
    	StringTerms aggregation = sr.getAggregations().get(field); 
    	Iterator<org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket> bucketIt = aggregation.getBuckets().iterator();
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	while(bucketIt.hasNext()){
    		Map<String,Object> map = new HashMap<String,Object>();
    		List<Map<String, Object>> subList = new ArrayList<Map<String,Object>>();
    		map.put("subList",subList);
    		Bucket bucket = bucketIt.next();
        	map.put("key", bucket.getKeyAsString());
        	map.put("value", bucket.getDocCount());
        	
        	Histogram histogram = bucket.getAggregations().get(subField);
        	
        	for (Histogram.Bucket subBucket : histogram.getBuckets()) {  
        		Map<String,Object> subMap = new HashMap<String,Object>();
        		subMap.put("key", subBucket.getKeyAsString());
        		subMap.put("value", subBucket.getDocCount());
        		subList.add(subMap);
            }
        	list.add(map);
        }
		return list;
	}

	@Override
	public List<Map<String, Object>> getTimeCount(BoolQueryBuilder builder, String startDate, String endDate) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean");
		DateHistogramAggregationBuilder termsBuilder = AggregationBuilders
    			.dateHistogram("publish_date")
    			.field("publish_date").dateHistogramInterval(DateHistogramInterval.DAY)
    			.format("yyyy-MM-dd")
    			.minDocCount(0L)
    			.extendedBounds(new ExtendedBounds(startDate, endDate));
    	searchRequestBuilder.addAggregation(termsBuilder);
		searchRequestBuilder.setQuery(builder).setSize(0);
    	SearchResponse sr = searchRequestBuilder.execute().actionGet();
    	Histogram aggregation = sr.getAggregations().get("publish_date"); 
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	for (Histogram.Bucket bucket : aggregation.getBuckets()) {  
    		Map<String,Object> map = new HashMap<String,Object>();
        	map.put("key", bucket.getKeyAsString());
        	map.put("value", bucket.getDocCount());
        	list.add(map);
        }  
		return list;
	}

	@Override
	public List<Map<String, Object>> getTotalCountHour(String startTime,String endTime) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean");
		
		BoolQueryBuilder filterQueryBuilder = new BoolQueryBuilder();//过滤用builder
		filterQueryBuilder.must(QueryBuilders.rangeQuery("insert_time").format("yyyy-MM-dd HH:mm:ss").gt(startTime).lte(endTime));
		
		DateHistogramAggregationBuilder termsBuilder = AggregationBuilders
    			.dateHistogram("insert_time")
    			.field("insert_time").dateHistogramInterval(DateHistogramInterval.HOUR)
    			.format("yyyy-MM-dd HH:mm:ss")
    			.minDocCount(0L)
    			.extendedBounds(new ExtendedBounds(startTime, endTime));
    	searchRequestBuilder.addAggregation(termsBuilder);
    	searchRequestBuilder.setQuery(filterQueryBuilder).setSize(0);
    	SearchResponse sr = searchRequestBuilder.execute().actionGet();
    	Histogram aggregation = sr.getAggregations().get("insert_time"); 
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	for (Histogram.Bucket bucket : aggregation.getBuckets()) {  
    		Map<String,Object> map = new HashMap<String,Object>();
        	map.put("key", bucket.getKeyAsString());
        	map.put("value", bucket.getDocCount());
        	list.add(map);
        }  
		return list;
	}

	@Override
	public List<Map<String, Object>> getGlobalMap(String dateRange) {
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(env.getProperty("elasticsearch.ennew.name")).setTypes("htmlbean");
		TermsAggregationBuilder termsBuilder = AggregationBuilders.terms("area").field("area").order(Order.count(false)).size(300);
    	searchRequestBuilder.addAggregation(termsBuilder);
    	
		searchRequestBuilder.setQuery(new BoolQueryBuilder().must(QueryBuilders.rangeQuery("insert_time").gte(dateRange)));
		//searchRequestBuilder.setQuery(new BoolQueryBuilder().must(QueryBuilders.rangeQuery("insert_time").format("yyyy-MM-dd").gt("2018-10-01").lte("2018-10-15")));
		
    	SearchResponse sr = searchRequestBuilder.setSize(0).execute().actionGet();
    	
    	Map<String, Aggregation> aggMap = sr.getAggregations().asMap();
    	StringTerms terms = (StringTerms) aggMap.get("area");
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	Iterator<org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket> bucketIt = terms.getBuckets().iterator();
    	while(bucketIt.hasNext()){
        	Bucket bucket = bucketIt.next();
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("key", bucket.getKeyAsString());
        	map.put("value", bucket.getDocCount());
        	list.add(map);
    	}
		return list;
	}
	
}
