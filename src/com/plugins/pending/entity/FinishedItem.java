package com.plugins.pending.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.console.entity.Staff;

/**
 *        @hibernate.class
 *         table="fd_finished"
 *
 */
public class FinishedItem implements Serializable {

    /**
     * 对象id
     */
    private static final long serialVersionUID = -5403466429251523370L;

    /** identifier field */
    private String finishedId;

    /** nullable persistent field */
    private String forDoId;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String moduleName;

    /** nullable persistent field */
    private String flagId;

    /** nullable persistent field */
    private String url;

    /** nullable persistent field */
    private Staff sender;

    /** nullable persistent field */
    private String senderName;

    /** nullable persistent field */
    private Date sendTime;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private Staff finisher;

    /** nullable persistent field */
    private String finisherName;

    /** nullable persistent field */
    private Date finishTime;
    /**
     * 扩展数据存储，以json格式写入
     */
    private String jsonData;

    /** default constructor */
    public FinishedItem() {
    }

    /**
     * @return 返回 flagId。
     */
    public String getFlagId() {
        return flagId;
    }

    /**
     * @param flagId 要设置的 flagId。
     */
    public void setFlagId(String flagId) {
        this.flagId = flagId;
    }

    /**
     * @return 返回 forDoId。
     */
    public String getForDoId() {
        return forDoId;
    }

    /**
     * @param forDoId 要设置的 forDoId。
     */
    public void setForDoId(String forDoId) {
        this.forDoId = forDoId;
    }

    /**
     * @return 返回 moduleName。
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName 要设置的 moduleName。
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @return 返回 sender。
     */
    public Staff getSender() {
        return sender;
    }

    /**
     * @param sender 要设置的 sender。
     */
    public void setSender(Staff sender) {
        this.sender = sender;
    }

    /**
     * @return 返回 senderName。
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * @param senderName 要设置的 senderName。
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * @return 返回 sendTime。
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime 要设置的 sendTime。
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return 返回 status。
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 要设置的 status。
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 返回 title。
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title 要设置的 title。
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return 返回 url。
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url 要设置的 url。
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return 返回 finishedId。
     */
    public String getFinishedId() {
        return finishedId;
    }

    /**
     * @param finishedId 要设置的 finishedId。
     */
    public void setFinishedId(String finishedId) {
        this.finishedId = finishedId;
    }

    /**
     * @return 返回 finisher。
     */
    public Staff getFinisher() {
        return finisher;
    }

    /**
     * @param finisher 要设置的 finisher。
     */
    public void setFinisher(Staff finisher) {
        this.finisher = finisher;
    }

    /**
     * @return 返回 finisherName。
     */
    public String getFinisherName() {
        return finisherName;
    }

    /**
     * @param finisherName 要设置的 finisherName。
     */
    public void setFinisherName(String finisherName) {
        this.finisherName = finisherName;
    }

    /**
     * @return 返回 finishTime。
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime 要设置的 finishTime。
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
            .append("finishedId", getFinishedId())
            .toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof FinishedItem)) {
            return false;
        }
        FinishedItem castOther = (FinishedItem) other;
        return new EqualsBuilder()
            .append(this.getFinishedId(), castOther.getFinishedId())
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFinishedId())
            .toHashCode();
    }

    /**
     * @return the jsonData
     */
    public String getJsonData() {
        return jsonData;
    }

    /**
     * @param jsonData the jsonData to set
     */
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

}
