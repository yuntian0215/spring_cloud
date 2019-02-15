package com.hww.es.service.ucenter;

import java.util.List;

import com.hww.es.pojo.ucenter.UcenterRoleResources;

/**
 * <p>角色资源对应</p>
 * <p>Description</p>
 * 2018年10月12日下午4:18:36
 * @author lvjie
 */
public interface IUcenterRoleResourcesService {
	int insertRoleAndResources(int roleId,String resourcesIds);
	
	List<UcenterRoleResources> fingResourcesByRoleId(int roleId);
}
