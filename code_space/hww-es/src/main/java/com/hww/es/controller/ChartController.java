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
import com.hww.es.pojo.es.EsNewsQueryParam;
import com.hww.es.service.es.ChartService;
import com.hww.es.service.es.QueryParamRemoveService;
import com.hww.es.service.es.QueryParamService;
import com.hww.es.service.es.SearchService;
import com.hww.es.service.websource.WebSourceService;
import com.hww.es.service.websource.WebTypeService;
import com.hww.es.util.MyDateUtil;
import com.hww.es.util.R;

/**
 * 
 * @author fuxuan
 *
 */
@RestController
@RequestMapping("/search")
public class ChartController {

	
	@Autowired
	private ChartService chartService;
	@Autowired
	QueryParamService queryParamService;
	@Autowired
	QueryParamRemoveService queryParamRemoveService;
	@Autowired
	WebTypeService webTypeService;
	@Autowired
	SearchService searchService;
	@Autowired
	WebSourceService webSourceService;
	/**
	 * 
	 * @param keyword 关键字
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/chart")
	@ResponseBody
	public R chart(EsNewsQueryParam param) throws Exception {
		
		try {
			List<Long> removeList = null;//排除新闻列表
			List<String> sourceIdList = null;
			List<Long> webTypeIdList = param.getWebTypeIdList();
			if(param.getId() != null) {//如果id不是null，是已经定制好的搜索集合
				param = queryParamService.getById(param.getId());//根据id查出详情
				removeList = queryParamRemoveService.getListByQueryParamId(param.getId());//查出删除列表
			}
			
			String startDate = param.getStartDate();
			String endDate = param.getEndDate();
			if(StringUtils.isNotBlank(param.getDateRange())) {
				endDate = MyDateUtil.getDate("now");
				startDate = MyDateUtil.getDate(param.getDateRange());
			}
			
			if(webTypeIdList != null && webTypeIdList.size() > 0) {
				sourceIdList = webSourceService.getSourceIdListByTypeIdList(webTypeIdList);
			}
			BoolQueryBuilder builder = searchService.getAdvancedBuilder(param, sourceIdList, removeList);
			
			Long totalCount = chartService.getTotalCount(builder);
			
			List<Map<String, Object>> sourceCount = chartService.getFieldCount(builder, "source_id");
			int size = sourceCount.size();
			if(size >10) {
				sourceCount = sourceCount.subList(0, 10);
			}
			for(Map<String,Object> map : sourceCount) {
				Long id = Long.valueOf(String.valueOf(map.get("key")));
				map.put("key", webSourceService.getFullNameById(id));
				map.put("id", id);
			}
			
			//List<Map<String, Object>> mediaTypeCount = chartService.getFieldCount(builder, "media_type");
			List<Map<String, Object>> dateMediaTypeCount = chartService.getFieldTimeCount(builder,
					"media_type", "publish_date",startDate,endDate);
			List<Map<String, Object>> areaCount = chartService.getFieldCount(builder, "area");
			//List<Map<String, Object>> attributeCount = chartService.getFieldCount(builder, "attribute");
			List<Map<String, Object>> dateAttributeCount = chartService.getFieldTimeCount(builder, "attribute",
					"publish_date",startDate,endDate);
			//List<Map<String, Object>> languageCount = chartService.getFieldCount(builder, "language");
			List<Map<String, Object>> dateLanguageCount = chartService.getFieldTimeCount(builder,
					"language", "publish_date",startDate,endDate);
			List<Map<String, Object>> dateTotalCount = chartService.getTimeCount(builder,startDate,endDate);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("dateTotalCount", dateTotalCount);
			map.put("totalCount", totalCount);
			map.put("sourceCount", sourceCount);
			//map.put("attributeCount", attributeCount);
			map.put("dateAttributeCount", dateAttributeCount);
			//map.put("mediaTypeCount", mediaTypeCount);
			map.put("dateMediaTypeCount", dateMediaTypeCount);
			map.put("areaCount", areaCount);
			//map.put("languageCount", languageCount);
			map.put("dateLanguageCount", dateLanguageCount);
			return R.ok().put("data", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return R.error();
		}
	}
}
