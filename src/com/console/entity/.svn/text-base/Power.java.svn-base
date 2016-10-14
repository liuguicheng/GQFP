package com.console.entity;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.springline.beans.tree.support.layered.ILayerTreeAbility;

/**
 * The <code>Power</code> class 描述一个系统权限对象.
 */
public class Power implements Serializable, ILayerTreeAbility {
    /**
     * 对象id
     */
    private static final long serialVersionUID = 3471103268794882275L;

    /**  */
    public static final int LEVEL_LENGTH = 2;

    /**
     * 对应的字典标识
     */
    public static final String TREE_DIC_IDENTIFICATION = "menuTree";

    /**
     * 必须按角色授权才有效的菜单
     */
    public static final String POWER_TYPE_ROLE = "1";
    /**
     * 必须按部门授权才有效的菜单
     */
    public static final String POWER_TYPE_DEPT = "2";
    /**
     * 必须迭代分配才有效的权限
     * 角色、部门都拥有该权限才有效
     */
    public static final String POWER_TYPE_ITERATE = "3";
    /**
     * 公用权限，不用分配就已经可以使用
     */
    public static final String POWER_TYPE_COMMON = "4";


    /** 权限编号. */
    private String id;
    /** 权限编码. */
    private String code;
    /** 权限名称. */
    private String name;
    /** 权限功能的url. */
    private String url;
    /** 权限图标的url. */
    private String imgUrl;
    /** 权限所属的模块名称. */
    private String moduleName;
    /** 权限类型标志. */
    private String typeFlag;

    /**
     * 为实现菜单树而增加的属性，
     * 层次，设置layerCode时自动根据4位一层的编码规则进行计算
     */
    private int layer;

//    /** 包含本权限的角色集. */
//    private Set roles;
    /**包含本权限的单位集和**/
    private Set departments;
  
    /**
     * 访问该页面需要几级密码
     */
    private int passwordLevel;
    /**
     * @hibernate.id column = "power_id" generator-class = "identity"
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
     * @hibernate.property column = "power_code" length = "40" not-null = "true"
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
//        String tempStr = code;
//        while (tempStr.charAt(tempStr.length() - 1) == '0') {
//            tempStr = tempStr.substring(0, tempStr.length() - 1);
//        }
        this.layer = code.length() / LEVEL_LENGTH;
//        if (tempStr.length() % LEVEL_LENGTH > 0) {
//            layer++;
//        }
    }

    /**
     * @hibernate.property column = "power_name" length = "30" not-null = "true"
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
     * @hibernate.property column = "url" length = "100"
     * @return Returns the url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @hibernate.property column = "imgurl" length = "100"
     * @return Returns the imgUrl.
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl The imgUrl to set.
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @hibernate.property column = "module_name" length = "20"
     * @return Returns the moduleName.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param moduleName The moduleName to set.
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * @hibernate.property column = "power_type_flag" length = "1" not-null = "true"
     * @return Returns the typeFlag.
     */
    public String getTypeFlag() {
        return typeFlag;
    }

    /**
     * @param typeFlag The typeFlag to set.
     */
    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

//    /**
//     * @hibernate.set table = "cg_sys_role_power" cascade = "none"
//     *                inverse = "true" lazy = "true" order-by = "role_id"
//     * @hibernate.collection-key column = "power_id"
//     * @hibernate.collection-many-to-many class = "com.spower.basesystem.manage.valueobject.Role"
//     *                column = "role_id"
//     * @return Returns the roles.
//     */
//    public Set getRoles() {
//        return this.roles;
//    }

//    /**
//     * @param roles The roles to set.
//     */
//    public void setRoles(Set roles) {
//        this.roles = roles;
//    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Power) {
            if (this.id != null) {
                return this.id.equals(((Power) obj).getId());
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
     * @return Returns the departments.
     */
    public Set getDepartments() {
        return departments;
    }

    /**
     * @param departments The departments to set.
     */
    public void setDepartments(Set departments) {
        this.departments = departments;
    }

    /**
     * @return 对应部门Id的字符串，Id之间以逗号分隔
     */
    public String getDepartmentIdString() {
        StringBuffer ids = new StringBuffer();
        for (Iterator it = this.departments.iterator(); it.hasNext();) {
            Department dep = (Department) it.next();
            ids.append(dep.getId()).append(",");
        }
        if (ids.length() > 0) {
            ids.deleteCharAt(ids.length() - 1);
        }
        return ids.toString();
    }

    /**
     * @return 对应部门名称的字符串，Id之间以逗号分隔
     */
    public String getDepartmentNameString() {
        StringBuffer ids = new StringBuffer();
        for (Iterator it = this.departments.iterator(); it.hasNext();) {
            Department dep = (Department) it.next();
            ids.append(dep.getName()).append(",");
        }
        if (ids.length() > 0) {
            ids.deleteCharAt(ids.length() - 1);
        }
        return ids.toString();
    }

    /**
     * @see org.springline.beans.tree.support.layered.ILayerTreeAbility#getLayer()
     */
    public int getLayer() {
        return this.layer;
    }

    /**
     * @see org.springline.beans.tree.support.layered.ILayerTreeAbility#getNodeKey()
     */
    public String getNodeKey() {
        if (this.id == null) {
            return null;
        }
        return this.id.toString();
    }

    /**
     * @see org.springline.beans.tree.support.layered.ILayerTreeAbility#getNodeName()
     */
    public String getNodeName() {
        return this.name;
    }

	/**
	 * @return the passwordLevel
	 */
	public int getPasswordLevel() {
		return passwordLevel;
	}

	/**
	 * @param passwordLevel the passwordLevel to set
	 */
	public void setPasswordLevel(int passwordLevel) {
		this.passwordLevel = passwordLevel;
	}


}
