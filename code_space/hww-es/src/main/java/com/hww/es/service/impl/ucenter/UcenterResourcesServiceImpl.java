package com.hww.es.service.impl.ucenter;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hww.es.mapper.UcenterResourcesMapper;
import com.hww.es.mapper.UcenterRoleResourcesMapper;
import com.hww.es.pojo.ucenter.UcenterResources;
import com.hww.es.service.ucenter.IUcenterResourcesService;
/**
 * <p>资源信息</p>
 * <p>Description</p>
 * 2018年10月12日下午4:17:52
 * @author lvjie
 */
@Service
public class UcenterResourcesServiceImpl implements IUcenterResourcesService{
	@Resource
	private UcenterResourcesMapper resourcesMapper;
	@Resource
	private UcenterRoleResourcesMapper roleResourcesMapper;

	@Override
	public List<UcenterResources> findList() {
		List<UcenterResources> list = resourcesMapper.findSelect();
		if(list!=null){
			for(int i=0;i<list.size();i++){
				List<UcenterResources> prolist = resourcesMapper.findListByPraentId(list.get(i).getId());
				list.get(i).setList(prolist);
			}
		}
		return list;
	}

	@Override
	public UcenterResources findResourcesById(int id) {
		UcenterResources resources = resourcesMapper.selectByPrimaryKey(id);
		return resources;
	}

	@Override
	public int updateResourcesById(UcenterResources resources) {
		resources.setUpdateTime(new Date());
		int status = resourcesMapper.updateByPrimaryKeySelective(resources);
		return status;
	}

	@Override
	public int insertResources(UcenterResources resources) {
		resources.setState(1);//0未启用，1启用，2禁用
		resources.setCreateTime(new Date());
		resources.setUpdateTime(new Date());
		int status = resourcesMapper.insert(resources);
		return status;
	}

	@Override
	public int deleteResources(int id) {
		roleResourcesMapper.deleteByResourcesId(id);//删除中间表该id的资源
		resourcesMapper.deleteByParentId(id);//删除下面的子集
		int status = resourcesMapper.deleteByPrimaryKey(id);
		return status;
	}

	@Override
	public List<UcenterResources> findSelect() {
		List<UcenterResources> list = resourcesMapper.findSelect();
		return list;
	}
}
