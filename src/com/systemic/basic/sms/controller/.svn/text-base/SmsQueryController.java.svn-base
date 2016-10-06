package com.systemic.basic.sms.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.command.SmsQueryInfo;
import com.systemic.basic.sms.service.ISmsService;
import com.systemic.basic.sms.support.SmsHelperSupport;



public class SmsQueryController extends SpringlineMultiActionController {
	
	/** 服务注入 */
	private ISmsService smsService;

	public void setSmsService(ISmsService smsService) {
		this.smsService = smsService;
	}

	/**
	 *  显示主界面 
	 *  
	 */
	public ModelAndView smsManage(HttpServletRequest request, HttpServletResponse response, Object object){
//		SmsSocketController d = new SmsSocketController()
		return  new ModelAndView("basic/sms/smsManage");
	}
	
	 public ModelAndView smsSend(HttpServletRequest request,HttpServletResponse response) {
		 String moblieNum = request.getParameter("moblieNum");
		 String content = request.getParameter("content");
		 
		 SmsHelperSupport.getInstance().smsClientSupport(content, moblieNum);
//		 sk.smsClientSupport(content, moblieNum);
		 return new ModelAndView(new GBRedirectView("../basic/smsManage.do"));
	    }
	

		/**
		 * 查询所有的短信信息
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public ModelAndView smsQuery(HttpServletRequest request, HttpServletResponse response,SmsQueryInfo info) {
			Map model = new HashMap();
	        List<SysSmsType>  smsList = this.smsService.selectAllSms(info);
	        model.put("smsList", smsList);
	        model.put("token", request.getParameter("token"));
	        model.put("message", request.getParameter("message"));
	        return new ModelAndView("basic/sms/smsQuery", model);
		}
		
		/**
		 * 删除短信内容信息
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public ModelAndView smsDelete(HttpServletRequest request, HttpServletResponse response) {
			Map model = new HashMap();
			String[] ids = request.getParameterValues("smsId");
			try {
				this.smsService.deleteSms(ids);
				model.put("message", "短信删除成功！");
			} catch (Exception e) {
				e.printStackTrace();
				model.put("message", "操作失败！" + e.getMessage());
			}
			model.put("token", request.getParameter("token"));
			return new ModelAndView(new GBRedirectView("../basic/smsQuery.do"), model);
		}

}
