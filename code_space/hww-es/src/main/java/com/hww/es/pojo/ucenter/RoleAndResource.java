package com.hww.es.pojo.ucenter;

import java.io.Serializable;

import lombok.Data;
@Data
public class RoleAndResource implements Serializable{/**
	 * <p>Title</p>
	 * <p>Description</p>
	 * 2018年10月26日下午5:28:03
	 * @author lvjie
	 */
	private static final long serialVersionUID = 1L;
	private Integer parentId;
	private Integer roleId;

}
