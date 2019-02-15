package com.hww.es.pojo.ucenter;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UCenterCompany implements Serializable{
	
	private static final long serialVersionUID = 7114524026601720448L;

	private Integer id;
    private String companyName;
    
    private Date createTime;

    private Date updateTime;

}