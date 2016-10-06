package com.systemic.basic.sms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.command.SmsEditInfo;
import com.systemic.basic.sms.service.ISmsService;

public class SmsEditController extends SpringlineSimpleFormController {

	/** 服务注入 */
	private ISmsService smsService;

	public void setSmsService(ISmsService smsService) {
		this.smsService = smsService;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Map model = new HashMap();
		SmsEditInfo info = (SmsEditInfo) command;
		//info.setSmsId(smsId);
		try {
			SysSmsType sms = this.smsService.saveSms(info);
			model.put("message", "保存成功！");

		} catch (Exception e) {
			e.printStackTrace();
			model.put("message", "操作失败！" + e.getMessage());
		}
		model.put("token", request.getParameter("token"));
		return new ModelAndView(new GBRedirectView(getSuccessView()), model);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		String smsId = request.getParameter("smsId");
		@SuppressWarnings("unused")
		//UserInfo user = (UserInfo) AuthenticationFilter.getAuthenticator(request);
		SysSmsType entity;
		if (StringUtils.isNotBlank(smsId)) {
			// 修改
			entity = this.smsService.loadSmsById(smsId);

		} else {
			// 新增
			entity = new SysSmsType();
			// Map dicData = (Map)
			// CacheHelper.getInstance().getCacheObject("dicEnterprisePrjType");
			// IDictionaryMapValueItem item = (IDictionaryMapValueItem)
			// HtmlHelper
			// .getMapData(dicData, "14");
			// Sms ent= (Sms) item.getData();
		}
		model.put("command", entity);
		model.put("token", request.getParameter("token"));

		return model;
	}

}
