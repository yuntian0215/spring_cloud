package com.hww.es.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hww.es.pojo.es.EsNewsQueryParam;
import com.hww.es.pojo.warning.WarningTask;
import com.hww.es.service.es.ChartService;
import com.hww.es.service.es.QueryParamService;
import com.hww.es.service.es.SearchService;
import com.hww.es.service.warning.WarningTaskService;
import com.hww.es.util.R;

@RestController
public class HomePageController {

	@Autowired
	private ChartService chartService;
	@Autowired
	private SearchService searchService;
	@Autowired
	private QueryParamService queryParamService;
	@Autowired
	private WarningTaskService warningTaskService;
	
	@RequestMapping("/home")
	@ResponseBody
	public R home(Long companyId) {
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			
			List<Map<String, Object>> globalMap = chartService.getGlobalMap("now-30d");
			List<EsNewsQueryParam> queryList = queryParamService.getListByCompanyId(companyId);
			List<WarningTask> warningList = warningTaskService.getListByCompanyId(companyId);
			data.put("globalMap", globalMap);
			data.put("queryList", queryList);
			data.put("warningList", warningList);
			return R.ok().put("data", data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return R.error();
		}
	}
}
