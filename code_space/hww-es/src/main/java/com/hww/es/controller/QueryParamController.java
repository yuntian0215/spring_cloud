package com.hww.es.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hww.es.pojo.es.EsNewsQueryParam;
import com.hww.es.service.es.QueryParamService;
import com.hww.es.util.R;

@RestController
@RequestMapping("/search")
public class QueryParamController {

	@Autowired
	QueryParamService queryParamService;
	
	
	/**
	 * 新建查询集合
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addQueryParam")
	@ResponseBody
	public R addQueryParam(EsNewsQueryParam param) throws Exception {
		try {
			if(param.getId() == null) {
				queryParamService.add(param);
			}else {
				queryParamService.update(param);
			}
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 删除查询集合
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteQueryParam")
	@ResponseBody
	public R deleteQueryParam(Long id) throws Exception {
		try {
			queryParamService.delete(id);
			queryParamService.deleteWebTypeByParamId(id);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 查询集合详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getQueryParamDetail")
	@ResponseBody
	public R getQueryParamDetail(Long id) throws Exception {
		try {
			EsNewsQueryParam queryParam = queryParamService.getById(id);
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("queryParam", queryParam);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 查询集合列表
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getQueryParamList")
	@ResponseBody
	public R getQueryParamListPage(Long companyId) throws Exception {
		try {
			
			List<EsNewsQueryParam> list = queryParamService.getListByCompanyId(companyId);
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("list", list);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
