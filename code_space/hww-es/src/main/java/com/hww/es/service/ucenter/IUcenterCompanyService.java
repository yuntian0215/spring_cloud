package com.hww.es.service.ucenter;

import java.util.List;

import com.hww.es.pojo.ucenter.UCenterCompany;

/**
 * <p>公司基本信息</p>
 * 2018年12月12日下午1:18:30
 * @author lvjie
 */
public interface IUcenterCompanyService {
	List<UCenterCompany> findList(String keyword);
	
	int insertCompany(UCenterCompany company);
	
	UCenterCompany findCompanyById(Integer id);
	
	int updateCompanyById(UCenterCompany company);
	
	List<UCenterCompany> selectList();
}
