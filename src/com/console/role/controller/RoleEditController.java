package com.console.role.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.ConsoleHelper;
import com.console.role.command.RoleInfo;
import com.console.role.service.IRoleService;


public class RoleEditController extends SpringlineSimpleFormController {
    /**
     *
     */
    private IRoleService roleService;

    /**
     * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
            throws Exception {

        super.initBinder(request, binder);
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(java.lang.Object)
     */
    protected ModelAndView onSubmit(Object command) throws Exception {
        RoleInfo roleInfo = (RoleInfo) command;

        Map model = new HashMap();
        this.roleService.saveRole(roleInfo);

        return new ModelAndView(new GBRedirectView(super.getSuccessView()), model);
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        String id = request.getParameter("roleId");
        if (id != null && id.trim().length() > 0) {
            model.put("command", this.roleService.selectRole(id));
        }
        model.put("powerList", ConsoleHelper.getInstance().getPowerService().selectAllPower(null));
        return model;
    }



    /**
     * @param roleService The roleService to set.
     */
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

}
