package com.yuntian.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuntian.utils.CheckUtil;
import com.yuntian.utils.WeixinUtil;
import com.yuntian.utils.message.Article;
import com.yuntian.utils.message.MessageUtil;
import com.yuntian.utils.message.NewsMessage;
import com.yuntian.utils.message.TextMeaasge;
import com.yuntian.utils.message.WeiXinUserInfo;
/**
 * <p>微信接入接口</p>
 * 2019年4月2日下午2:42:17
 * @author lvjie
 */
@RestController
@RequestMapping("/weixin")
public class WeixinRunController {
    @Value("${weixin.appid}")
    private String appID;
    @Value("${weixin.appsecret}")
    private String appsecret;
	/**
	 * <p>验证微信接入是否合法，也就是微信平台上URL地址</p>
	 * @param request
	 * @param response
	 * @throws IOException
	 * 2019年4月3日下午3:47:24
	 * @author lvjie
	 */
	@RequestMapping("/init")
	public void init(HttpServletRequest request, HttpServletResponse response) throws IOException{
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		if(isGet){
			String signature = request.getParameter("signature");// 微信加密签名
	        String timestamp = request.getParameter("timestamp");// 时间戳
	        String nonce = request.getParameter("nonce");       // 随机数
	        //成为开发者验证
	        String echostr = request.getParameter("echostr");
	        PrintWriter out = response.getWriter();
	        if(CheckUtil.checkSignature(signature, timestamp, nonce)){
	        	//校验成功返回接收的字符串
	        	out.print(echostr);
	        }
		}else{
			try {  
                // 接收消息并返回消息  
                postMsg(request, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
	/**
	 * <p>当接入成功后，用户在平台上发送消息，根据消息处理返回信息</p>
	 * @param request
	 * @param response
	 * @throws IOException
	 * 2019年4月3日下午3:48:15
	 * @author lvjie
	 */
	public void postMsg(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            
            TextMeaasge text = new TextMeaasge();
            // 发送和回复是反向的
            text.setToUserName(fromUserName);
            text.setFromUserName(toUserName);
            text.setCreateTime(new Date().getTime());
            text.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
            
        	//获取用户信息
            WeiXinUserInfo weiXinUserInfo = WeixinUtil.getUserInfo(WeixinUtil.getAccessToken(appID, appsecret).getAccessToken(),fromUserName);
            System.out.println("该用户的openId："+weiXinUserInfo.getOpenid());
            
            //当检测到用户输入信息时，当没有对应的处理时给他返回的信息
            text.setContent("你输入的是："+content+";没有对应的处理选项，请重新输入！");
            //处理汉字返回乱码问题
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String respMessage = MessageUtil.messageToXml(text);
            
            // 处理文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                // 创建图文消息  
                NewsMessage newsMessage = new NewsMessage();
                newsMessage.setToUserName(fromUserName);
                newsMessage.setFromUserName(toUserName);
                newsMessage.setCreateTime(new Date().getTime());
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                
                List<Article> articleList = new ArrayList<Article>();
                
                // 指定消息回复  
                if ("1".equals(content)) {
                    text.setContent("今天的天气真不错！");
                    respMessage = MessageUtil.messageToXml(text);
                }
                //单图文消息
                else if("2".equals(content)){
                    Article article = new Article();
                    article.setTitle("微信公众帐号开发教程Java版");
                    article.setDescription("第一张图片");
                    article.setPicUrl("http://pic.qiantucdn.com/58pic/26/10/40/58c04e46c2ffa_1024.jpg!/fw/780/watermark/url/L3dhdGVybWFyay12MS4zLnBuZw==/align/center");
                    article.setUrl("http://www.cnblogs.com/x-99/");
                    articleList.add(article);
                    // 设置图文消息个数 
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合 
                    newsMessage.setArticles(articleList);
                    // 将图文消息对象转换成xml字符串 
                    respMessage = MessageUtil.messageToXml(newsMessage);
                }
                //多图文消息
                else if("3".equals(content)){
                    Article article1 = new Article();
                    article1.setTitle("微信公众帐号开发教程Java版");
                    article1.setDescription("");
                    article1.setPicUrl("http://pic.qiantucdn.com/58pic/26/10/40/58c04e46c2ffa_1024.jpg!/fw/780/watermark/url/L3dhdGVybWFyay12MS4zLnBuZw==/align/center");
                    article1.setUrl("http://www.cnblogs.com/x-99/");
                    Article article2 = new Article();
                    article2.setTitle("微信公众帐号开发教程.NET版");
                    article2.setDescription("");
                    article2.setPicUrl("http://pic.qiantucdn.com/58pic/26/10/40/58c04e46c2ffa_1024.jpg!/fw/780/watermark/url/L3dhdGVybWFyay12MS4zLnBuZw==/align/center");
                    article2.setUrl("http://www.cnblogs.com/x-99/");
                    Article article3 = new Article();
                    article3.setTitle("微信公众帐号开发教程C版");
                    article3.setDescription("");
                    article3.setPicUrl("http://pic.qiantucdn.com/58pic/26/10/40/58c04e46c2ffa_1024.jpg!/fw/780/watermark/url/L3dhdGVybWFyay12MS4zLnBuZw==/align/center");
                    article3.setUrl("http://www.cnblogs.com/x-99/");
                    
                    articleList.add(article1);
                    articleList.add(article2);
                    articleList.add(article3);
                    // 设置图文消息个数 
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合 
                    newsMessage.setArticles(articleList);
                    // 将图文消息对象转换成xml字符串 
                    respMessage = MessageUtil.messageToXml(newsMessage);
                }
            //设置当新用户关注时返回信息
            }else if(content==null){
            	text.setContent("感谢关注海外舆情公众号，你的建议是我努力的方向！");
                respMessage = MessageUtil.messageToXml(text);
            }
            response.getWriter().write(respMessage);// 将回应发送给微信服务器  
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
