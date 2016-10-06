package com.plugins.pending.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.console.entity.Staff;
import com.google.gson.Gson;

/**
 *        @hibernate.class
 *         table="fd_fordo"
 *
 */
public class PendingItem implements Serializable {

    /**
     * 对象id
     */
    private static final long serialVersionUID = 905115379024794493L;

    /**
     * Gson对象，用于对jsonData在字符串和properties之间进行转换，以便存储和读取
     */
    private static final Gson gson = new Gson();

    public static final String SEPARATOR = "/";

    /** identifier field */
    private String forDoId;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String moduleName;
//    /** 应用模块类型 */
//    private String moduleType;
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

    /**
     * 扩展属性存储，以json格式写入
     */
    private String jsonData;

    /**
     * 扩展属性集
     * 写入时，以Properties对象写入，转换成json进行存储
     * 读取时，在setJsonData时反转恢复Properties对象，然后提供getProps(String proName)方法读取
     */
    private Properties props;

    /** default constructor */
    public PendingItem() {
    }

    /**
     * 设置扩展属性
     * @param p 存储扩展属性的Properties，名称和值只能是字符串
     */
    public void setProps(Properties p) {
        props = p;
        if (p != null && !p.isEmpty()) {
            this.jsonData = gson.toJson(props);
        }
    }
    /**
     * 获取扩展属性值
     * @param propertyName 属性名
     * @return 属性值字符串
     */
    public String getProperty(String propertyName) {
        if (props != null) {
            return props.getProperty(propertyName);
        }
        return "";
    }

    /**
     * 在写入jsonData值，反转生成Properties对象
     * @param jsonData the jsonData to set
     */
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
        try {
            props = gson.fromJson(jsonData, Properties.class);
        } catch (Exception ex) {
            props = null;
        }
    }

    /**
     *
     * @return 多级moduleName的最后一级
     */
    public String getSimpleName() {
        if (moduleName != null) {
            String[] s = moduleName.split(SEPARATOR);
            if (s != null && s.length > 0) {
                return s[s.length - 1];
            }
        }
        return moduleName;
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
            .append("forDoId", getForDoId())
            .toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof PendingItem)) {
            return false;
        }
        PendingItem castOther = (PendingItem) other;
        return new EqualsBuilder()
            .append(this.getForDoId(), castOther.getForDoId())
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getForDoId())
            .toHashCode();
    }

    /**
     * @return the jsonData
     */
    public String getJsonData() {
        return jsonData;
    }


}
