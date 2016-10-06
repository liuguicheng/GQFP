package com.plugins.pending.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *        @hibernate.class
 *         table="fd_recipient"
 *
 */
public class PendingItemRecipients implements Serializable {

    /**
     * 对象id
     */
    private static final long serialVersionUID = 5697897370552532785L;

    /** not nullable persistent field */
    private String recipientId;

    /** not nullable persistent field */
    private String forDoId;

    /**
     *
     */
    private PendingItem forDoc;

    /** nullable persistent field */
    private Date readTime;

    /** nullable persistent field */
    private String status;

    /** default constructor */
    public PendingItemRecipients() {
    }

    /**
     * @return 返回 forDoId。
     */
    public String getForDoId() {
        return forDoId;
    }


    /**
     * @param fordoId 要设置的 forDoId。
     */
    public void setForDoId(String fordoId) {
        this.forDoId = fordoId;
    }

    /**
     * @return 返回 readTime。
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * @param readTime 要设置的 readTime。
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     * @return 返回 recipientId。
     */
    public String getRecipientId() {
        return recipientId;
    }

    /**
     * @param recipientId 要设置的 recipientId。
     */
    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
            .append("recipientId", this.getRecipientId()).append("forDoId", this.getForDoId())
            .toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof PendingItemRecipients)) {
            return false;
        }
        PendingItemRecipients castOther = (PendingItemRecipients) other;
        return new EqualsBuilder()
            .append(this.getRecipientId(), castOther.getRecipientId())
            .append(this.getForDoId(), castOther.getForDoId())
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getRecipientId())
            .append(getForDoId())
            .toHashCode();
    }

    /**
     * @return 返回 forDoc。
     */
    public PendingItem getForDoc() {
        return forDoc;
    }

    /**
     * @param forDoc 要设置的 forDoc。
     */
    public void setForDoc(PendingItem forDoc) {
        this.forDoc = forDoc;
    }

}
