package com.hww.es.service.impl.websource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hww.es.mapper.WebTypeMapper;
import com.hww.es.pojo.websource.WebType;
import com.hww.es.service.websource.WebTypeService;

@Service
public class WebTypeServiceImpl implements WebTypeService {

	@Autowired
	WebTypeMapper webTypeMapper;
	
	@Override
	public List<String> getSourceIdListBySourceTypeIdList(List<String> typeIdList) {
		
		return webTypeMapper.selectSourceIdListBySourceTypeIdList(typeIdList);
	}

	@Override
	public void addWebType(WebType type) {
		webTypeMapper.insert(type);
	}

	@Override
	public void addWebSourceIdType(Long typeId, Long sourceId) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("typeId", typeId);
		map.put("sourceId", sourceId);
		webTypeMapper.insertSourceIdType(map);
	}

	@Override
	public void deleteWebType(Long typeId) {
		// TODO Auto-generated method stub
		webTypeMapper.delete(typeId);
		webTypeMapper.deleteSourceIdType(typeId);
	}

	@Override
	public List<WebType> getWebTypeList(Long companyId) {
		// TODO Auto-generated method stub
		return webTypeMapper.selectWebTypeListByCompanyId(companyId);
	}

}
