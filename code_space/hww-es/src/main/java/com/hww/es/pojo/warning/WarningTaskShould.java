package com.hww.es.pojo.warning;

import java.io.Serializable;

import com.hww.es.pojo.es.EsNewsQueryParamShould;

import lombok.Data;

@Data
public class WarningTaskShould extends EsNewsQueryParamShould implements Serializable{

	private static final long serialVersionUID = 3987229953911841311L;
	
	private Long WarningTaskId;
}
