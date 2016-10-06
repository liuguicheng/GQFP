package com.plugins.dirtree.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.plugins.dirtree.service.IDirService;

public class DirTypeController extends SpringlineMultiActionController {

	private IDirService dirService;
	private String dirTypeListView = "../dirTree/dirTypeManage.do";

	public void setDirService(IDirService dirService) {
		this.dirService = dirService;
	}

	public void setDirTypeListView(String dirTypeListView) {
		this.dirTypeListView = dirTypeListView;
	}

	public ModelAndView dirTypeDelete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String[] ids = request.getParameterValues("dirTypeId");
		dirService.deleteDirType(ids);
		return new ModelAndView(new GBRedirectView(dirTypeListView));
	}

	public ModelAndView dirTypeSort(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String[] ids = request.getParameterValues("dirType");
			dirService.doDirTypeSort(ids);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return new ModelAndView(new GBRedirectView(dirTypeListView));
	}

	public ModelAndView dirTypeUse(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String id = request.getParameter("dirTypeId");

			dirService.doDirTypeUse(id);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return new ModelAndView(new GBRedirectView(dirTypeListView));
	}
}
