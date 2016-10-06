package com.console.operatelog.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springline.orm.Page;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.console.operatelog.command.OperateLogQueryInfo;
import com.console.operatelog.service.IOperateLogService;

public class OperateLogManageController extends PaginationQueryController{

	/**  */
	private IOperateLogService operateLogService;

	/**
	 * @param operateLogService the operateLogService to set
	 */
	public void setOperateLogService(IOperateLogService operateLogService) {
		this.operateLogService = operateLogService;
	}

	/**
	 *
	 * @see org.springline.web.pagination.PaginationQueryController#selectQueryResult(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springline.web.pagination.PaginationInfo, java.util.Map)
	 */
	@Override
	protected Page selectQueryResult(HttpServletRequest request,
			HttpServletResponse response, PaginationInfo command, Map model)
			throws Exception {

		OperateLogQueryInfo info = (OperateLogQueryInfo) command;
		Page page = this.operateLogService.selectOperateLogList(info);
		return page;
	}

}
