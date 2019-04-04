package com.yuntian.utils.menu;
/**
 * <p>是一级菜单还是二级菜单就看你如何封装菜单</p>
 * 2019年4月3日下午4:31:29
 * @author lvjie
 */
public class CommonButton extends Button{
	private String type;
    private String key;
    private String url;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
