package com.hww.es.pojo.ucenter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class UcenterResources implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String resourcesName;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private String resourcesUrl;
    
    private Integer parentId;
    
    private Integer urlNum;
    
    private List<UcenterResources> list;

}