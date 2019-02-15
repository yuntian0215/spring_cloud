package com.hww.es.service.impl.ucenter;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hww.es.mapper.UcenterResourcesMapper;
import com.hww.es.mapper.UcenterRoleMapper;
import com.hww.es.mapper.UcenterRoleResourcesMapper;
import com.hww.es.pojo.ucenter.RoleAndResource;
import com.hww.es.pojo.ucenter.UcenterResources;
import com.hww.es.pojo.ucenter.UcenterRole;
import com.hww.es.service.ucenter.IUcenterRoleService;
/**
 * <p>角色信息</p>
 * <p>Description</p>
 * 2018年10月12日下午4:15:46
 * @author lvjie
 */
@Service
public class UcenterRoleServiceImpl implements IUcenterRoleService{
	@Resource
	private UcenterRoleMapper roleMapper;
	@Resource
	private UcenterRoleResourcesMapper roleResourcesMapper;
	@Resource
	private UcenterResourcesMapper resourcesMapper;

	@Override
	public List<UcenterRole> findList() {
		List<UcenterRole> list = roleMapper.findList();
		return list;
	}

	@Override
	public UcenterRole findRoleById(int id) {
		UcenterRole role = roleMapper.selectByPrimaryKey(id);
		return role;
	}

	@Override
	public int updateRoleById(UcenterRole role) {
		role.setUpdateTime(new Date());
		int status = roleMapper.updateByPrimaryKeySelective(role);
		return status;
	}

	@Override
	public int insertRole(UcenterRole role) {
		role.setState(1);//0未启用，1启用，2禁用
		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		int status = roleMapper.insert(role);
		return status;
	}

	@Override
	public int deleteRole(int id) {
		roleResourcesMapper.deleteByRoleId(id);//删除中间表含该roleId的资源
		int status = roleMapper.deleteByPrimaryKey(id);
		return status;
	}

	@Override
	public UcenterRole findRoleAndResourcesByRoleId(int id) {
		UcenterRole role = roleMapper.selectByPrimaryKey(id);//查询组信息
		List<UcenterResources> list = resourcesMapper.findListResourcesByRoleId(id);//根据roleId和中间表查询首级资源
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				UcenterResources ur = list.get(i);
				RoleAndResource rr = new RoleAndResource();
				rr.setParentId(ur.getId());
				rr.setRoleId(id);
				List<UcenterResources> lists = resourcesMapper.findListByPraentId1(rr);//根据父级查子集
				if(lists.size()>0){
					ur.setList(lists);
				}else{
					ur.setList(null);
				}
			}
			role.setResourcesList(list);
		}else{
			role.setResourcesList(null);
		}
		return role;
	}
}
