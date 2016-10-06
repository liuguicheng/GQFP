package com.console.operate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.entity.Operate;
import com.console.operate.command.OperateEditInfo;
import com.console.operate.service.IOperateService;

public class OperateEditController extends SpringlineSimpleFormController{

	private IOperateService operateService;

	/**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
    	Map model = new HashMap();
    	OperateEditInfo info = (OperateEditInfo) command;
        //NullBeanUtils.copyProperties(operate, info);
    	//BeanUtils.copyProperties(info, operate);

        this.operateService.saveOperate(info);

        model.put("token", request.getParameter("token"));
        model.put("message", "±£´æ³É¹¦£¡");

        return new ModelAndView(new GBRedirectView(super.getSuccessView()), model);
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
     */
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        String operateId = request.getParameter("operateId");
        if (operateId != null && operateId.trim().length() > 0) {
        	Operate operate = this.operateService.selectOperate(operateId);
            model.put("command", operate);
        }

        model.put("token", request.getParameter("token"));
        return model;
    }

	/**
	 * @param operateService the operateService to set
	 */
	public void setOperateService(IOperateService operateService) {
		this.operateService = operateService;
	}
}
