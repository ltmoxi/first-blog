package com.moses.blog.view;

import java.util.Date;

/**
 * @author Moses
 */
public class Article {
    private Integer id;
    private String title;
    private String content;
    private String summary;
    private String cover;
    private Integer viewCount;
    private Date updateTime;
    private Integer status;
    private Integer typeId;

    private String name;

    public Article() {

    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                ", cover='" + cover + '\'' +
                ", viewCount=" + viewCount +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", typeId=" + typeId +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Article(Integer id, String title, String content, String summary, String cover, Integer viewCount, Date updateTime, Integer status, Integer typeId, String name) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.cover = cover;
        this.viewCount = viewCount;
        this.updateTime = updateTime;
        this.status = status;
        this.typeId = typeId;
        this.name = name;
    }
}
