package com.hww.es.pojo.es;

import java.io.Serializable;
import lombok.Data;

@Data
public class EsNewsQueryParamShould implements Serializable {

	private static final long serialVersionUID = -794919351843815138L;

	private Long id;
	private String shoulds;
	private Integer minMatch;
	private Long companyId;
	private Long newsQueryParamId;
	
}
