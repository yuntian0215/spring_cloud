package com.hww.es.util.ftpUtil;

import lombok.Data;

import java.util.List;

/**
 * <p>对应模版实体</p>
 * 2018年11月26日上午9:37:26
 * @author lvjie
 */
@Data
public class TemplateBO {

    private String templateName;

    private String freeMarkerUrl;

    private String ITEXTUrl;

    private String JFreeChartUrl;

    private List<String> scores;

    private String imageUrl;

    private String picUrl;

    private String scatterUrl;


}
