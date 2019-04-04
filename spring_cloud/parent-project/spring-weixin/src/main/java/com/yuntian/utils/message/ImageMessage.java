package com.yuntian.utils.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * <p>图片消息</p>
 * 2019年4月3日下午3:01:53
 * @author lvjie
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage{
	// 图片链接  
    private String PicUrl;
    //图片消息媒体id
    private String MediaId;
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
