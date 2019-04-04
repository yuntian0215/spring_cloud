package com.yuntian.utils.menu;
/**
 * <p>顶级菜单 :含有菜单子项</p>
 * 2019年4月3日下午4:32:22
 * @author lvjie
 */
public class ComplexButton extends Button{
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}
