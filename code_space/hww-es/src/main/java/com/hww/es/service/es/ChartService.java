package com.hww.es.service.es;

import java.util.List;
import java.util.Map;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface ChartService {

	//public List<Map<String, Object>> getHotMediaList(EsNewsDTO bean);

	public Long getTotalCount(BoolQueryBuilder builder);

	public List<Map<String, Object>> getFieldCount(BoolQueryBuilder builder, String field);

	public List<Map<String, Object>> getFieldTimeCount(BoolQueryBuilder builder, String field, String subField,String startDate,String endDate);
	
	public List<Map<String, Object>> getFieldTimeCount(BoolQueryBuilder builder, String field,String subField,String subsubField,String startDate,String endDate);

	public List<Map<String,Object>> getTimeCount(BoolQueryBuilder builder,String startDate,String endDate);
	
	public List<Map<String,Object>> getTotalCountHour(String startTime,String endTime);
	
	public List<Map<String,Object>> getGlobalMap(String dateRange);
	
	
}
