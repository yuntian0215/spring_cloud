package com.yuntian.time;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuntian.utils.AccessToken;
import com.yuntian.utils.WeixinUtil;
import com.yuntian.utils.msgtemp.SendMessage;
import com.yuntian.utils.msgtemp.Template;
import com.yuntian.utils.msgtemp.TemplateParam;
/**
 * <p>定时任务</p>
 * 2019年4月4日下午1:39:36
 * @author lvjie
 */
@Component
public class SendMsgTimer {
	String appID = "wx9a4adf39803ba67e";
	String appsecret = "bd916329727e6e595792897e90746067";
	/**
	 * cron = "0 0/1 * * * *" 每分钟执行一次
	 * cron = "0 0 0/1 * * *" 每小时执行一次
	 * cron = "0 10 1 * * *" 每天1点10份执行
	 */
	@Scheduled(cron = "0 0/2 * * * *")
	public void toSend(){
		//接下来给这个客户发送模板消息
		//先给模板中设置参数
		Template tem=new Template();  
		tem.setTemplateId("f_gZLdgEp-wAYQBSBEAkv76fwmjyxfbx_hC-tpgj35Y");  //模板id
		tem.setTopColor("#00DD00");  
		tem.setToUser("o-3kv1HHQculP4jSCgcU_73LJO0E");//得到用户的openid
		tem.setUrl("http://www.baidu.com");  
		List<TemplateParam> paras=new ArrayList<TemplateParam>();  
		paras.add(new TemplateParam("first","我们将给您推送一些关于您项目进度的消息:","#FF3333"));  
		paras.add(new TemplateParam("title","这是一个舆情新闻标题","#0044BB"));//标题
		paras.add(new TemplateParam("info","今天中午刘昊给吕杰打了，老惨了！","#0044BB"));//新闻简介
		paras.add(new TemplateParam("url","点击刘昊打吕杰详情...","#0044BB"));//新闻连接
		tem.setTemplateParamList(paras);  
		
		// 调用接口获取access_token
        AccessToken at = WeixinUtil.getAccessToken(appID, appsecret);
		
		boolean result=SendMessage.sendTemplateMsg(at.getAccessToken(),tem);  
		if(result==true){
			System.out.println("发送成功");
			
		}
	}
}
