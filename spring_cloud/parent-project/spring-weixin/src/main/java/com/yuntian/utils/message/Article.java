package com.yuntian.utils.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * <p>处理返回消息实体</p>
 * 2019年4月3日下午3:04:30
 * @author lvjie
 */
@XStreamAlias("xml")
public class Article extends BaseMessage{
	/** 
     * 消息名称 
     */  
    private String Title;  
    /** 
     * 消息描述 
     */  
    private String Description;  
  
    /** 
     * 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
     */  
    private String PicUrl;  
  
    /** 
     * 点击图文消息跳转链接 
     */  
    private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
