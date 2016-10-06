
package com.console.manage.command;

import java.io.Serializable;


public class DepartmentPowerInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3692604079790953505L;

    /**
     * 单位Id
     */
    private String depId;

    /**
     * 单位拥有的权限Id
     */
    private String[] powerId;


    /**
     * @return Returns the staffId.
     */
    public String[] getPowerId() {
        return powerId;
    }

    /**
     * @param staffId The staffId to set.
     */
    public void setPowerId(String[] staffId) {
        this.powerId = staffId;
    }

    /**
     * @return Returns the depId.
     */
    public String getDepId() {
        return depId;
    }

    /**
     * @param depId The depId to set.
     */
    public void setDepId(String depId) {
        this.depId = depId;
    }


}
