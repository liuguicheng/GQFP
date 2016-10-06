package com.plugins.dirtree.command;

import java.sql.Timestamp;

import org.springline.web.mvc.SpringlineCommand;

import com.plugins.dirtree.entity.DirData;

public class DirDataEditInfo extends SpringlineCommand {
    /**
     *
     */
    private static final long serialVersionUID = 895831603747617812L;
    // Fields

    private String dirId;
    private String dirTypeId;
    private String dirCode;
    private String dirName;
    private Integer sortOrder;
    private String isValid=DirData.NORMAL_STATE;
    private String parentId;
    private String remark;
    private Timestamp createTime;

    // Constructors

    /** default constructor */
    public DirDataEditInfo() {
        InitDatetime();
    }

    /** minimal constructor */
    public DirDataEditInfo(String dirId) {
        this.dirId = dirId;
    }

    /** full constructor */
    public DirDataEditInfo(String dirId, String dirTypeId, String dirCode,
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

    /**
     * 初始化时间
     */
    private void InitDatetime(){
        createTime=java.sql.Timestamp.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
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
