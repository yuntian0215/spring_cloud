package com.hww.es.pojo.ucenter;

import java.io.Serializable;

import lombok.Data;

@Data
public class UcenterRoleResources implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer roleId;

    private Integer resourcesId;

}