package com.systemic.gq.pay.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.entity.Rule;
import com.systemic.gq.entity.Withdrawals;
import com.systemic.gq.pay.command.WithdrawalsEdit;
import com.systemic.gq.pay.command.WithdrawalsQueryInfo;
import com.systemic.gq.pay.service.ISpringWithdrawalsService;
import com.systemic.unit.ConUnit;

@Controller
@RequestMapping(value = "/withdrawals")
public class WithdrawalsController {

	@Autowired
	private ISpringWithdrawalsService springwithdrawalsService;

	/**
	 * @param springwithdrawalsService
	 *            the springwithdrawalsService to set
	 */
	public void setSpringwithdrawalsService(ISpringWithdrawalsService springwithdrawalsService) {
		this.springwithdrawalsService = springwithdrawalsService;
	}

	public String ManageView(HttpServletRequest request, Model model, WithdrawalsQueryInfo info, String viewType) {
		Page page = this.springwithdrawalsService.selectWithdrawalsLog(info);
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		model.addAttribute("command", info);
		return "gq/pay/withdrawals" + viewType;

	}

	@RequestMapping(value = "/withdrawalsManage.do")
	public String withdrawalsManage(HttpServletRequest request, HttpServletResponse response, Model model,
			WithdrawalsQueryInfo info) {
		return ManageView(request, model, info, "Manage");
	}

	@RequestMapping(value = "/withdrawalsMember.do")
	public String withdrawalsMember(HttpServletRequest request, HttpServletResponse response, Model model,
			WithdrawalsQueryInfo info) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		try {
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			if (member != null) {
				// 只获取自己
				info.setMemberstaffid(member.getStaffId());
				model.addAttribute("member", member);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// 会员没有staffId数据
			throw new RuntimeException("获取数据列表出错，请联系管理员！");
		}
		return ManageView(request, model, info, "Manage");
	}

