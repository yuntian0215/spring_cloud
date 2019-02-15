package com.hww.es.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.es.pojo.warning.WarningHistroy;
import com.hww.es.service.warning.WarningHistroyService;
import com.hww.es.util.R;

@RestController
@RequestMapping("/warningHistroy")
public class WarningHistroyController{

	@Autowired
	WarningHistroyService warningHistroyService;
	
	@RequestMapping("/listPage")
	@ResponseBody
	public R add(Integer pageNum, Integer pageSize,Long taskId) throws Exception {
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			PageHelper.startPage(pageNum,pageSize);
			List<WarningHistroy> list = warningHistroyService.getList(taskId);
			PageInfo<WarningHistroy> page = new PageInfo<WarningHistroy>(list);
			data.put("page", page);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
