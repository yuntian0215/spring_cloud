package com.hww.es.service.impl.warning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hww.es.mapper.WarningTaskMapper;
import com.hww.es.mapper.WarningTaskShouldMapper;
import com.hww.es.pojo.warning.WarningTask;
import com.hww.es.pojo.warning.WarningTaskShould;
import com.hww.es.service.warning.WarningTaskService;

@Service
public class WarningTaskServiceImpl implements WarningTaskService {

	@Autowired
	WarningTaskMapper warningTaskMapper;
	@Autowired
	WarningTaskShouldMapper warningTaskShouldMapper;
	
	
	@Override
	public Long add(WarningTask task) {
		warningTaskMapper.insert(task);
		if(task.getShouldsList() != null) {
			for(WarningTaskShould should : task.getShouldsList()) {
				should.setWarningTaskId(task.getId());
				warningTaskShouldMapper.insert(should);
			}
		}
		if(task.getWebTypeIdList() != null) {
			List<Long> webTypeIdList = task.getWebTypeIdList();
			for(Long typeId : webTypeIdList) {
				Map<String,Object> map = new HashMap<String,Object>(); 
				map.put("webTypeId", typeId);
				map.put("taskId", task.getId());
				warningTaskMapper.insertWebType(map);
			}
		}
		if(task.getUserIdList() != null) {
			for( Long userId : task.getUserIdList()) {
				Map<String,Object> map = new HashMap<String,Object>(); 
				map.put("companyId", task.getCompanyId());
				map.put("userId", userId);
				map.put("taskId", task.getId());
				warningTaskMapper.insertUser(map);
			}
		}
		return null;
	}


	@Override
	public void delete(Long taskId) {
		warningTaskMapper.delete(taskId);
		warningTaskMapper.deleteWebType(taskId);
		warningTaskMapper.deleteUser(taskId);
		warningTaskShouldMapper.deleteByTaskId(taskId);
	}


	@Override
	public List<WarningTask> getListByCompanyId(Long companyId) {
		// TODO Auto-generated method stub
		return warningTaskMapper.selectListByCompanyId(companyId);
	}


	@Override
	public WarningTask getById(Long id) {
		// TODO Auto-generated method stub
		return warningTaskMapper.selectById(id);
	}


	@Override
	public void update(WarningTask task) {
		// TODO Auto-generated method stub
		warningTaskMapper.update(task);
		warningTaskShouldMapper.deleteByTaskId(task.getId());
		if(task.getShouldsList() != null) {
			for(WarningTaskShould should : task.getShouldsList()) {
				should.setWarningTaskId(task.getId());
				warningTaskShouldMapper.insert(should);
			}
		}
		warningTaskMapper.deleteWebType(task.getId());
		if(task.getWebTypeIdList() != null) {
			List<Long> webTypeIdList = task.getWebTypeIdList();
			for(Long typeId : webTypeIdList) {
				Map<String,Object> map = new HashMap<String,Object>(); 
				map.put("webTypeId", typeId);
				map.put("taskId", task.getId());
				warningTaskMapper.insertWebType(map);
			}
		}
		warningTaskMapper.deleteUser(task.getId());
		if(task.getUserIdList() != null) {
			for( Long userId : task.getUserIdList()) {
				Map<String,Object> map = new HashMap<String,Object>(); 
				map.put("companyId", task.getCompanyId());
				map.put("userId", userId);
				map.put("taskId", task.getId());
				warningTaskMapper.insertUser(map);
			}
		}
	}


	@Override
	public void changeStatus(WarningTask task) {
		// TODO Auto-generated method stub
		warningTaskMapper.changeStatus(task);
	}


	@Override
	public void deleteWarningWebTypeByWebTypeId(Long typeId) {
		// TODO Auto-generated method stub
		warningTaskMapper.deleteWarningWebTypeByWebTypeId(typeId);
	}
	

}
