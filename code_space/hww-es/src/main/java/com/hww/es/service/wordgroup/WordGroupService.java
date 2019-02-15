package com.hww.es.service.wordgroup;

import java.util.List;

import com.hww.es.pojo.wordgroup.WordGroup;

public interface WordGroupService {

	public void add(WordGroup wordGroup);

	public void update(WordGroup wordGroup);

	public void delete(Long id);

	public WordGroup getById(Long id);

	public List<WordGroup> getListByCompanyId(Long companyId);

}
