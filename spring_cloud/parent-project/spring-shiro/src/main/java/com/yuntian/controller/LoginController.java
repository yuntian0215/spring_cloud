package com.yuntian.controller;

import com.alibaba.fastjson.JSONObject;
import com.yuntian.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>登陆验证模块</p>
 * 2019/3/8/0008 14:10
 *
 * @author lvjie
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    /**
    *@Description ajax方式登陆
    *@Param [userName, password]
    *@Return com.yuntian.utils.R
    *@Author lvjie
    *@Date 2019/3/8/0008 17:04
    */
    @RequestMapping("/ajaxLogin")
    public R ajaxLogin(String userName,String password){
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());//返回token，下次访问必须带上token值
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok().put("data",jsonObject);
    }
    /**
    *@Description  退出登陆 记得退出也要带上token
    *@Param []
    *@Return com.yuntian.utils.R
    *@Author lvjie
    *@Date 2019/3/11/0011 13:53
    */
    @RequestMapping("/logout")
    public R logout(){
        JSONObject jsonObject = new JSONObject();
        SecurityUtils.getSubject().logout();
        jsonObject.put("msg", "退出成功");
        return R.ok().put("data",jsonObject);
    }
    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }
}
