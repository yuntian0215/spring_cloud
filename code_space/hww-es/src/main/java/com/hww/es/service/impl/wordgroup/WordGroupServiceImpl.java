package com.hww.es.service.impl.wordgroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hww.es.mapper.WordGroupMapper;
import com.hww.es.pojo.wordgroup.WordGroup;
import com.hww.es.service.wordgroup.WordGroupService;

@Service
public class WordGroupServiceImpl implements WordGroupService {

	@Autowired
	WordGroupMapper wordGroupMapper;
	
	@Override
	public void add(WordGroup wordGroup) {
		// TODO Auto-generated method stub
		wordGroupMapper.insert(wordGroup);
	}

	@Override
	public void update(WordGroup wordGroup) {
		// TODO Auto-generated method stub
		wordGroupMapper.update(wordGroup);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		wordGroupMapper.delete(id);
	}

	@Override
	public WordGroup getById(Long id) {
		// TODO Auto-generated method stub
		return wordGroupMapper.selectById(id);
	}

	@Override
	public List<WordGroup> getListByCompanyId(Long companyId) {
		// TODO Auto-generated method stub
		return wordGroupMapper.selectListByCompanyId(companyId);
	}

}
