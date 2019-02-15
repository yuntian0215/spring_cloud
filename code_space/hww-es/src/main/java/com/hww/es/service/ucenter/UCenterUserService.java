package com.hww.es.service.ucenter;

import java.util.List;

import com.hww.es.pojo.ucenter.UcenterUser;
import com.hww.es.pojo.ucenter.UCenterUserDTO;

public interface UCenterUserService {

	public UCenterUserDTO getUser(UcenterUser user);
	
	public UcenterUser selectByPrimaryKey(int id);
	
	public List<UcenterUser> findList(String keyword,Integer roleId);
	
	public int updateUserById(UcenterUser user);
	
	public int updateRoleById(int id,int roleId);
	
	public int insertUser(UcenterUser user);
	
	public int deleteUserById(int id);
	
	public UcenterUser getUcenterUser(UcenterUser user);
	
	public int updatePassById(Integer id,String password,String password1);

	public List<UcenterUser> getCompanyUser(Long companyId);
	
	public int findUserByName(String userName);
}
