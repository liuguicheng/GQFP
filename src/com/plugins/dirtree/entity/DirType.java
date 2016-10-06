package com.plugins.dirtree.entity;

import java.sql.Timestamp;

/**
 * DirType entity. @author MyEclipse Persistence Tools
 */

public class DirType implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 4377649597796000823L;
    /**
     *  目录树类型状态：正常
     */
    public static final String NORMAL_STATE = "1";
    /** 目录树类型状态：隐藏 */
    public static final String HIDDEN_STATE = "0";
    /**
     * 简单字典标识
     */
    public static final String SIMPLE_DIC_IDENTIFICATION = "dirTreeType";
    /**
     * 目录类型编号
     */
    private String dirTypeId;
    /**
     * 目录类型标识
     */
    private String dirTypeCode;
    /**
     * 目录类型名称
     */
    private String dirTypeName;
    /**
     * 目录类型序号
     */
    private Integer sortOrder;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 是否有效
     */
    private String isValid= NORMAL_STATE;



    // Constructors

    /** default constructor */
    public DirType() {
    }

    /** minimal constructor */
    public DirType(String dirTypeId) {
        this.dirTypeId = dirTypeId;
    }

    /** full constructor */
    public DirType(String dirTypeId, String dirTypeCode, String dirTypeName,
            Integer sortOrder, Timestamp createTime, String isValid) {
        this.dirTypeId = dirTypeId;
        this.dirTypeCode = dirTypeCode;
        this.dirTypeName = dirTypeName;
        this.sortOrder = sortOrder;
        this.createTime = createTime;
        this.isValid = isValid;
    }

    // Property accessors

    public String getDirTypeId() {
        return this.dirTypeId;
    }

    public void setDirTypeId(String dirTypeId) {
        this.dirTypeId = dirTypeId;
    }

    public String getDirTypeCode() {
        return this.dirTypeCode;
    }

    public void setDirTypeCode(String dirTypeCode) {
        this.dirTypeCode = dirTypeCode;
    }

    public String getDirTypeName() {
        return this.dirTypeName;
    }

    public void setDirTypeName(String dirTypeName) {
        this.dirTypeName = dirTypeName;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getIsValid() {
        return this.isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

}