package com.console.entity;

import java.io.Serializable;
import java.util.Set;


public class Role implements Serializable {
    /**隐含角色**/
    public static final String HIDDEN_ROLE = "0";
    /**普通角色**/
    public static final String NORMAL_ROLE = "1";

    /**
     * 简单字典标识
     */
    public static final String DIC_ROLE = "dicRole";

    /**
     * 对象id
     */
    private static final long serialVersionUID = -1654511848987819335L;
    /** 角色编号. */
    private String id;
    /** 角色名称. */
    private String name;
    /** 顺序. */
    private Integer sortOrder;
    /**
     * @return the sortOrder
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * @param sortOrder the sortOrder to set
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * @return the homeUrl
     */
    public String getHomeUrl() {
        return homeUrl;
    }

    /**
     * @param homeUrl the homeUrl to set
     */
    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    /** 主页. */
    private String homeUrl;
    
    /** 角色说明. */
    private String remark;
    /** 本角色包含的权限集. */
    private Set powers;
    /** 授与本角色的员工集. */
    private Set staffSet;
    /** 本角色包含的页面权限集. */
    private Set operates;

    /**
     * @hibernate.id column = "role_id" generator-class = "assigned"
     * @return Returns the id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @hibernate.property column = "role_name" length = "40" not-null = "true"
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @hibernate.property column = "remark" length = "40" not-null = "false"
     * @return Returns the remark.
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * @param remark The remark to set.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @hibernate.set table = "cg_sys_role_power" cascade = "none"
     *                inverse = "false" lazy = "true" order-by = "power_id asc"
     * @hibernate.collection-key column = "role_id"
     * @hibernate.collection-many-to-many class = "com.spower.basesystem.manage.valueobject.Power"
     *                column = "power_id"
     * @return Returns the powers.
     */
    public Set getPowers() {
        return this.powers;
    }

    /**
     * @param powers The powers to set.
     */
    public void setPowers(Set powers) {
        this.powers = powers;
    }

//    /**
//     * @hibernate.set table = "cg_sys_staff_role_rel" cascade = "none"
//     *                inverse = "true" lazy = "true" order-by = "staff_id asc"
//     * @hibernate.collection-key column = "role_id"
//     * @hibernate.collection-many-to-many class = "com.spower.basesystem.manage.valueobject.Staff"
//     *                column = "staff_id"
//     * @return Returns the staffSet.
//     */
//    public Set getStaffSet() {
//        return this.staffSet;
//    }

//    /**
//     * @param staffSet The staffSet to set.
//     */
//    public void setStaffSet(Set staffSet) {
//        this.staffSet = staffSet;
//    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Role) {
            if (this.id != null) {
                return this.id.equals(((Role) obj).getId());
            }
        }
        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.id.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[编号：" + this.id + " 名称：" + this.name + "]";
    }

    /**
     * @return Returns the staffSet.
     */
    public Set getStaffSet() {
        return staffSet;
    }

    /**
     * @param staffSet The staffSet to set.
     */
    public void setStaffSet(Set staffSet) {
        this.staffSet = staffSet;
    }

	/**
	 * @return the operates
	 */
	public Set getOperates() {
		return operates;
	}

	/**
	 * @param operates the operates to set
	 */
	public void setOperates(Set operates) {
		this.operates = operates;
	}

}

