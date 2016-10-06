
package com.console.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.manage.command.DepartmentPowerInfo;
import com.console.manage.service.IManageService;
import com.console.power.service.IPowerService;


public class DepartmentPowerController extends SpringlineSimpleFormController {

    /**     *     */
    private IManageService manageService;
    /**     *     */
    private IPowerService powerService;

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        DepartmentPowerInfo info = (DepartmentPowerInfo) command;
        this.manageService.doGrantPowerToDepartment(info);

        return new ModelAndView(
                new GBRedirectView(super.getSuccessView()), "depId", info.getDepId());
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        model.put("powerList", this.powerService.selectAllPower(null));

        String depId = request.getParameter("depId");
        if (depId != null && depId.length() > 0) {
            model.put("department", this.manageService.selectDepartment(depId));
        }
        return model;
    }

    /**
     * @param manageService The manageService to set.
     */
    public void setManageService(IManageService manageService) {
        this.manageService = manageService;
    }

    /**
     * @param powerService The powerService to set.
     */
    public void setPowerService(IPowerService powerService) {
        this.powerService = powerService;
    }

}
