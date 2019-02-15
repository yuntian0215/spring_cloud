package com.hww.es.service.ucenter;

import java.util.List;

import com.hww.es.pojo.ucenter.UcenterRole;

/**
 * <p>角色信息</p>
 * <p>Description</p>
 * 2018年10月12日
 * @author lvjie
 */
public interface IUcenterRoleService {
	public List<UcenterRole> findList();
	
	public UcenterRole findRoleById(int id);
	
	public int updateRoleById(UcenterRole role);
	
	public int insertRole(UcenterRole role);
	
	public int deleteRole(int id);
	
	public UcenterRole findRoleAndResourcesByRoleId(int id);
}
