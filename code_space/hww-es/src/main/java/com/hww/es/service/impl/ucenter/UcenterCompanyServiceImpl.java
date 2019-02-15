package com.hww.es.service.impl.ucenter;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hww.es.mapper.UcenterCompanyMapper;
import com.hww.es.pojo.ucenter.UCenterCompany;
import com.hww.es.service.ucenter.IUcenterCompanyService;
/**
 * <p>公司基本信息</p>
 * 2018年12月12日下午1:19:30
 * @author lvjie
 */
@Service
public class UcenterCompanyServiceImpl implements IUcenterCompanyService{
	@Resource
	private UcenterCompanyMapper companyMapper;
	@Override
	public List<UCenterCompany> findList(String keyword) {
		List<UCenterCompany> list = companyMapper.selectByKeyword(keyword);
		return list;
	}
	@Override
	public int insertCompany(UCenterCompany company) {
		company.setCreateTime(new Date());
		company.setUpdateTime(new Date());
		int status = companyMapper.insert(company);
		return status;
	}
	@Override
	public UCenterCompany findCompanyById(Integer id) {
		UCenterCompany company = companyMapper.selectByPrimaryKey(id);
		return company;
	}
	@Override
	public int updateCompanyById(UCenterCompany company) {
		company.setUpdateTime(new Date());
		int status = companyMapper.updateByPrimaryKeySelective(company);
		return status;
	}
	@Override
	public List<UCenterCompany> selectList() {
		List<UCenterCompany> list = companyMapper.selectByKeyword(null);
		return list;
	}

}
