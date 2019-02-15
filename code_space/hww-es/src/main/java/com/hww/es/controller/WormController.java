package com.hww.es.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.es.pojo.ucenter.UCenterCompany;
import com.hww.es.pojo.websource.WebSource;
import com.hww.es.service.es.EsWebSourceService;
import com.hww.es.service.websource.WebSourceService;
import com.hww.es.util.R;

@RestController
@RequestMapping("/worm")
public class WormController {

	@Autowired
	WebSourceService webSourceService;
	@Autowired
	EsWebSourceService esWebSourceService;
	
	@RequestMapping("/add")
	@ResponseBody
	public R add(WebSource webSource) throws Exception {
		try {
			webSource = webSourceService.addWebSource(webSource);
			esWebSourceService.addWebSource(webSource);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	@RequestMapping("/flushAll")
	@ResponseBody
	public R flushAll() throws Exception {
		try {
			List<WebSource> list= webSourceService.getList();
			esWebSourceService.addWebSourceList(list);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
