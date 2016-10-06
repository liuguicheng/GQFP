
package com.console.role.command;

import java.io.Serializable;



public class RoleGrantInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8504162822774684959L;

    /**
     * ½ÇÉ«Id
     */
    private String roleId;

    /**
     * ÈËÔ±Id
     */
    private String[] staffId;

    /**
     * @return Returns the roleId.
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId The roleId to set.
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return Returns the staffId.
     */
    public String[] getStaffId() {
        return staffId;
    }

    /**
     * @param staffId The staffId to set.
     */
    public void setStaffId(String[] staffId) {
        this.staffId = staffId;
    }
}
