package com.hww.es.service.impl.ucenter;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hww.es.mapper.UcenterRoleResourcesMapper;
import com.hww.es.pojo.ucenter.UcenterRoleResources;
import com.hww.es.service.ucenter.IUcenterRoleResourcesService;
/**
 * <p>角色资源对应</p>
 * <p>Description</p>
 * 2018年10月12日下午4:19:31
 * @author lvjie
 */
@Service
public class UcenterRoleResourcesServiceImpl implements IUcenterRoleResourcesService{
	@Resource
	private UcenterRoleResourcesMapper roleResourcesMapper;

	@Override
	public int insertRoleAndResources(int roleId, String resourcesIds) {
		int status = 0;
		if(resourcesIds!=""){
			roleResourcesMapper.deleteByRoleId(roleId);//首先删除以前的
			String[] ids = resourcesIds.split(",");
			for(int i=0;i<ids.length;i++){
				UcenterRoleResources hrr = new UcenterRoleResources();
				hrr.setRoleId(roleId);
				hrr.setResourcesId(Integer.parseInt(ids[i]));
				status = roleResourcesMapper.insert(hrr);
			}
		}
		return status;
	}

	@Override
	public List<UcenterRoleResources> fingResourcesByRoleId(int roleId) {
		List<UcenterRoleResources> list = roleResourcesMapper.fingResourcesByRoleId(roleId);
		return list;
	}
}
