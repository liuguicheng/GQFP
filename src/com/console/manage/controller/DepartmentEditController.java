package com.console.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.entity.Department;
import com.console.manage.command.DepartmentInfo;
import com.console.manage.service.IManageService;


public class DepartmentEditController extends SpringlineSimpleFormController {
	/**  */
	private IManageService manageService;

	/**
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
	 */
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		DepartmentInfo info = new DepartmentInfo();
		String id = request.getParameter("id");
		if (id == null) { // 新增
			String parentId = request.getParameter("parentId");
			if (parentId != null && parentId.trim().length() > 0) {
				Department parentDep = this.manageService
					.selectDepartment(parentId);
				info.setParentId(parentDep.getId());
				info.setParentName(parentDep.getName());
				info.setOrderNumber(this.manageService
					.selectUsableOrderNumber(parentId));
			}
			String isIndependent = request.getParameter("isIndependent");
			if (isIndependent != null && isIndependent.trim().length() > 0) {
				info.setIsIndependent(new Boolean(true));
			}
		} else { // 修改
			Department department = this.manageService.selectDepartment(id);
			BeanUtils.copyProperties(department, info);
			info.setParentName(this.manageService.selectDepartment(
				info.getParentId()).getName());
			if (department.getId().equals(department.getAncestorDep())) { // 单位
				info.setIsIndependent(new Boolean(true));
			}
		}
		model.put("command", info);
		model
			.put("currentUser", AuthenticationFilter.getAuthenticator(request));
		return model;
	}

	/**
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Map model = new HashMap();
		DepartmentInfo info = (DepartmentInfo) command;
		model.put("parentId", info.getParentId());
		try {
			this.manageService.saveDepartment(info);
		} catch (Exception ex) {
			model.put("message", ex.getMessage());
			model.put("command", info);
			return new ModelAndView(this.getFormView(), model);
		}

		return new ModelAndView(new GBRedirectView(this.getSuccessView()),
			model);
	}

	/**
	 * @see org.springline.web.mvc.SpringlineSimpleFormController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(
			"true", "false", true));
	}

	/**
	 * @return Returns the powerService.
	 */
	public IManageService getManageService() {
		return this.manageService;
	}

	/**
	 * @param manageService The manageService to set.
	 */
	public void setManageService(IManageService manageService) {
		this.manageService = manageService;
	}

}
