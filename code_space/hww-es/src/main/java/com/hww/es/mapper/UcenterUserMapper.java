package com.hww.es.mapper;

import java.util.List;

import com.datastax.driver.mapping.annotations.Param;
import com.hww.es.pojo.ucenter.UCenterUserDTO;
import com.hww.es.pojo.ucenter.UcenterUser;
import com.hww.es.pojo.ucenter.UserPass;

public interface UcenterUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UcenterUser record);

    int insertSelective(UcenterUser record);

    UcenterUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UcenterUser record);

    int updateByPrimaryKey(UcenterUser record);
    
    public void delete (Long id);
	
	public UCenterUserDTO selectById(Long id);
	
	public UCenterUserDTO selectUser(UcenterUser User);
	
	List<UcenterUser> selectList(UcenterUser User);
	
	UcenterUser getUcenterUser(UcenterUser record);
	
	int updatePassById(UserPass upass);

	public List<UcenterUser> selectCompanyUser(Long companyId);
	
	public int findUserByName(@Param("userName") String userName);
}