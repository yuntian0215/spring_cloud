package com.hww.es.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.es.pojo.ucenter.UcenterUser;
import com.hww.es.service.ucenter.UCenterUserService;
import com.hww.es.util.R;
/**
 * <p>用户基本信息</p>
 * <p>Description</p>
 * 2018年10月12日
 * @author lvjie
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UCenterUserService userService;
	/**
	 * <p>根据关键词分页查询所有用户</p>
	 * <p>Description</p>
	 * @param keyword
	 * @param pageNum 当前页号
	 * @param pageSize 每页显示几条
	 * @return
	 * 2018年10月29日上午10:16:03
	 * @author lvjie
	 */
	@RequestMapping("/findList")
	@ResponseBody
	public R findList(String keyword,Integer roleId,Integer pageNum, Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<UcenterUser> list = userService.findList(keyword,roleId);
		PageInfo<UcenterUser> info = new PageInfo<UcenterUser>(list);
 		return R.ok().put("data", info);
	}
	/**
	 * 根据id获取用户信息
	 * @return
	 * 2018年10月11日上午11:00:11
	 */
	@RequestMapping("/findUserById")
	@ResponseBody
	public R findUserById(Integer id){
		UcenterUser user = userService.selectByPrimaryKey(id);
		return R.ok().put("data", user);
	}
	/**
	 * 根据id修改用户信息
	 * @param id
	 * @return
	 * 2018年10月11日上午10:54:43
	 */
	@RequestMapping("/updateUserById")
	@ResponseBody
	public R updateUserById(UcenterUser user){
		int status = userService.updateUserById(user);
		return R.ok().put("data", status);
	}
	/**
	 * 根据id修改分组
	 * @param id
	 * @param roleId
	 * @return
	 * 2018年10月11日上午11:06:12
	 */
	@RequestMapping("/updateRoleById")
	@ResponseBody
	public R updateRoleById(Integer id,Integer roleId){
		int status = userService.updateRoleById(id,roleId);
		return R.ok().put("data", status);
	}
	/**
	 * 添加新用户
	 * @param user
	 * @return
	 * 2018年10月11日下午2:26:46
	 */
	@RequestMapping("/insertUser")
	@ResponseBody
	public R insertUser(UcenterUser user){
		int status = userService.insertUser(user);
		return R.ok().put("data", status);
	}
	/**
	 * <p>根据id删除数据</p>
	 * <p>Description</p>
	 * @param id
	 * @return
	 * 2018年10月16日下午2:15:52
	 * @author lvjie
	 */
	@RequestMapping("/deleteUserById")
	@ResponseBody
	public R deleteUserById(Integer id){
		int status = userService.deleteUserById(id);
		return R.ok().put("data", status);
	}
	/**
	 * <p>Title</p>
	 * @param id 用户id
	 * @param password 旧密码
	 * @param password1 新密码
	 * @return
	 * 2018年10月30日上午8:55:07
	 * @author lvjie
	 */
	@RequestMapping("/updatePassById")
	@ResponseBody
	public R updatePassById(Integer id,String password,String password1){
		int status = userService.updatePassById(id,password,password1);
		return R.ok().put("data", status);
	}
	
	@RequestMapping("/getCompanyUser")
	@ResponseBody
	public R getCompanyUser(Long companyId) throws Exception {
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			List<UcenterUser> list = userService.getCompanyUser(companyId);
			data.put("list", list);
			return R.ok().put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	/**
	 * <p>根据用户名查询用户是否存在</p>
	 * @param userName
	 * @return
	 * 2018年11月16日下午2:42:54
	 * @author lvjie
	 */
	@RequestMapping("/findUserByName")
	@ResponseBody
	public R findUserByName(String userName){
		int status = userService.findUserByName(userName);
		return R.ok().put("data", status);
	}
}
