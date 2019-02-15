package com.hww.es.util.ftpUtil;

import lombok.Data;

/**
 * <p>生成折线图实体</p>
 * 2018年11月26日上午9:33:05
 * @author lvjie
 */
@Data
public class XYLine {
    private double yValue;
    private String  xValue;
    private String groupName;
    public XYLine(){

    }
    public XYLine(double yValue, String xValue, String groupName){
        this.yValue=yValue;
        this.xValue=xValue;
        this.groupName=groupName;
    }



}
