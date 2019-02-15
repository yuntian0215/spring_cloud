package com.hww.es.service.impl.es;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hww.es.mapper.QueryParamMapper;
import com.hww.es.mapper.QueryParamRemoveMapper;
import com.hww.es.mapper.QueryParamShouldMapper;
import com.hww.es.pojo.es.EsNewsQueryParam;
import com.hww.es.pojo.es.EsNewsQueryParamShould;
import com.hww.es.service.es.QueryParamService;
/**
 * <p>新闻搜索实现</p>
 * <p>Description</p>
 * 2018年11月7日上午9:45:32
 * @author lvjie
 */
@Service
public class QueryParamServiceImpl implements QueryParamService {

	
	@Autowired
	QueryParamMapper queryParamMapper;
	@Autowired
	QueryParamShouldMapper queryParamShouldMapper;
	@Autowired
	QueryParamRemoveMapper queryParamRemoveMapper;
	
	@Override
	public Long add(EsNewsQueryParam param) {
		queryParamMapper.insert(param);//插入新建任务
		Long paramId = param.getId();
		if(param.getShouldsList() != null) {
			List<EsNewsQueryParamShould> shouldsList = param.getShouldsList();
			for(EsNewsQueryParamShould shoulds:shouldsList) {
				shoulds.setNewsQueryParamId(paramId);
				queryParamShouldMapper.insert(shoulds);
			}
		}
		if(param.getWebTypeIdList() != null) {
			List<Long> webTypeIdList = param.getWebTypeIdList();
			for(Long typeId : webTypeIdList) {
				Map<String,Object> map = new HashMap<String,Object>(); 
				map.put("webTypeId", typeId);
				map.put("paramId", paramId);
				queryParamMapper.insertWebType(map);
			}
		}
		return paramId;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		queryParamMapper.delete(id);
		queryParamShouldMapper.deleteByQueryParamId(id);
		queryParamRemoveMapper.deleteByQueryParamId(id);
		
	}

	@Override
	public EsNewsQueryParam getById(Long id) {
		EsNewsQueryParam param = queryParamMapper.selectById(id);
		return param;
	}

	@Override
	public List<EsNewsQueryParam> getListByCompanyId(Long companyId) {
		return queryParamMapper.selectListByCompanyId(companyId);
	}

	@Override
	public void deleteQueryWebTypeByWebTypeId(Long webTypeId) {
		queryParamMapper.deleteQueryWebTypeByWebTypeId(webTypeId);
	}

	@Override
	public void deleteWebTypeByParamId(Long paramId) {
		// TODO Auto-generated method stub
		queryParamMapper.deleteWebTypeByParamId(paramId);
	}

	@Override
	public void update(EsNewsQueryParam param) {
		queryParamMapper.update(param);
		queryParamShouldMapper.deleteByQueryParamId(param.getId());
		if(param.getShouldsList() != null) {
			List<EsNewsQueryParamShould> shouldsList = param.getShouldsList();
			for(EsNewsQueryParamShould shoulds:shouldsList) {
				shoulds.setNewsQueryParamId(param.getId());
				queryParamShouldMapper.insert(shoulds);
			}
		}
		queryParamMapper.deleteWebTypeByParamId(param.getId());
		if(param.getWebTypeIdList() != null) {
			List<Long> webTypeIdList = param.getWebTypeIdList();
			for(Long typeId : webTypeIdList) {
				Map<String,Object> map = new HashMap<String,Object>(); 
				map.put("webTypeId", typeId);
				map.put("paramId", param.getId());
				queryParamMapper.insertWebType(map);
			}
		}
	}

}
