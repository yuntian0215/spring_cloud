package com.hww.es.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hww.es.pojo.wordgroup.WordGroup;
import com.hww.es.service.wordgroup.WordGroupService;
import com.hww.es.util.R;

@RestController
@RequestMapping("/wordGroup")
public class WordGroupController {

	@Autowired
	WordGroupService wordGroupService;
	
	
	@RequestMapping("/add")
	@ResponseBody
	public R add(WordGroup wordGroup) throws Exception {
		try {
			if(wordGroup.getId() == null) {
				wordGroupService.add(wordGroup);
			}else {
				wordGroupService.update(wordGroup);
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
			wordGroupService.delete(id);
			
			return R.ok();
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
			WordGroup wordGroup = wordGroupService.getById(id);
			data.put("wordGroup", wordGroup);
			return R.ok().put("data", data);
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
			List<WordGroup> list = wordGroupService.getListByCompanyId(companyId);
			data.put("list", list);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
}
