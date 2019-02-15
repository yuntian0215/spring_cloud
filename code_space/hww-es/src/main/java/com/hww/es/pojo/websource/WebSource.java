package com.hww.es.pojo.websource;

import java.io.Serializable;

import lombok.Data;

@Data
public class WebSource implements Serializable {
	
	private static final long serialVersionUID = -8462451734422733097L;

	private Long sourceId;
	private String fullName;
	private String sourceName;
	private String homePage;
	private String logo;
	private String mediaType;
	private String language;
	private String country;
	private String continent;
	private String province;
	
}
