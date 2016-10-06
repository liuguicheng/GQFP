package com.console.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.console.entity.Staff;
import com.console.manage.command.StaffQueryInfo;
import com.console.manage.service.IManageService;

public class SelectOperateStaffController extends SpringlineMultiActionController{

	/**     *     */
    private IManageService manageService;


    /**
     * 审核人员选择.
     * @param request   HttpServletRequest实例
     * @param response  HttpServletResponse实例
     * @return  ModelAndView
     */
    public ModelAndView selectOperateStaff(HttpServletRequest request, HttpServletResponse response) {

        Map model = new HashMap();

        Staff currentUser = (Staff) AuthenticationFilter.getAuthenticator(request);
        String depId = request.getParameter("depId");//部门id
        String operateCode = request.getParameter("operateCode");//页面权限编码

        if(depId != null && depId.trim().length() > 0 && operateCode != null && operateCode.trim().length() > 0){
        	List staffList = this.manageService.selectOperateStaff(depId, operateCode);
        	model.put("staffList", staffList);
        }
        if(depId == null && operateCode != null && operateCode.trim().length() > 0){
        	List staffList = this.manageService.selectOperateStaff(null, operateCode);
        	model.put("staffList", staffList);
        }

        model.put("token", request.getParameter("token"));
        return new ModelAndView("manage/selectOperateStaff", model);
    }

    /**
     * 录入人员选择.
     * @param request   HttpServletRequest实例
     * @param response  HttpServletResponse实例
     * @return  ModelAndView
     */
    public ModelAndView selectInputerStaff(HttpServletRequest request, HttpServletResponse response) {

        Map model = new HashMap();

        List staffList = this.manageService.selectAllStaff(new StaffQueryInfo());
        model.put("staffList", staffList);

        model.put("token", request.getParameter("token"));
        return new ModelAndView("manage/selectInputerStaff", model);
    }

    /**
     * @param manageService The manageService to set.
     */
    public void setManageService(IManageService manageService) {
        this.manageService = manageService;
    }
}
