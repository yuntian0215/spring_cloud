package com.hww.es.pojo.ucenter;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class UcenterUser implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String userName;

    private String phoneNumber;

    private String password;

    private String email;

    private Integer companyId;

    private Date createTime;

    private Date updateTime;

    private Integer roleId;

    private Integer state;//0未启用，1启用，2禁用
    
    private UcenterRole role;//角色
    
    private UCenterCompany company;//公司

}