package com.plugins.dirtree.entity;

import java.sql.Timestamp;

import org.springline.beans.tree.support.paternity.IPaternityTreeAbility;

/**
 * DirData entity. @author MyEclipse Persistence Tools
 */

public class DirData implements java.io.Serializable,IPaternityTreeAbility {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = -6233975825423596087L;
    /**
     * 目录编号
     */
    private String dirId;
    /**
     * 目录类型编号
     */
    private String dirTypeId;
    private String dirCode;
    /**
     * 目录名称
     */
    private String dirName;
    /**
     * 目录序号
     */
    private Integer sortOrder;
    /**
     * 是否有效
     */
    private String isValid;
    /**
     * 上级目录编号
     */
    private String parentId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     *  目录树状态：正常
     */
    public static final String NORMAL_STATE = "1";
    /** 目录树状态：隐藏 */
    public static final String HIDDEN_STATE = "0";

    // Constructors


    /** default constructor */
    public DirData() {
    }

    public boolean isParent(IPaternityTreeAbility arg) {
        // TODO Auto-generated method stub
        DirData tmp=(DirData) arg;
        return (tmp != null && !this.equals(tmp) && this.getDirId().equals(tmp.getParentId()));
    }

    public boolean isRoot() {
        // TODO Auto-generated method stub
        return (this.parentId == null || this.parentId.trim().length() == 0 || this.parentId.equals(this.dirId));
    }

    public String getNodeKey() {
        // TODO Auto-generated method stub
        if (this.dirId == null) {
            return "";
        }
        return this.dirId.toString();
    }

    public String getNodeName() {
        // TODO Auto-generated method stub
         return this.dirName;
    }

    /** minimal constructor */
    public DirData(String dirId) {
        this.dirId = dirId;
    }

    /** full constructor */
    public DirData(String dirId, String dirTypeId, String dirCode,
            String dirName, Integer sortOrder, String isValid, String parentId,
            String remark, Timestamp createTime) {
        this.dirId = dirId;
        this.dirTypeId = dirTypeId;
        this.dirCode = dirCode;
        this.dirName = dirName;
        this.sortOrder = sortOrder;
        this.isValid = isValid;
        this.parentId = parentId;
        this.remark = remark;
        this.createTime = createTime;
    }

    // Property accessors

    public String getDirId() {
        return this.dirId;
    }

    public void setDirId(String dirId) {
        this.dirId = dirId;
    }

    public String getDirTypeId() {
        return this.dirTypeId;
    }

    public void setDirTypeId(String dirTypeId) {
        this.dirTypeId = dirTypeId;
    }

    public String getDirCode() {
        return this.dirCode;
    }

    public void setDirCode(String dirCode) {
        this.dirCode = dirCode;
    }

    public String getDirName() {
        return this.dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getIsValid() {
        return this.isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}