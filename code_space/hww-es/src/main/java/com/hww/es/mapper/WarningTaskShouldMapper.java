package com.hww.es.mapper;

import com.hww.es.pojo.warning.WarningTaskShould;

public interface WarningTaskShouldMapper {

	public void insert(WarningTaskShould shoulds);

	public void deleteByTaskId(Long taskId);

}
