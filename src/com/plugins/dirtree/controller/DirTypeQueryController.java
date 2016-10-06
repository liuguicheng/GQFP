package com.plugins.dirtree.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.plugins.dirtree.service.IDirService;

public class DirTypeQueryController extends SpringlineMultiActionController {

	private IDirService dirService;
	private String dirTypeListView;

	public void setDirTypeListView(String dirTypeListView) {
		this.dirTypeListView = dirTypeListView;
	}

	public void setDirService(IDirService dirService) {
		this.dirService = dirService;
	}

	public ModelAndView dirTypeManage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map model = new HashedMap();
		List list = dirService.selectAllDirTypes();
		model.put("dirTypeList", list);
		return new ModelAndView(dirTypeListView, model);
	}
}
