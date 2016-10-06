package com.console.entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.springline.beans.tree.support.paternity.IPaternityTreeAbility;

/**
 * The <code>Department</code> class 描述部门对象.
 */
public class Department implements Serializable, IPaternityTreeAbility {
    /**
     * 对象id
     */
    private static final long serialVersionUID = -4360139611589850736L;
    /**
     * 树字典标识
     */
    public static final String TREE_DIC_IDENTIFICATION = "dicDepartmentTree";
    /**
     * 简单字典标识
     */
    public static final String SIMPLE_DIC_IDENTIFICATION = "dicDepartment";

    /** 部门状态：正常 */
    public static final String NORMAL_STATE = "1";
    /** 部门状态：隐藏 */
    public static final String HIDDEN_STATE = "0";

    /** 部门默认的 祖宗ID （用于汇聚采集管理的 数据资源 维护模块）*/
    public static final String DEFAULT_PARENT_ID = "1";

    /** 部门ID：外部组织大于10000. */
    private String id;
    /** 部门名称. */
    private String name;
    /** 部门名称. */
    private String distinguishedName;
    /**部门编码 */
    private String code;
    /** 部门简称. */
    private String alias;
    /** 部门领导. */
    private String lead;
    /** 状态：空值或0为正常，1隐藏状态. */
    private String state;
    /** 县局标志：1为县局. */
    private String rank;
    /** 描述信息. */
    private String inf;
    /** 父部门ID. */
    private String parentId;
    /**祖宗部门Id*/
    private String ancestorDep;
    /**顺序号*/
    private Integer orderNumber;

    /**部门对应的权限集合，修改部门信息的时候一同更新，因此修改部门信息时，必须初始化该集合**/
    private Set powerSet;

    /**
     * @see org.springline.beans.tree.support.paternity.IPaternityTreeAbility#isParent(org.springline.beans.tree.support.paternity.IPaternityTreeAbility)
     */
    public boolean isParent(IPaternityTreeAbility obj) {
        Department tmp = (Department) obj;
        return (tmp != null && !equals(tmp) && tmp.getParentId().equals(this.getId()));
    }

    /**
     * @see org.springline.beans.tree.support.paternity.IPaternityTreeAbility#isRoot()
     */
    public boolean isRoot() {
        return (this.parentId == null || this.parentId.equals(this.id));
    }

    /**
     * @see org.springline.beans.tree.support.ITreeAbility#getNodeKey()
     */
    public String getNodeKey() {
        if (this.id == null) {
            return "";
        }
        return this.id.toString();
    }

    /**
     * @see org.springline.beans.tree.support.ITreeAbility#getNodeName()
     */
    public String getNodeName() {
        return this.name;
    }

    /**
     * @hibernate.property column = "dep_alias" length = "20" not-null = "false"
     * @return Returns the alias.
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * @param alias The alias to set.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @hibernate.id column = "dep_id" generator-class = "assigned"
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
     * @hibernate.property column = "dep_inf" length = "100" not-null = "false"
     * @return Returns the inf.
     */
    public String getInf() {
        return this.inf;
    }

    /**
     * @param inf The inf to set.
     */
    public void setInf(String inf) {
        this.inf = inf;
    }

    /**
     * @hibernate.property column = "dep_lead" length = "20" not-null = "false"
     * @return Returns the lead.
     */
    public String getLead() {
        return this.lead;
    }

    /**
     * @param lead The lead to set.
     */
    public void setLead(String lead) {
        this.lead = lead;
    }

    /**
     * @hibernate.property column = "dep_name" length = "60" not-null = "true"
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
     * @hibernate.property column = "dep_rank" length = "1" not-null = "true"
     * @return Returns the rank.
     */
    public String getRank() {
        return this.rank;
    }

    /**
     * @param rank The rank to set.
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * @hibernate.property column = "dep_state" length = "1" not-null = "true"
     * @return Returns the state.
     */
    public String getState() {
        return this.state;
    }

    /**
     * @param state The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @hibernate.property column = "parent_dep" not-null = "true"
     * @return Returns the parentId.
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * @param parentId The parentId to set.
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

//    /**
//     * @hibernate.collection-one-to-many class = "com.spower.basesystem.manage.valueobject.Department"
//     * @hibernate.collection-key column = "parent_dep"
//     * @hibernate.set lazy = "true" where = "dep_state = '0'" order-by = "dep_id asc"
//     * @return Returns the subDepartment.
//     */
//    public Set getSubDepartment() {
//        return this.subDepartment;
//    }

//    /**
//     * @param subDepartment The subDepartment to set.
//     */
//    public void setSubDepartment(Set subDepartment) {
//        this.subDepartment = subDepartment;
//    }

//    /**
//     * @hibernate.collection-one-to-many class = "com.spower.basesystem.manage.valueobject.Staff"
//     * @hibernate.collection-key column = "dep_id"
//     * @hibernate.set lazy = "true" where = "valid = '0'" order-by = "staff_id asc"
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
        if (obj instanceof Department) {
            if (this.id != null) {
                return this.id.equals(((Department) obj).getId());
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
     * @return Returns the ancestorDep.
     */
    public String getAncestorDep() {
        return ancestorDep;
    }

    /**
     * @param ancestorDep The ancestorDep to set.
     */
    public void setAncestorDep(String ancestorDep) {
        this.ancestorDep = ancestorDep;
    }

    /**
     * @return Returns the orderNumber.
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber The orderNumber to set.
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * @return Returns the powerSet.
     */
    public Set getPowerSet() {
        return powerSet;
    }

    /**
     * @param powerSet The powerSet to set.
     */
    public void setPowerSet(Set powerSet) {
        this.powerSet = powerSet;
    }

    /** 判断当前部门是否拥有该权限
     * @param powerCode 权限编号
     * @return true or false
     */
    public boolean hasPower(String powerCode) {
        if (powerCode != null && this.powerSet != null) {
            for (Iterator it = this.powerSet.iterator(); it.hasNext();) {
                Power power = (Power) it.next();
                if (powerCode.equals(power.getCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return the distinguishedName
     */
    public String getDistinguishedName() {
        return distinguishedName;
    }

    /**
     * @param distinguishedName the distinguishedName to set
     */
    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }
}
