package com.plugins.pending.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *        @hibernate.class
 *         table="fd_recipient_bak"
 *
 */
public class PendingItemRecipientsBak implements Serializable {

    /**
     * 对象id
     */
    private static final long serialVersionUID = 5697897370552532785L;

    /** identifier field */
    private String recipientBakId;

    /** not nullable persistent field */
    private String finishedId;

    /** not nullable persistent field */
    private String recipientId;

    /** not nullable persistent field */
    private String forDoId;

    /** nullable persistent field */
    private Date readTime;

    /** nullable persistent field */
    private String status;

    /**
     *
     */
    private com.plugins.pending.entity.FinishedItem finishedItem;

   // private com.spower.basesystem.pending.valueobject.PendingItem forDoc ;

    /** default constructor */
    public PendingItemRecipientsBak() {
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
     * @return 返回 recipientBakId。
     */
    public String getRecipientBakId() {
        return recipientBakId;
    }

    /**
     * @param recipientBakId 要设置的 recipientBakId。
     */
    public void setRecipientBakId(String recipientBakId) {
        this.recipientBakId = recipientBakId;
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
            .append("recipientBakId", this.getRecipientBakId())
            .toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof PendingItemRecipientsBak)) {
            return false;
        }
        PendingItemRecipientsBak castOther = (PendingItemRecipientsBak) other;
        return new EqualsBuilder()
            .append(this.getRecipientBakId(), castOther.getRecipientBakId())
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getRecipientBakId())
            .toHashCode();
    }

    /**
     * @return 返回 finishedItem。
     */
    public com.plugins.pending.entity.FinishedItem getFinishedItem() {
        return finishedItem;
    }

    /**
     * @param finishedItem 要设置的 finishedItem。
     */
    public void setFinishedItem(
            com.plugins.pending.entity.FinishedItem finishedItem) {
        this.finishedItem = finishedItem;
    }

    /**
     * @return 返回 forDoc。
     */
//    public com.spower.basesystem.pending.valueobject.PendingItem getForDoc() {
//        return forDoc;
//    }

    /**
     * @param forDoc 要设置的 forDoc。
     */
//    public void setForDoc(com.spower.basesystem.pending.valueobject.PendingItem forDoc) {
//        this.forDoc = forDoc;
//    }

}
