package com.console.manage.command;

import org.springline.web.pagination.PaginationInfo;

public class DepartmentQueryInfo extends PaginationInfo {

    /**  */
    private static final long serialVersionUID = 6922392184198139792L;
    /** 所属部门. */
    private String depName;
    /** 所属部门编码 */
    private String depCode;
    /** 父部门ID. */
    private String parentId;
    /** ID. */
    private String id;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
