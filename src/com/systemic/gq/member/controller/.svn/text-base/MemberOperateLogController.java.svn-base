package com.systemic.gq.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.console.operatelog.command.OperateLogQueryInfo;
import com.console.operatelog.service.IOperateLogService;
import com.systemic.gq.entity.Member;

@Controller
@RequestMapping(value="/memberOperateLog")
public class MemberOperateLogController {

	
	@Autowired
	private IOperateLogService operateLogService;

	@RequestMapping(value="/operatelog.do")
	protected String selectQueryResult(HttpServletRequest request,
			HttpServletResponse response, Model model,OperateLogQueryInfo info)
			throws Exception {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		info.setOperatorName(member.getUserName());
		Page page = this.operateLogService.selectOperateLogList(info);
		model.addAttribute("page", page);
		return "/gq/member/memberoperateLogList";
	}
}
