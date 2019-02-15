package com.hww.es.pojo.warning;

import java.io.Serializable;
import java.util.List;
import com.hww.es.pojo.ucenter.UcenterUser;
import com.hww.es.pojo.websource.WebType;
import lombok.Data;


@Data
public class WarningTask implements Serializable {
	
	private static final long serialVersionUID = 8718889228506347884L;

	
	private Long id;
	private String must;
	private String mustNot;
	private List<WarningTaskShould> shouldsList;
	private String attributes;
	private String title;
	private String dateRange;
	private String searchRange;
	private Long companyId;
	private Integer status;
	private Integer mail;
	private Integer sms;
	private Integer wechat;
	private Integer nightOff;

	private List<UcenterUser> userList;
	private List<WebType> webTypeList;
	private List<Long> userIdList;
	private List<Long> webTypeIdList;
	
}
