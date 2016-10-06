
package com.console.role.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.manage.service.IManageService;
import com.console.role.command.RoleGrantInfo;
import com.console.role.service.IRoleService;

public class RoleGrantController extends SpringlineSimpleFormController {

    /**     *     */
    private IRoleService roleService;

    /**     *     */
    private IManageService manageService;

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        RoleGrantInfo info = (RoleGrantInfo) command;
        this.roleService.updateRoleGrantInfo(info);
        Map model = new HashMap();
        model.put("departmentId", request.getParameter("departmentId"));
        model.put("roleId", info.getRoleId());
        return new ModelAndView(new GBRedirectView(super.getSuccessView()), model);
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        model.put("roleList", this.roleService.selectRoleList());
        String departmentId = request.getParameter("departmentId");
        if (departmentId != null && departmentId.trim().length() > 0) {
            model.put("department", this.manageService.selectDepartment(departmentId));
            model.put("staffList", this.manageService.selectDepartmentStaff(departmentId));
        }
        String roleId = request.getParameter("roleId");
        if (roleId != null && roleId.trim().length() > 0) {
            model.put("roleId", new Integer(roleId));
            model.put("grantStaffSet", this.roleService.selectRoleStaffList(roleId));
        } else {
            model.put("grantStaffSet", Collections.EMPTY_SET);
        }

        return model;
    }

    /**
     * @param roleService The roleService to set.
     */
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * @param manageService The manageService to set.
     */
    public void setManageService(IManageService manageService) {
        this.manageService = manageService;
    }

}
