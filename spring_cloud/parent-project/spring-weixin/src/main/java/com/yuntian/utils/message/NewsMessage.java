package com.yuntian.utils.message;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * <p>列表图文信息</p>
 * 2019年4月3日下午3:00:47
 * @author lvjie
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage{
	/** 
     * 图文消息个数，限制为10条以内 
     */  
    private int ArticleCount;  
    
    /** 
     * 多条图文消息信息，默认第一个item为大图 
     */  
    private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}
