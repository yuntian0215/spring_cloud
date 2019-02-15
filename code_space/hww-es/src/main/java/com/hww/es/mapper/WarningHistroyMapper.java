package com.hww.es.mapper;

import java.util.List;

import com.hww.es.pojo.warning.WarningHistroy;

public interface WarningHistroyMapper {

	public List<WarningHistroy> getList(Long taskId);

	public void deleteByTaskId(Long taskId);
}
