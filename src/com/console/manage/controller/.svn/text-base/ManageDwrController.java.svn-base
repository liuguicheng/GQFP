
package com.console.manage.controller;

import java.util.List;

import com.console.manage.service.IManageService;


public class ManageDwrController {

    /** manageService */
    private IManageService manageService;
    /**
     * 选择指定部门的员工
     * @param depId 部门编号
     * @return 员工列表
     */
    public List getDepartmentStaff(String depId) {
        return this.manageService.selectDepartmentStaff(depId);
    }


    /**
     * @return 返回 manageService。
     */
    public IManageService getManageService() {
        return manageService;
    }
    /**
     * @param manageService 要设置的 manageService。
     */
    public void setManageService(IManageService manageService) {
        this.manageService = manageService;
    }

}
