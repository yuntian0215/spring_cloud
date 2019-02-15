package com.hww.es.service.impl.ucenter;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hww.es.mapper.UcenterCompanyMapper;
import com.hww.es.mapper.UcenterRoleMapper;
import com.hww.es.mapper.UcenterUserMapper;
import com.hww.es.pojo.ucenter.UCenterCompany;
import com.hww.es.pojo.ucenter.UcenterRole;
import com.hww.es.pojo.ucenter.UcenterUser;
import com.hww.es.pojo.ucenter.UCenterUserDTO;
import com.hww.es.pojo.ucenter.UserPass;
import com.hww.es.service.ucenter.UCenterUserService;
import com.hww.es.util.MD5;

@Service
public class UCenterUserServiceImpl implements UCenterUserService {

	@Autowired
	UcenterUserMapper userMapper;
	@Resource
	private UcenterRoleMapper roleMapper;
	@Resource
	private UcenterCompanyMapper companyMapper;
	@Value("${password}")
	private String password;//获取默认密码
	
	@Override
	public UCenterUserDTO getUser(UcenterUser user) {
		return userMapper.selectUser(user);
	}

	@Override
	public UcenterUser selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UcenterUser> findList(String keyword,Integer roleId) {
		UcenterUser usr = new UcenterUser();
		usr.setUserName(keyword);
		usr.setRoleId(roleId);
		List<UcenterUser> list = userMapper.selectList(usr);
		if(list!=null){
			for(int i=0;i<list.size();i++){
				UcenterUser user = list.get(i);
				if(user.getRoleId()!=null){
					UcenterRole role = roleMapper.selectByPrimaryKey(user.getRoleId());
					if(role!=null){
						user.setRole(role);
					}else{
						user.setRole(new UcenterRole());
					}
				}else{
					user.setRole(new UcenterRole());
				}
				if(user.getCompanyId()!=null){
					UCenterCompany company = companyMapper.selectByPrimaryKey(user.getCompanyId());
					if(company!=null){
						user.setCompany(company);
					}else{
						user.setCompany(new UCenterCompany());
					}
				}else{
					user.setCompany(new UCenterCompany());
				}
			}
		}
		return list;
	}

	@Override
	public int updateUserById(UcenterUser user) {
		user.setUpdateTime(new Date());
		int status = userMapper.updateByPrimaryKeySelective(user);
		return status;
	}

	@Override
	public int updateRoleById(int id, int roleId) {
		UcenterUser user = new UcenterUser();
		user.setId(id);
		user.setRoleId(roleId);
		user.setUpdateTime(new Date());
		int status = userMapper.updateByPrimaryKeySelective(user);
		return status;
	}

	@Override
	public int insertUser(UcenterUser user) {
		try {
			String newPs = MD5.md5(password);
			user.setPassword(newPs);
			user.setState(1);//0未启用，1启用，2禁用
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int status = userMapper.insert(user);
		return status;
	}

	@Override
	public int deleteUserById(int id) {
		int status = userMapper.deleteByPrimaryKey(id);
		return status;
	}

	@Override
	public UcenterUser getUcenterUser(UcenterUser user) {
		UcenterUser cuser = userMapper.getUcenterUser(user);
		if(cuser.getRoleId()!=null){
			UcenterRole role = roleMapper.selectByPrimaryKey(cuser.getRoleId());
			cuser.setRole(role);
		}else{
			cuser.setRole(null);
		}
		return cuser;
	}
	
	@Override
	public int updatePassById(Integer id, String password, String password1) {
		int status = 0;
		try {
			String pass = MD5.md5(password);//旧密码
			String newPass = MD5.md5(password1);//新密码
			UserPass upass = new UserPass();
			upass.setId(id);
			upass.setPassword(pass);
			upass.setPassword1(newPass);
			upass.setUpdateTime(new Date());
			status = userMapper.updatePassById(upass);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<UcenterUser> getCompanyUser(Long companyId) {
		// TODO Auto-generated method stub
		return userMapper.selectCompanyUser(companyId);
	}
	/**
	 * <p>根据用户名查询用户是否存在</p>
	 * @param userName
	 * @return
	 * 2018年11月16日下午2:44:03
	 * @author lvjie
	 */
	@Override
	public int findUserByName(String userName) {
		int status = userMapper.findUserByName(userName);
		return status;
	}

}
