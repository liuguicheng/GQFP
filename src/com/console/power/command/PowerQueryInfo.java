/**
 * Description:
 * History:  2009-9-18 Create
**/

package com.console.power.command;

import org.springline.web.mvc.SpringlineCommand;

/**
 * @description
 */
public class PowerQueryInfo extends SpringlineCommand {

    /**
     *
     */
    private static final long serialVersionUID = 5859504432734427753L;

    /**
     * 菜单编号
     */
    private String powerCode;

    /**
     * 菜单名称
     */
    private String powerName;
    
    /**
     * 菜单所属模块
     */
    private String moduleName;


    /**
     * @return the powerCode
     */
    public String getPowerCode() {
        return powerCode;
    }

    /**
     * @param powerCode the powerCode to set
     */
    public void setPowerCode(String powerCode) {
        this.powerCode = powerCode;
    }

    /**
     * @return the powerName
     */
    public String getPowerName() {
        return powerName;
    }

    /**
     * @param powerName the powerName to set
     */
    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    
}
