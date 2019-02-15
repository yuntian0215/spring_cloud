package com.hww.es.pojo.es;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EsNews implements Serializable{

	private static final long serialVersionUID = 2045347012676990336L;
	
	private String id;
	private String title;// 标题
	private String content;// 内容
	private String url;// 链接地址
	private String author;// 作者
	private String introduction;// 简介
	private String publishDate;// 发布日期
	private String insertTime;// 入库时间
	private String mediaType;// 媒体类型
	private String area;// 地区
	private String attribute;// 舆情属性
	private String language;// 语种
	private String source;// 来源
	private String sourceId;// 来源id
	private Float score;// 相关度分数
	private Integer readCount = 0;// 阅读数
	private Integer replyCount = 0;// 回复数
	private String origin;//是否原文
	private String simiCount;//相似文章数
	private String persons;//人名
	private String locations;//地名
	private String keywords;//关键字
	private String sourceUrl;
	private String pos;//正面分数
	private String neg;//负面分数
	private String lenContent;//字数
	private List<Map<String,Object>> simiNewsList;
	
}
