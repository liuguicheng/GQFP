package com.plugins.cms.entity;

import org.springline.beans.tree.support.paternity.IPaternityTreeAbility;

/**
 * CmsChannel entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class CmsChannel implements java.io.Serializable, IPaternityTreeAbility {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = -5986494468646974165L;
    /**
     * Ê÷×Öµä±êÊ¶
     */
    public static final String TREE_DIC_IDENTIFICATION = "dicCmsChannelTree";
    public static final String SIMPLE_DIC_IDENTIFICATION = "dicCmsChannel";

    private String  channelId;
    private String  parentId;
    private String  channelPath;
    private Integer sortOrder;
    private String  channelName;
    private String  isStaticChannel;
    private String  isStaticContent;
    private String  titleImg;
    private String  contentImg;
    private String  isBlank;
    private String channelStatus;

    // Constructors

    /** default constructor */
    public CmsChannel() {
    }


    // Property accessors

    /**
     * @see org.springline.beans.tree.support.paternity.IPaternityTreeAbility#isParent(org.springline.beans.tree.support.paternity.IPaternityTreeAbility)
     */
    @Override
    public boolean isParent(IPaternityTreeAbility obj) {
        CmsChannel tmp = (CmsChannel) obj;
        return (tmp != null && !equals(tmp) && tmp.getParentId() != null && tmp.getParentId().equals(this.getChannelId()));
    }


    /**
     * @see org.springline.beans.tree.support.paternity.IPaternityTreeAbility#isRoot()
     */
    @Override
    public boolean isRoot() {
        return (this.parentId == null || this.parentId.trim().length() == 0 || this.parentId.equals(this.channelId));
    }


    /**
     * @see org.springline.beans.tree.support.ITreeAbility#getNodeKey()
     */
    @Override
    public String getNodeKey() {
        return this.channelId;
    }


    /**
     * @see org.springline.beans.tree.support.ITreeAbility#getNodeName()
     */
    @Override
    public String getNodeName() {
        return this.channelName;
    }


    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChannelPath() {
        return this.channelPath;
    }

    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getIsStaticChannel() {
        return this.isStaticChannel;
    }

    public void setIsStaticChannel(String isStaticChannel) {
        this.isStaticChannel = isStaticChannel;
    }

    public String getIsStaticContent() {
        return this.isStaticContent;
    }

    public void setIsStaticContent(String isStaticContent) {
        this.isStaticContent = isStaticContent;
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

    public String getIsBlank() {
        return this.isBlank;
    }

    public void setIsBlank(String isBlank) {
        this.isBlank = isBlank;
    }


    /**
     * @return the channelStatus
     */
    public String getChannelStatus() {
        return channelStatus;
    }


    /**
     * @param channelStatus the channelStatus to set
     */
    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

}