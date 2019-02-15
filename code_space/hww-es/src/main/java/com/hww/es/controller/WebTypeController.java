package com.hww.es.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hww.es.pojo.websource.WebType;
import com.hww.es.service.es.QueryParamService;
import com.hww.es.service.warning.WarningTaskService;
import com.hww.es.service.websource.WebTypeService;
import com.hww.es.util.R;

@RestController
@RequestMapping("/webType")
public class WebTypeController {

	@Autowired
	WebTypeService webTypeService;
	@Autowired
	QueryParamService queryParamService;
	@Autowired
	WarningTaskService warningTaskService;
	
	/**
	 * 新建站点分类
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	@ResponseBody
	public R addWebType(WebType type,Long[] sourceIdList) throws Exception {
		try {
			webTypeService.addWebType(type);
			Long typeId = type.getId();
			for(Long sourceId : sourceIdList) {
				webTypeService.addWebSourceIdType(typeId,sourceId);
			}
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("id", typeId);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 删除站点分类
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public R deleteWebType(Long typeId) throws Exception {
		try {
			webTypeService.deleteWebType(typeId);
			queryParamService.deleteQueryWebTypeByWebTypeId(typeId);
			warningTaskService.deleteWarningWebTypeByWebTypeId(typeId);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 根据公司查询站点分类列表
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getWebTypeList")
	@ResponseBody
	public R getWebTypeList(Long companyId) throws Exception {
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			List<WebType> list = webTypeService.getWebTypeList(companyId);
			data.put("list", list);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
