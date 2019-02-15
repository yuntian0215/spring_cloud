package com.hww.es.pojo.ucenter;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserPass implements Serializable{
	/**
	 * <p>Title</p>
	 * <p>Description</p>
	 * 2018年10月30日上午9:19:54
	 * @author lvjie
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//用户id
	private String password;//旧密码
	private String password1;//新密码
	private Date updateTime;//更新日期
}
