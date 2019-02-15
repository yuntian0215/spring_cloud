package com.hww.es.pojo.websource;

import java.io.Serializable;

import lombok.Data;

@Data
public class WebType implements Serializable {
	
	private static final long serialVersionUID = 3998787203776635660L;

	private Long id;
	private String title;
	private Long companyId;
}
