package com.hww.es.service.warning;

import java.util.List;

import com.hww.es.pojo.warning.WarningTask;

public interface WarningTaskService {

	
	public Long add(WarningTask task);

	public void delete(Long id);

	public List<WarningTask> getListByCompanyId(Long companyId);

	public WarningTask getById(Long id);

	public void update(WarningTask task);

	public void changeStatus(WarningTask task);

	public void deleteWarningWebTypeByWebTypeId(Long typeId);
	
}