	/**
	 * 提现申请
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param withdrawals
	 * @return
	 */
	@RequestMapping(value = "/ajaxEditwithdrawals.do")
	@ResponseBody
	public String ajaxEditwithdrawals(HttpServletRequest request, HttpServletResponse response, Model model,
			WithdrawalsEdit withdrawals) {
		String staffId = request.getParameter("memberstaffid");
		String memberId = request.getParameter("memberId");
		if (staffId != null && !"".equals(staffId)) {

			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffId);
			try {
				if (member == null) {
					model.addAttribute("message", "会员编号不存在！");
					return "0";
				}

				// 验证规则
				String returnstr = doValidationRule(model, withdrawals, member);
				if (!"".equals(returnstr)) {
					return returnstr;
				}

				// 获取比例
				int withdrawals_counter_fee = selbl();
				if (withdrawals_counter_fee == 0) {
					withdrawals.setActualMoeny(withdrawals.getWMoney());
					withdrawals.setWCounterfree(0);
				} else {
					// 手续费百分比
					double bfb = ConUnit.takePercentage(withdrawals_counter_fee, 100D);
					// 手续费
					double sxf = withdrawals.getWMoney() * bfb;
					// 去除手续费
					double actualMoeny = withdrawals.getWMoney() - sxf;
					withdrawals.setActualMoeny(actualMoeny);
					withdrawals.setWCounterfree(withdrawals_counter_fee);
				}
				withdrawals.setBankUsername(ConUnit.toutf(withdrawals.getBankUsername()));
				withdrawals.setStatus(ConsoleHelper.YES);
				withdrawals.setMemberUsername(member.getUserName());
				this.springwithdrawalsService.saveWithdrawals(withdrawals);

			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "提交失败");
				return "0";

			}
			if (memberId != null && !"".equals(memberId)) {
				// 返回2 跳转到会员自己的充值列表
				return "2";
			}
			// 返回1 跳转到管理员看到的充值列表页面
			return "1";
		}
		return "0";
	}

	// 获取提现比例
	private int selbl() {
		int withdrawals_counter_fee = 0;
		// 查询系统设置 获取提现手续费
		Rule rule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
		if (rule != null) {
			// 提现手续费 为0时不计算
			withdrawals_counter_fee = rule.getWithdrawals_counter_fee();
		}
		return withdrawals_counter_fee;
	}

	// 验证提现所有规则
	private String doValidationRule(Model model, WithdrawalsEdit withdrawals, Member member) {
		String returnstr = "";
		// 查询系统设置 获取提现手续费
		Rule rule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
		if (rule != null) {
			//判断今天是否可以 提现
			String withdrawlastime=rule.getWithdrawals_time();
			int todayWeek= ConUnit.dayForWeek(new Date());
			if(withdrawlastime.indexOf(String.valueOf(todayWeek))<0){
				//今日不能提现
				returnstr = "-7";
				return returnstr;
			}
			
			
			// 提现手续费 为0时不计算
			// 提现每日上限次数 为0时不计算
			int withdrawals_count = rule.getWithdrawals_count();
			// 提现每日金额 为0时不计算
			int withdrawals_money = rule.getWithdrawals_money();
			if (withdrawals.getWMoney() > withdrawals_money) {
				// 提现金额超过规定金额
				model.addAttribute("message", "单次提现金额已超过限定！");
				returnstr = "-1";
				return returnstr;
			}
			if (withdrawals_count != 0 || withdrawals_money != 0) {
				// 查询提现记录 （条件：当日）
				WithdrawalsQueryInfo info = new WithdrawalsQueryInfo();
				info.setApplyTime(new Date());
				List<Withdrawals> list = this.springwithdrawalsService.selectWithdrawalsList(info);
				if (list != null && !list.isEmpty()) {
					if (list.size() >= withdrawals_count) {
						model.addAttribute("message", "该会员今日提现次数已达上限,请明日再进行提现申请！");
						returnstr = "-2";
						return returnstr;
					}
					double totalMoney = 0;
					for (Withdrawals w : list) {
						totalMoney += w.getWMoney();
					}
					// 加上本次提现金额
					totalMoney += withdrawals.getWMoney();
					// 判断系统提现每日上限 是否超出
					if (totalMoney >= withdrawals_money) {
						model.addAttribute("message", "该会员今日提现金额已达上限,请明日再进行提现申请！");
						returnstr = "-3";
						return returnstr;
					}

					// 判断余额
					String returstr = pdYe(withdrawals, member);
					if (!"".equals(returstr)) {
						returnstr = returstr;
						return returnstr;
					}
				}
			}

		}
		return returnstr;
	}

	// 判断余额
	private String pdYe(WithdrawalsEdit withdrawals, Member member) {
		String returnstr = "";
		// 获取提现币种类型
		String waccounttype = withdrawals.getWAccountType();
		switch (waccounttype) {
		case "1":
			// 电子币
			// 判断是否有相关金钱可以提现
			if (withdrawals.getWMoney() > member.getElectroniccurrency()) {
				returnstr = "-4";
			}
			break;
		case "2":
			// 奖金币
			// 判断是否有相关金钱可以提现
			if (withdrawals.getWMoney() > member.getGoldAward()) {
				returnstr = "-4";
			}
			break;
		}

		return returnstr;
	}

	/**
	 * to审核
	 */
	@RequestMapping(value = "/withdrawalsReview.do", method = RequestMethod.GET)
	public String towithdrawalsReview(HttpServletRequest request, HttpServletResponse response, Model model) {
		String WId = request.getParameter("WId");
		Withdrawals withdrawals = this.springwithdrawalsService.selectWithdrawalsById(WId);
		model.addAttribute("withdrawals", withdrawals);
		return "gq/pay/withdrawalsReview";
	}

	/**
	 * do审核
	 */
	@RequestMapping(value = "/withdrawalsReview.do", method = RequestMethod.POST)
	@ResponseBody
	public String dowithdrawalsReview(HttpServletRequest request, HttpServletResponse response, Model model,
			WithdrawalsEdit wedit) {
		String type = request.getParameter("istrue");
		String jg = "不同意";
		Withdrawals withdrawals = this.springwithdrawalsService.selectWithdrawalsById(wedit.getWId());
		if (withdrawals != null) {
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			withdrawals.setAuditIp(ConsoleHelper.getUserIp());
			withdrawals.setAuditTime(new Date());
			withdrawals.setHandleId(member.getStaffId());
			withdrawals.setStatus(type);
			this.springwithdrawalsService.updateWithdrawals(withdrawals);
			model.addAttribute("message", "操作成功");
			if ("2".equals(type)) {
				// 审核同意 才更新会员金钱
				// 更新会员电子币
				updateMemberForMoney(withdrawals);
				jg = "同意";
			}
			// 添加paylog
			addPayLog(withdrawals, jg);

		}
		return "1";
	}

	private void updateMemberForMoney(Withdrawals withdrawlas) {

		Member member = ConsoleHelper.getInstance().getManageService()
				.selectMemberByStaffId(withdrawlas.getMemberstaffid());
		String type = withdrawlas.getWAccountType();
		Double money=0.0;
		switch (type) {
		case "1":
		    money = member.getElectroniccurrency();
			if (money == null) {
				money = 0.00;
			}
			member.setElectroniccurrency(money -withdrawlas.getWMoney());
			break;
		case "2":
			money = member.getGoldAward();
			if (money == null) {
				money = 0.00;
			}
			member.setGoldAward(money -withdrawlas.getWMoney());
			break;
		case "3":

			break;
		default:
			break;
		}
		
		ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
	}
	
	private void addPayLog(Withdrawals withdrawals,String jg) {
		String logContent ="用户编号为:"+withdrawals.getMemberstaffid()+"用户,在IP为"+withdrawals.getApplyLocalHost()+"机器上-申请提现。"+
	      "提现金额为:"+withdrawals.getWMoney()+"!审核人:"+withdrawals.getHandleId()+",在ip为"+ConsoleHelper.getUserIp()+"机器上已审核,审核结果为:"+jg;
		String operationType="2";
		String memberId=withdrawals.getMemberstaffid();
		double money=withdrawals.getWMoney();
		ConsoleHelper.getInstance().getSpringPayLogService().savePayLog(logContent,money, operationType,memberId);
	}

}
