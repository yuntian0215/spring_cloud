package com.hww.es.pojo.es;

import java.io.Serializable;
import java.util.List;
import com.hww.es.pojo.websource.WebType;
import lombok.Data;

@Data
public class EsNewsQueryParam implements Serializable {

	private static final long serialVersionUID = 3605284598405636171L;

	private Long id;
	private String title;
	
	private Long companyId;
	
	private String must;
	private String mustNot;
	private List<EsNewsQueryParamShould> shouldsList;
	private String searchRange;
	private String attributes;
	private String startDate;//
	private String endDate;//
	private String dateRange;//
	private String sortBy;
	private List<Long> webTypeIdList;
	private List<WebType> webTypeList;
	private Integer userId;
	
	private String mediaTypes;
	private String languages;
	
	private String ids;//选中id集合
	
}
