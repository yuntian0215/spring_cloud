package com.yuntian.utils.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * <p>消息文本实体类</p>
 * 2019年4月3日下午2:49:14
 * @author lvjie
 */
@XStreamAlias("xml")
public class TextMeaasge extends BaseMessage{
	/**
     * 文本消息内容
     */
    private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
