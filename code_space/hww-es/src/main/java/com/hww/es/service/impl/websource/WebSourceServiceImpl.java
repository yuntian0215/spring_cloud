package com.hww.es.service.impl.websource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hww.es.mapper.WebSourceMapper;
import com.hww.es.pojo.websource.WebSource;
import com.hww.es.service.websource.WebSourceService;

@Service
public class WebSourceServiceImpl implements WebSourceService {

	@Autowired
	WebSourceMapper webSourceMapper;
	
	@Override
	public WebSource getById(Long id) {
		// TODO Auto-generated method stub
		return webSourceMapper.selectById(id);
	}

	@Override
	public List<WebSource> getList(WebSource websource) {
		// TODO Auto-generated method stub
		return webSourceMapper.selectList(websource);
	}

	@Override
	public List<WebSource> getList() {
		// TODO Auto-generated method stub
		return webSourceMapper.getList();
	}

	@Override
	public List<String> getSourceIdListByTypeIdList(List<Long> webTypeIdList) {
		// TODO Auto-generated method stub
		return webSourceMapper.selectSourceIdListByTypeIdList(webTypeIdList);
	}

	@Override
	public List<WebSource> getListByTypeId(Long typeId) {
		// TODO Auto-generated method stub
		return webSourceMapper.selectListByTypeId(typeId);
	}

	@Override
	public String getFullNameById(Long id) {
		// TODO Auto-generated method stub
		return webSourceMapper.selectFullNameById(id);
	}

	@Override
	public WebSource addWebSource(WebSource webSource) {
		webSourceMapper.insert(webSource);
		return webSource;
	}



}
