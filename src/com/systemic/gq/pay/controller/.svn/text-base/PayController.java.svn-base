package com.systemic.gq.pay.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.member.command.MemberEditInfo;
import com.systemic.gq.pay.command.PayInfoEdit;
import com.systemic.gq.pay.command.PayQueryInfo;
import com.systemic.gq.pay.service.ISpringPayService;

@Controller
@RequestMapping(value = "/pay")
public class PayController {
	@Autowired
	private ISpringPayService springPayService;

	/**
	 * 支付公用视图
	 * 
	 * @param request
	 * @param model
	 * @param info
	 * @param viewType
	 * @return
	 */
	public String payManageView(HttpServletRequest request, Model model, PayQueryInfo info, String viewType) {
		Page page = this.springPayService.selectPay(info);
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		model.addAttribute("command", info);
		return "gq/pay/pay" + viewType;

	}

	/**
	 * 充值管理
	 * 管理员访问的
	 * @param request
	 * @param response
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/payManage.do")
	public String payManage(HttpServletRequest request, HttpServletResponse response, Model model, PayQueryInfo info) {
		return payManageView(request, model, info, "Manage");
	}
	/**
	 * 充值管理
	 * 会员自己查看自己的列表
	 * @param request
	 * @param response
	 * @param model
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/payMember.do")
	public String payMember(HttpServletRequest request, HttpServletResponse response, Model model, PayQueryInfo info) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		try {
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			if (member != null) {
				// 只获取自己
				info.setMemberId(member.getMemberId());
				model.addAttribute("member", member);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 会员没有staffId数据
			throw new RuntimeException("获取数据列表出错，请联系管理员！");
		}
		return payManageView(request, model, info, "Manage");
	}

	/**
	 * 编辑充值申请
	 * 
	 * @param request
	 * @param response
	 * @param numberId
	 * @param model
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/payEdit.do", method = RequestMethod.GET)
	public String payEdit(HttpServletRequest request, HttpServletResponse response, String numberId, Model model,
			Long token) {
		MemberEditInfo command = new MemberEditInfo();
		if (StringUtils.isNotBlank(numberId)) {
			model.addAttribute("command", this.springPayService.selectPay(numberId));
		} else {
			command.setCreateTime(new Date());
			model.addAttribute("command", command);
		}
		model.addAttribute("token", token);
		return "gq/pay/payEdit";
	}

//	/**
//	 * 保存充值申请、审核充值
//	 * 
//	 * @param request
//	 * @param response
//	 * @param editType
//	 * @param model
//	 * @param token
//	 * @param info
//	 * @return
//	 */
//	@RequestMapping(value = "/payEdit.do", method = RequestMethod.POST)
//	public String paySaveEdit(HttpServletRequest request, HttpServletResponse response, @PathVariable String editType,
//			Model model, Long token, PayInfoEdit info) {
//		Member member = selectMember(request);
//		String logContent = "";
//		try {
//			// this.springPayService.savePay(info);
//			logContent = "在IP为" + ConsoleHelper.getUserIp() + "机器上-申请" + info.getRecordType() + "充值";
//			model.addAttribute("message", "提交成功！");
//			PayInfo pay = this.springPayService.selectPay(info.getNumberId());
//			ConsoleHelper.getInstance().getSpringPayLogService().savePayLog(logContent, member, pay);
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("message", "提交失败！");
//		}
//		model.addAttribute("command", info);
//		model.addAttribute("token", token);
//		return "redirect:../pay/payManage.do";
//	}

	@RequestMapping(value = "/auditManage.do")
	public String auditManage(HttpServletRequest request, Model model, PayQueryInfo info) {
		return payManageView(request, model, info, "AuditManage");
	}
    
	/**
	 * 申请充值
	 * @param request
	 * @param response
	 * @param model
	 * @param token
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/ajaxEditPay.do")
	@ResponseBody
	public String ajaxEditPay(HttpServletRequest request, HttpServletResponse response, Model model, Long token,
			PayInfoEdit info) {
		String staffId = request.getParameter("memnberStaffId");
		String memberId=request.getParameter("memberId");
		if (staffId != null && !"".equals(staffId)) {

			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffId);
			try {
				if(member==null){
					model.addAttribute("message", "会员编号不存在！");
					return "0";
				}
				info.setApplyState(ConsoleHelper.YES);
				this.springPayService.savePay(info, member);

			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "提交失败！");

			}
			model.addAttribute("command", info);
			model.addAttribute("token", token);
			if(memberId!=null&&!"".equals(memberId)){
				//返回2 跳转到会员自己的充值列表
				return "2";
			}
			//返回1 跳转到管理员看到的充值列表页面
			return "1";
		}
		return "0";
	}
   
	@RequestMapping(value = "/payReview.do", method = RequestMethod.GET)
	public String topayReview(HttpServletRequest request, HttpServletResponse response, Model model) {
		String numberid = request.getParameter("numberId");
		PayInfo payinfo = this.springPayService.selectPay(numberid);
		model.addAttribute("payinfo", payinfo);
		return "gq/pay/payReview";
	}

	/**
	 * 充值审核
	 * @param request
	 * @param response
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/payReview.do", method = RequestMethod.POST)
	@ResponseBody
	public String dopayReview(HttpServletRequest request, HttpServletResponse response, Model model, PayInfoEdit info) {
		String type = request.getParameter("istrue");
		String jg="不同意";
		PayInfo payinfo = this.springPayService.selectPay(info.getNumberId());
		
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		if (payinfo != null) {
			payinfo.setHandleId(member.getMemberId());
			payinfo.setMemnberStaffId(member.getStaffId());
			payinfo.setApplyState(type);
			payinfo.setAuditTime(new Date());
			payinfo.setAuditIp(ConsoleHelper.getUserIp());
			this.springPayService.updatePayInfo(payinfo);
			model.addAttribute("message", "操作成功");
			if ("2".equals(type)) {
				// 审核同意 才更新会员金钱
				//更新会员电子币
				updateMemberElectroniccurency(payinfo);
				jg="同意";
			}
			//添加paylog
			addPayLog(payinfo,jg);
		}

		return "1";
	}

	private void addPayLog(PayInfo info,String jg) {
		String logContent ="用户编号为:"+info.getMemnberStaffId()+"用户,在IP为"+info.getApplyLocalHost()+"机器上-申请充值。"+
	      "充值金额为:"+info.getApplyRecordNum()+"!审核人:"+info.getHandleId()+",在ip为"+info.getAuditIp()+"机器上已审核,审核结果为:"+jg;
		String operationType="1";
		String memberId=info.getMemnberStaffId();
		double money=info.getApplyRecordNum();
		ConsoleHelper.getInstance().getSpringPayLogService().savePayLog(logContent,money, operationType,memberId);
	}

	private void updateMemberElectroniccurency(PayInfo payinfo) {
		Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(payinfo.getMemnberStaffId());
		
		Double money=member.getElectroniccurrency();
		if(money==null){
			money=0.00;
		}
		member.setElectroniccurrency(money + payinfo.getApplyRecordNum());
		ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
	}
	
	

	

}
