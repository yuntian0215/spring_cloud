package com.hww.es.mapper;

import java.util.List;

import com.hww.es.pojo.wordgroup.WordGroup;

public interface WordGroupMapper {

	public void insert(WordGroup wordGroup);
	
	public void delete(Long id);
	
	public WordGroup selectById(Long id);

	public void update(WordGroup wordGroup);

	public List<WordGroup> selectListByCompanyId(Long companyId);
}
