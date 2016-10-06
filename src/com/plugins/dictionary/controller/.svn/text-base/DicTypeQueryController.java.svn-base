package com.plugins.dictionary.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springline.orm.Page;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.plugins.dictionary.command.DicTypeQueryInfo;
import com.plugins.dictionary.service.IDictionaryService;
/**
 *字典类型分页控制器
 * @author wanqiuyu
 *
 */
public class DicTypeQueryController extends PaginationQueryController{

	/**字典dictionaryService接口*/
	private IDictionaryService dictionaryService;

	/**
	 * 返回 IDictionaryService
	 * @return
	 */
	public IDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	/**
	 * 设置IDictionaryService
	 * @param dictionaryService
	 */
	public void setDictionaryService(IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	@Override
	protected Page selectQueryResult(HttpServletRequest request,
			HttpServletResponse response, PaginationInfo command, Map model)
			throws Exception {
		DicTypeQueryInfo info = (DicTypeQueryInfo)command;
		Page page = this.dictionaryService.getDicTypeQueryPage(info);
		return page;
	}

}
