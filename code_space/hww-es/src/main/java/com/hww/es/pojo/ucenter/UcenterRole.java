package com.hww.es.pojo.ucenter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class UcenterRole implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String roleName;

    private Integer companyId;

    private Integer state;

    private Date createTime;

    private Date updateTime;
    
    private List<UcenterResources> resourcesList;

}