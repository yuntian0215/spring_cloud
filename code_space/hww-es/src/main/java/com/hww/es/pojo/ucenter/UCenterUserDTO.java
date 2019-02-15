package com.hww.es.pojo.ucenter;

import java.io.Serializable;

import lombok.Data;

@Data
public class UCenterUserDTO extends UcenterUser implements Serializable {

	private static final long serialVersionUID = 6981053524561177619L;

	private String companyName;
	private String token;
}
