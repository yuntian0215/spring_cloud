package com.hww.es.pojo.warning;
import java.io.Serializable;
import lombok.Data;

@Data
public class WarningHistroy implements Serializable {

	private static final long serialVersionUID = 2887685058165514493L;

	private Long id;
	private String url;
	private String title;
	private String content;
	private String source;
	private String attribute;
	private Long WarningTaskId;
	private String WarningTaskName;
	private String WarningTime;
	
}

