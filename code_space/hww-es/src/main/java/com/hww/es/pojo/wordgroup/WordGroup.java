package com.hww.es.pojo.wordgroup;

import java.io.Serializable;

import lombok.Data;

@Data
public class WordGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1221230188658451306L;

	private Long id;
	private Long companyId;
	private String title;
	private String word;
}
