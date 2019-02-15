package com.hww.es.pojo.es;

import java.io.Serializable;
import lombok.Data;

@Data
public class EsNewsQueryParamRemove implements Serializable {


	private static final long serialVersionUID = 4131613747937112777L;
	
	private Long id;
	private Long companyId;
	private Long newsQueryParamId;
	private String newsId;
}
