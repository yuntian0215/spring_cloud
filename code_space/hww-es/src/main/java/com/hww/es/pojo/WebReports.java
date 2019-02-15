package com.hww.es.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class WebReports implements Serializable{
    /**
	 * <p>Title</p>
	 * <p>Description</p>
	 * 2018年10月31日下午1:31:35
	 * @author lvjie
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String title;

    private String abstracts;

    private String authors;

    private String reportType;

    private Integer isRead;

    private Integer userId;

    private String docUrl;

    private String docType;

    private String state;

    private Date sendingTime;

    private Integer num;
    
    private String startTime;//开始时间
    
    private String endtTime;//结束时间
    
    private String userName;//上传用户

}