package com.hww.es.pojo.websource;

import java.util.Date;

public class WebSiteSource {
    private Integer id;

    private String siteEnName;

    private String siteCnName;

    private String siteUrl;

    private Integer siteStatus;

    private Date createDate;

    private Date updateDate;

    private Integer userId;

    private String noteMsg;

    private String siteDemand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteEnName() {
        return siteEnName;
    }

    public void setSiteEnName(String siteEnName) {
        this.siteEnName = siteEnName == null ? null : siteEnName.trim();
    }

    public String getSiteCnName() {
        return siteCnName;
    }

    public void setSiteCnName(String siteCnName) {
        this.siteCnName = siteCnName == null ? null : siteCnName.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public Integer getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(Integer siteStatus) {
        this.siteStatus = siteStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNoteMsg() {
        return noteMsg;
    }

    public void setNoteMsg(String noteMsg) {
        this.noteMsg = noteMsg == null ? null : noteMsg.trim();
    }

    public String getSiteDemand() {
        return siteDemand;
    }

    public void setSiteDemand(String siteDemand) {
        this.siteDemand = siteDemand == null ? null : siteDemand.trim();
    }
}