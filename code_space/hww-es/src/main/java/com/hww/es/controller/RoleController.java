package com.hww.es.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hww.es.pojo.ucenter.UcenterRole;
import com.hww.es.pojo.ucenter.UcenterRoleResources;
import com.hww.es.service.ucenter.IUcenterRoleResourcesService;
import com.hww.es.service.ucenter.IUcenterRoleService;
import com.hww.es.util.R;
/**
 * <p>角色信息</p>
 * <p>Description</p>
 * 2018年10月12日
 * @author lvjie
 */
@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	private IUcenterRoleService roleService;
	@Resource
	private IUcenterRoleResourcesService roleResourcesService;
	/**
	 * 查询所有权限分组
	 * @return
	 * 2018年10月11日下午1:41:58
	 */
	@RequestMapping("/findList")
	@ResponseBody
	public R findList(){
		List<UcenterRole> list = roleService.findList();
		return R.ok().put("data", list);
	}
	/**
	 * 根据id获取组信息
	 * @param id
	 * @return
	 * 2018年10月11日下午1:48:11
	 */
	@RequestMapping("/findRoleById")
	@ResponseBody
	public R findRoleById(Integer id){
		UcenterRole role = roleService.findRoleById(id);
		return R.ok().put("data", role);
	}
	/**
	 * 根据id修改
	 * @param role
	 * @return
	 * 2018年10月11日下午2:20:17
	 */
	@RequestMapping("/updateRoleById")
	@ResponseBody
	public R updateRoleById(UcenterRole role){
		int status = roleService.updateRoleById(role);
		return R.ok().put("data", status);
	}
	/**
	 * 添加权限组
	 * @param role
	 * @return
	 * 2018年10月11日下午2:22:41
	 */
	@RequestMapping("/insertRole")
	@ResponseBody
	public R insertRole(UcenterRole role){
		int status = roleService.insertRole(role);
		return R.ok().put("data", status);
	}
	/**
	 * <p>删除权限组</p>
	 * <p>Description</p>
	 * @param id
	 * @return
	 * 2018年10月17日上午9:46:05
	 * @author lvjie
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public R deleteRole(Integer id){
		int status = roleService.deleteRole(id);
		return R.ok().put("data", status);
	}
	/**
	 * 添加权限组对应的资源
	 * @return
	 * 2018年10月11日下午2:47:20
	 */
	@RequestMapping("/insertRoleAndResources")
	@ResponseBody
	public R insertRoleAndResources(Integer roleId,String resourcesIds){
		int status = roleResourcesService.insertRoleAndResources(roleId,resourcesIds);
		return R.ok().put("data", status);
	}
	/**
	 * 根据权限组id获取资源
	 * @return
	 * 2018年10月11日下午2:59:11
	 */
	@RequestMapping("/fingResourcesByRoleId")
	@ResponseBody
	public R fingResourcesByRoleId(Integer roleId){
		List<UcenterRoleResources> list = roleResourcesService.fingResourcesByRoleId(roleId);
		return R.ok().put("data", list);
	}
	/**
	 * <p>根据roleId获取分组和资源</p>
	 * <p>Description</p>
	 * @param roleId
	 * @return
	 * 2018年10月23日上午9:11:38
	 * @author lvjie
	 */
	@RequestMapping("/findRoleAndResourcesByRoleId")
	@ResponseBody
	public R findRoleAndResourcesByRoleId(Integer roleId){
		UcenterRole role = roleService.findRoleAndResourcesByRoleId(roleId);
		return R.ok().put("data", role);
	}
}
