
package com.console.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.console.entity.Department;


public class DepartmentSelectController extends AbstractController {

    /**选择人员视图**/
    private String selectView;

    /**
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected final ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = getCustomDataMap(request);
        return new ModelAndView(this.selectView, model);
    }

    /** 默认按staffs传递已选择的人员信息，若有其他参数要求，交由子类重载该函数进行处理
     * @param request HttpServletRequest
     * @return 已选择的人员信息Map
     */
    protected Map getCustomDataMap(HttpServletRequest request) {
        //解析初始选择的人员
        Map model = new HashMap();
        String depId = request.getParameter("depId");
        String depName = request.getParameter("depName");
        if (depId != null && depName != null) {
            String[] id = depId.split(",");
            String[] name = depName.split(",");
            if (id.length == name.length) {
                List depList = new ArrayList();
                for (int i = 0; i < id.length; i++) {
                    if (id[i] == null || id[i].trim().length() == 0) {
                        break;
                    }
                    Department dep = new Department();
                    dep.setId(id[i]);
                    dep.setName(name[i]);
                    depList.add(dep);
                }
                model.put("depSelectedList", depList);
            }
        }
        return model;
    }

    /**
     * @param selectStaffView The selectStaffView to set.
     */
    public void setSelectView(String selectStaffView) {
        this.selectView = selectStaffView;
    }


}
