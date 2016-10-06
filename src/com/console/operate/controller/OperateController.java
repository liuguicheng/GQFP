package com.console.operate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineCommandHelper;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.console.operate.command.OperateQueryInfo;
import com.console.operate.service.IOperateService;

public class OperateController extends SpringlineMultiActionController{

	private IOperateService operateService;

	/**
     * 列页面权限列表.
     * @param request   HttpServletRequest实例
     * @param response  HttpServletResponse实例
     * @return  ModelAndView
     */
    public ModelAndView operateList(HttpServletRequest request, HttpServletResponse response, OperateQueryInfo command) {
    	OperateQueryInfo info = (OperateQueryInfo) SpringlineCommandHelper.getSpringlineCommand(request);
        if (info == null) {
            info = command;
        }
        Map model = new HashMap();
        model.put("message", request.getParameter("message"));
        model.put("command", info);
        model.put("operateList", this.operateService.selectAllOperate(info));
        SpringlineCommandHelper.cacheSpringlineCommand(request, info);
        return new ModelAndView("manage/operateList", model);
    }

    /**
     * 删除指定的权限信息.
     * @param request   HttpServletRequest实例
     * @param response  HttpServletResponse实例
     * @return  ModelAndView
     */
    public ModelAndView operateDelete(HttpServletRequest request, HttpServletResponse response) {
        String[] operateId = request.getParameterValues("operateId");
        Map model = new HashMap();
        try {
            this.operateService.deleteOperate(operateId);
            model.put("message", "已成功删除所选权限菜单!");
        } catch (Exception ex) {
            model.put("message", "页面权限菜单已授权给相应角色，不能删除！");
        }
        model.put("token", request.getParameter("token"));
        return new ModelAndView(new GBRedirectView("../manage/operateList.do"), model);
    }

	/**
	 * @param operateService the operateService to set
	 */
	public void setOperateService(IOperateService operateService) {
		this.operateService = operateService;
	}

}
