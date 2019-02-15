package com.hww.es.service.impl.warning;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hww.es.mapper.WarningHistroyMapper;
import com.hww.es.pojo.warning.WarningHistroy;
import com.hww.es.service.warning.WarningHistroyService;

@Service
public class WarningHistroyServiceImpl implements WarningHistroyService {

	@Autowired
	WarningHistroyMapper warningHistroyMapper;
	
	@Override
	public List<WarningHistroy> getList(Long taskId) {
		return warningHistroyMapper.getList(taskId);
	}

	@Override
	public void deleteByTaskId(Long taskId) {
		warningHistroyMapper.deleteByTaskId(taskId);
	}

}
