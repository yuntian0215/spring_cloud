package com.hww.es.service.es;

import java.util.List;

import com.hww.es.pojo.es.EsNewsQueryParam;
/**
 * <p>新闻搜索接口</p>
 * <p>Description</p>
 * 2018年11月7日上午9:45:01
 * @author lvjie
 */
public interface QueryParamService {

	/**
	 * 新建查询集合
	 * @param param 
	 */
	public Long add(EsNewsQueryParam param);

	/**
	 * 删除查询集合
	 * @param id
	 */
	public void delete(Long id);

	/**
	 *查询集合详情 
	 * @param id
	 * @return
	 */
	public EsNewsQueryParam getById(Long id);

	/**
	 * 查询集合列表
	 * @param userId
	 * @return
	 */
	public List<EsNewsQueryParam> getListByCompanyId(Long companyId);
	
	public void deleteQueryWebTypeByWebTypeId(Long webTypeId);
	
	public void deleteWebTypeByParamId(Long paramId);

	public void update(EsNewsQueryParam param);
}
