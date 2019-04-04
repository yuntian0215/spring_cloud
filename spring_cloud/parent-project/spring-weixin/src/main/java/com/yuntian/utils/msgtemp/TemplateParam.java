package com.yuntian.utils.msgtemp;
/**
 * <p>模板消息由于模板选取不同，那么就要封装俩个实体类</p>
 * 2019年4月4日上午9:08:44
 * @author lvjie
 */
public class TemplateParam {
	// 参数名称  
    private String name;  
    // 参数值  
    private String value;  
    // 颜色  
    private String color;
    public TemplateParam() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TemplateParam(String name,String value,String color){  
        this.name=name;  
        this.value=value;  
        this.color=color;  
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
