package com.hww.es.service.impl.es;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hww.es.mapper.QueryParamRemoveMapper;
import com.hww.es.pojo.es.EsNewsQueryParamRemove;
import com.hww.es.service.es.QueryParamRemoveService;

@Service
public class QueryParamRemoveServiceImpl implements QueryParamRemoveService {
	
	@Autowired
	QueryParamRemoveMapper queryParamRemoveMapper;
	
	@Override
	public List<Long> getListByQueryParamId(Long paramId) {
		return queryParamRemoveMapper.selectListByQueryParamId(paramId);
	}

	@Override
	public void add(EsNewsQueryParamRemove remove) {
		// TODO Auto-generated method stub
		queryParamRemoveMapper.insert(remove);
	}

}
