package com.yuntian.utils.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * <p>用户在公众号回复信息，接收到返回的消息体</p>
 * 2019年4月3日下午2:46:55
 * @author lvjie
 */
@XStreamAlias("xml")
public class BaseMessage {
	/**
     * 接收方帐号（收到的OpenID）
     */
    private String ToUserName;
    /**
     * 开发者微信号
     */
    private String FromUserName;
    /**
     * 消息创建时间 （整型）
     */
    
    private long CreateTime;

    /**
     * 消息类型
     */
    private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
