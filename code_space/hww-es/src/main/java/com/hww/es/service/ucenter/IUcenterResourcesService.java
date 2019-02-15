package com.hww.es.service.ucenter;

import java.util.List;

import com.hww.es.pojo.ucenter.UcenterResources;

/**
 * <p>资源信息</p>
 * <p>Description</p>
 * 2018年10月12日下午4:17:05
 * @author lvjie
 */
public interface IUcenterResourcesService {
	List<UcenterResources> findList();
	
	UcenterResources findResourcesById(int id);
	
	int updateResourcesById(UcenterResources resources);
	
	int insertResources(UcenterResources resources);
	
	int deleteResources(int id);
	
	List<UcenterResources> findSelect();
}
