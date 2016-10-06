package com.plugins.dirtree.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.plugins.dirtree.command.DirTypeEditInfo;
import com.plugins.dirtree.entity.DirType;
import com.plugins.dirtree.service.IDirService;

public class DirTypeEditController extends SpringlineSimpleFormController {

	private IDirService dirService;

	public void setDirService(IDirService dirService) {
		this.dirService = dirService;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Map model = new HashMap();
		try {
			// TODO Auto-generated method stub
			DirTypeEditInfo info = (DirTypeEditInfo) command;

			boolean sucess = dirService.saveDirType(info);
			if (!sucess)
				throw new Exception("±£´æÊ§°Ü£¡");

			return new ModelAndView(new GBRedirectView(super.getSuccessView()),
					model);
		} catch (Exception e) {
			model.put("opMessage", e.getMessage());
			model.put("command", command);
			return new ModelAndView(super.getFormView(), model);
		}
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		Map model = new HashedMap();
		try {
			String id = request.getParameter("dirTypeId");
			DirType dirType = null;

			if (id != null && id.trim().length() > 0) {
				dirType = this.dirService.selectDirType(id);
			} else {
				dirType = new DirType();
				dirType.setSortOrder(dirService.selectUsableDirTypeOrderNumber());
			}
			model.put("command", dirType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
