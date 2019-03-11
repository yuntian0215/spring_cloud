package com.yuntian.config;

import com.yuntian.Resources;
import com.yuntian.Role;
import com.yuntian.User;
import com.yuntian.service.IResourcesService;
import com.yuntian.service.IRoleService;
import com.yuntian.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>自定义权限匹配和账号密码匹配</p>
 * 2019/3/8/0008 15:06
 *
 * @author lvjie
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private IResourcesService resourcesService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（Resources）
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principal instanceof User) {
            User userLogin = (User) principal;
            if(userLogin != null){
                List<Role> roleList = roleService.findRoleByUserId(userLogin.getUid());
                if(CollectionUtils.isNotEmpty(roleList)){
                    for(Role role : roleList){
                        info.addRole(role.getRoleName());

                        List<Resources> menuList = resourcesService.findResourcesByUserId(userLogin.getUid());
                        if(CollectionUtils.isNotEmpty(menuList)){
                            for (Resources menu : menuList){
                                if(!StringUtils.isEmpty(menu.getResourcesName())){
                                    info.addStringPermission(menu.getResourcesName());
                                }
                            }
                        }
                    }
                }
            }
        }
        return info;
    }
    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //获取用户的输入的账号.
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        // 从数据库获取对应用户名密码的用户
        User user = userService.findByUsername(name);
        if (user != null) {
            // 用户为禁用状态
            if (!user.getState().equals(1)) {
                throw new DisabledAccountException();
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user, //用户
                    user.getPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }
}
