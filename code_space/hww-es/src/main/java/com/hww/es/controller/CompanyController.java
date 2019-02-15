package com.hww.es.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.es.pojo.ucenter.UCenterCompany;
import com.hww.es.service.ucenter.IUcenterCompanyService;
import com.hww.es.util.R;
/**
 * <p>公司基本操作</p>
 * 2018年12月12日下午1:17:24
 * @author lvjie
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
	@Resource
	private IUcenterCompanyService companyService;
	/**
	 * <p>根据关键词查询列表</p>
	 * @param keyword
	 * @param pageNum 当前页号
	 * @param pageSize 每页显示几条
	 * @return
	 * 2018年12月12日下午1:26:40
	 * @author lvjie
	 */
	@RequestMapping("/findList")
	@ResponseBody
	public R findList(String keyword,Integer pageNum, Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<UCenterCompany> list = companyService.findList(keyword);
		PageInfo<UCenterCompany> info = new PageInfo<UCenterCompany>(list);
		return R.ok().put("data", info);
	}
	/**
	 * <p>添加公司</p>
	 * @param company
	 * @return
	 * 2018年12月12日下午1:42:50
	 * @author lvjie
	 */
	@RequestMapping("/insertCompany")
	@ResponseBody
	public R insertCompany(UCenterCompany company){
		int status = companyService.insertCompany(company);
		return R.ok().put("data", status);
	}
	/**
	 * <p>按id查询</p>
	 * @param id
	 * @return
	 * 2018年12月12日下午1:48:47
	 * @author lvjie
	 */
	@RequestMapping("/findCompanyById")
	@ResponseBody
	public R findCompanyById(Integer id){
		UCenterCompany company = companyService.findCompanyById(id);
		return R.ok().put("data", company);
	}
	/**
	 * <p>编辑</p>
	 * @param company
	 * @return
	 * 2018年12月12日下午1:46:54
	 * @author lvjie
	 */
	@RequestMapping("/updateCompanyById")
	@ResponseBody
	public R updateCompanyById(UCenterCompany company){
		int status = companyService.updateCompanyById(company);
		return R.ok().put("data", status);
	}
	/**
	 * <p>查询所有公司</p>
	 * @return
	 * 2018年12月12日下午2:51:34
	 * @author lvjie
	 */
	@RequestMapping("/selectList")
	@ResponseBody
	public R selectList(){
		List<UCenterCompany> list = companyService.selectList();
		return R.ok().put("data", list);
	}
}
