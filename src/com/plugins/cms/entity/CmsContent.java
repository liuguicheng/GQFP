package com.plugins.cms.entity;

import java.util.Date;

/**
 * CmsContent entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class CmsContent implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 1709718908569854403L;

    public static final String STATUS_TMP = "0";
    public static final String STATUS_NORNAL = "2";

    private String  contentId;
    private String  channelId;
    private String   topLevel;
    private String    hasTitleImg;
    private String    isRecommend;
    private String   status;
    private Integer viewsDay;
    private Short   commentsDay;
    private Short   downloadsDay;
    private Short   upsDay;
    private String  title;
    private String  author;
    private String  origin;
    private String  originUrl;
    private String  description;
    private Date    releaseDate;
    private String  titleImg;
    private String  contentImg;
    private String  typeImg;
    private String  link;

    /**
     *内容文本，大文本独立存储，需额外加载
     */
    private String contentText;
    // Constructors

    /** default constructor */
    public CmsContent() {
    }


    // Property accessors

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTopLevel() {
        return this.topLevel;
    }

    public void setTopLevel(String topLevel) {
        this.topLevel = topLevel;
    }

    public String getHasTitleImg() {
        return this.hasTitleImg;
    }

    public void setHasTitleImg(String hasTitleImg) {
        this.hasTitleImg = hasTitleImg;
    }

    public String getIsRecommend() {
        return this.isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getViewsDay() {
        return this.viewsDay;
    }

    public void setViewsDay(Integer viewsDay) {
        this.viewsDay = viewsDay;
    }

    public Short getCommentsDay() {
        return this.commentsDay;
    }

    public void setCommentsDay(Short commentsDay) {
        this.commentsDay = commentsDay;
    }

    public Short getDownloadsDay() {
        return this.downloadsDay;
    }

    public void setDownloadsDay(Short downloadsDay) {
        this.downloadsDay = downloadsDay;
    }

    public Short getUpsDay() {
        return this.upsDay;
    }

    public void setUpsDay(Short upsDay) {
        this.upsDay = upsDay;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginUrl() {
        return this.originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitleImg() {
        return this.titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getContentImg() {
        return this.contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public String getTypeImg() {
        return this.typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the contentText
     */
    public String getContentText() {
        return contentText;
    }

    /**
     * @param contentText the contentText to set
     */
    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

}