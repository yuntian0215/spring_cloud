package com.yuntian;

import java.io.Serializable;
import java.util.Date;

public class Resources implements Serializable {
    private Integer id;

    private String resourcesName;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private String resourcesUrl;

    private Integer parentId;

    private Integer urlNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourcesName() {
        return resourcesName;
    }

    public void setResourcesName(String resourcesName) {
        this.resourcesName = resourcesName == null ? null : resourcesName.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getResourcesUrl() {
        return resourcesUrl;
    }

    public void setResourcesUrl(String resourcesUrl) {
        this.resourcesUrl = resourcesUrl == null ? null : resourcesUrl.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUrlNum() {
        return urlNum;
    }

    public void setUrlNum(Integer urlNum) {
        this.urlNum = urlNum;
    }
}