package com.console.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.beans.dictionary.DictionaryLoader;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.Staff;
import com.console.manage.command.StaffInfo;
import com.console.manage.service.IManageService;
import com.console.role.service.IRoleService;

 /**
 * @description
 */
public class StaffSubDepartmentEditController extends SpringlineSimpleFormController {

    /**
     * service,需注入
     */
    private IManageService manageService;
    /**
     * service,需注入
     */
    private IRoleService roleService;

    /** （non Javadoc）
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException arg3) throws Exception {
        StaffInfo staffInfo = (StaffInfo) command;
        Map model = new HashMap();
//        try {
            Staff staff = this.manageService.updateStaff(staffInfo);
            if (staffInfo.getId() != null && staffInfo.getId().trim().length() > 0) {
                Staff tmp = (Staff) AuthenticationFilter.getAuthenticator(request);
                if (tmp.getId().equals(staff.getId())) { //修改当前员工
                    AuthenticationFilter.doAuthentication(request, staff);
                    if (!staff.getSysTemplate().equals(staffInfo.getSysTemplate())) {
                        model.put("templeteUpdate", "1");
                    }
                }
                model.put("message", "修改成功！");
            } else {
                model.put("message", "成功新增人员！");
            }
//        } catch (InvalidDataAccessResourceUsageException ex) {
//            model.put("message", ex.getMessage());
//            model.put("staff", staffInfo);
//            model.put("dicWebpagemodule", DictionaryLoader.getInstance().getAllDictionary().get("dicWebpagemodule"));
//            model.put("dicRole", roleService.selectRoleList());
//            model.put("departmentId", staffInfo.getDepartment());
//            return new ModelAndView(super.getFormView(), model);
//        }
        model.put("dicWebpagemodule", DictionaryLoader.getInstance().getAllDictionary().get("dicWebpagemodule"));
        model.put("dicRole", DictionaryLoader.getInstance().getAllDictionary().get("dicRole"));
        if (super.getSuccessView().startsWith("..")) {
            model.put("departmentId", staffInfo.getDepartment());
            return new ModelAndView(new GBRedirectView(super.getSuccessView()), model);
        } else {
            model.put("staff", staff);
            return new ModelAndView(super.getSuccessView(), model);
        }
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
    protected Map referenceData(HttpServletRequest request) throws Exception {
            Map model = new HashMap();
            String staffId = request.getParameter("staffId");
            Staff staff;
            if (staffId == null || staffId.trim().length() == 0) {
                staff = new Staff();
                staff.setValid(ConsoleHelper.YES);
                String departmentId = request.getParameter("departmentId");
                Department dp = this.manageService.selectDepartment(departmentId);
                staff.setDepartment(dp);
                staff.setOrderNumber(this.manageService.selectStaffUsableOrderNumber(departmentId));
            } else {
                    staff = this.manageService.selectStaff(staffId);
            }

            model.put("staff", staff);
            model.put("dicRole", roleService.selectRoleList());

//            IWFAdministrator admin = WFProxy.getInstance().getAdministrator();
//            if (admin != null) {
//                model.put("dicWorkQueue", admin.getQueueNames());
//            }
            model.put("currentUser", AuthenticationFilter.getAuthenticator(request));
            return model;
    }

    /**
     * @param manageService 要设置的 manageService。
     */
    public void setManageService(IManageService manageService) {
        this.manageService = manageService;
    }

    /**
     * @param roleService The roleService to set.
     */
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }



}
