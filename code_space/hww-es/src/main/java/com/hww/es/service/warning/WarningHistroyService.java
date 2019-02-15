package com.hww.es.service.warning;

import java.util.List;

import com.hww.es.pojo.warning.WarningHistroy;

public interface WarningHistroyService {

	public List<WarningHistroy> getList(Long taskId);

	public void deleteByTaskId(Long taskId);
}
