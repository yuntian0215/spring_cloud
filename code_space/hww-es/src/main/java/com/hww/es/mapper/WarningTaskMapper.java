package com.hww.es.mapper;

import java.util.List;
import java.util.Map;
import com.hww.es.pojo.warning.WarningTask;

public interface WarningTaskMapper {

	public void insert(WarningTask task);

	public void insertWebType(Map<String, Object> map);

	public void insertUser(Map<String, Object> map);

	public void delete(Long taskId);

	public void deleteWebType(Long taskId);

	public void deleteUser(Long taskId);

	public List<WarningTask> selectListByCompanyId(Long companyId);

	public WarningTask selectById(Long id);

	public void update(WarningTask task);

	public void changeStatus(WarningTask task);

	public void deleteWarningWebTypeByWebTypeId(Long typeId);
}
