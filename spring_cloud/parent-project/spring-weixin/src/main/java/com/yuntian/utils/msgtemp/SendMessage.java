package com.yuntian.utils.msgtemp;

import com.alibaba.fastjson.JSONObject;
import com.yuntian.utils.WeixinUtil;

/**
 * <p>这是发送模板消息的类</p>
 * 2019年4月4日上午9:12:49
 * @author lvjie
 */
public class SendMessage {
	
	public static boolean sendTemplateMsg(String token,Template template){  
        boolean flag=false;  
        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";  
        requestUrl=requestUrl.replace("ACCESS_TOKEN", token);  
      
        JSONObject jsonResult=WeixinUtil.httpRequest(requestUrl, "POST", template.toJSON());  
        if(jsonResult!=null){  
            int errorCode=jsonResult.getIntValue("errcode");  
            String errorMessage=jsonResult.getString("errmsg");  
            if(errorCode==0){  
                flag=true;  
            }else{  
                System.out.println("模板消息发送失败:"+errorCode+","+errorMessage);  
                flag=false;  
            }  
        }  
        return flag;  
	}
}
