package com.hww.es.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hww.es.pojo.warning.WarningTask;
import com.hww.es.service.warning.WarningHistroyService;
import com.hww.es.service.warning.WarningTaskService;
import com.hww.es.util.R;

@RestController
@RequestMapping("/warning")
public class WarningTaskController {

	@Autowired
	WarningTaskService warningTaskService;
	@Autowired
	WarningHistroyService warningHistroyService;
	
	@RequestMapping("/add")
	@ResponseBody
	public R add(WarningTask task) throws Exception {
		try {
			if(task.getId() == null) {
				warningTaskService.add(task);
			}else {
				warningTaskService.update(task);
			}
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public R delete(Long id) throws Exception {
		try {
			warningTaskService.delete(id);
			warningHistroyService.deleteByTaskId(id);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public R list(Long companyId) throws Exception {
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			List<WarningTask> list = warningTaskService.getListByCompanyId(companyId);
			data.put("list", list);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	
	@RequestMapping("/detail")
	@ResponseBody
	public R detail(Long id) throws Exception {
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			WarningTask task = warningTaskService.getById(id);
			data.put("task", task);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public R update(WarningTask task) throws Exception {
		try {
			warningTaskService.update(task);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	@RequestMapping("/changeStatus")
	@ResponseBody
	public R changeStatus(WarningTask task) throws Exception {
		try {
			warningTaskService.changeStatus(task);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
