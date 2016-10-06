package com.systemic.gq.pay.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Rule;
import com.systemic.gq.entity.TransferAccounts;
import com.systemic.gq.pay.command.TransferAccountsEdit;
import com.systemic.gq.pay.command.TransferAccountsQueryInfo;
import com.systemic.gq.pay.service.ITransferAccountService;
import com.systemic.unit.ConUnit;
@Controller
@RequestMapping(value="/transferAccount")
public class TransferAccountController {

	@Autowired
	private ITransferAccountService  transferAccountService;
	public void setTransferAccountService(ITransferAccountService transferAccountService) {
		this.transferAccountService = transferAccountService;
	}
	
	@RequestMapping(value="/selectTransferAccountPage.do")
	public String selectTransferAccountPage(HttpServletRequest reuquest,
			HttpServletResponse response,Model model,TransferAccountsQueryInfo info ){
		
		Page page=transferAccountService.selectTransferAccountsPage(info);
		model.addAttribute("page", page);
		return "gq/pay/transferAccountManage";
		
	}
	
	
	@RequestMapping(value = "/selectTransferAccountPageMember.do")
	public String selectTransferAccountPageMember(HttpServletRequest request, HttpServletResponse response, Model model, TransferAccountsQueryInfo info) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		try {
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			if (member != null) {
				// 只获取自己
				info.setTaTurnoutAccount(member.getStaffId());
				model.addAttribute("member", member);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 会员没有staffId数据
			throw new RuntimeException("获取数据列表出错，请联系管理员！");
		}
		Page page=transferAccountService.selectTransferAccountsPage(info);
		model.addAttribute("page", page);
		return "gq/pay/transferAccountManage";
	}
	
	@RequestMapping(value="/ajaxTransferAccount.do")
	@ResponseBody
	public String ajaxTransferAccount(HttpServletRequest request,
			HttpServletResponse response,Model model,TransferAccountsEdit edit){
		    String turnoutid=edit.getTaTurnoutAccount();
		    String intoid=edit.getTaIntoAccount();
		    String memberId=request.getParameter("memberId");
			if(turnoutid!=null&&!"".equals(turnoutid)&&
					intoid!=null&&!"".equals(intoid)){
				Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(turnoutid);
				Member intomember = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(intoid);
				try {
					if(member==null){
						//转出会员编号不存在
						return "-5";
					}
					if(intomember==null){
						//转入会员编号不存在
						return "-6";
					}
					//验证系统规则
					String returnstr=doValidationRule(model,edit,member);
					if(!"".equals(returnstr)){
						return returnstr;
					}
					edit.setTaTurnoutName(member.getUserName());
					edit.setTaIntoName(intomember.getUserName());
					edit.setTaTime(new Date());
					this.transferAccountService.save(edit);
					
					
					//添加操作记录
					addPayLog(edit);
					//更新双方用户金钱
					updateMemberForMoney(member,intomember,edit.getTaType(),edit.getTaMoeny());
					
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("message", "提交失败！");

				}
				if(memberId!=null&&!"".equals(memberId)){
					//返回2 跳转到会员自己的充值列表
					return "2";
				}
				//返回1 跳转到管理员看到的充值列表页面
				return "1";
				
			}
			return "0";
		
	}
	
	
	private void addPayLog(TransferAccountsEdit tr) {
		String logContent ="用户编号为:"+tr.getTaTurnoutAccount()+"用户,在IP为"+ConsoleHelper.getUserIp()+"机器上,向"+
				"用户编号为:"+tr.getTaIntoAccount()+"进行了转账,转账金额为:"+tr.getTaMoeny();
		String operationType="3";
		String memberId=tr.getTaTurnoutAccount();
		double money=tr.getTaMoeny();
		ConsoleHelper.getInstance().getSpringPayLogService().savePayLog(logContent,money, operationType,memberId);
	}
	
	private void updateMemberForMoney(Member member,Member intoMember,String type,Double Tranmoney) {
		
		Double money=0.0;
		switch (type) {
		case "1":
		    money = member.getElectroniccurrency();
			if (money == null) {
				money = 0.00;
			}
			member.setElectroniccurrency(money -Tranmoney);
			intoMember.setElectroniccurrency(money +Tranmoney);
			break;
		case "2":
			money = member.getGoldAward();
			if (money == null) {
				money = 0.00;
			}
			member.setGoldAward(money -Tranmoney);
			intoMember.setGoldAward(money +Tranmoney);
			break;
		case "3":

			break;
		default:
			break;
		}
		
		ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
		ConsoleHelper.getInstance().getSpringMemberService().updateMermber(intoMember);
	}
	
	
	
	// 验证转账所有规则
		private String doValidationRule(Model model, TransferAccountsEdit edit, Member member) {
			String returnstr = "";
			// 查询系统设置 获取转账手续费
			Rule rule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
			if (rule != null) {
				//判断今天是否可以 转账
				String transfertime=rule.getTransfer_time();
				int todayWeek= ConUnit.dayForWeek(new Date());
				if(transfertime.indexOf(String.valueOf(todayWeek))<0){
					//今日不能转账
					returnstr = "-7";
					return returnstr;
				}
				// 转账手续费 为0时不计算
				// 转账每日上限次数 为0时不计算
				int withdrawals_count = rule.getWithdrawals_count();
				// 转账每日金额 为0时不计算
				int withdrawals_money = rule.getWithdrawals_money();
				if (edit.getTaMoeny() > withdrawals_money) {
					// 转账金额超过规定金额
					model.addAttribute("message", "单次转账金额已超过限定！");
					returnstr = "-1";
					return returnstr;
				}
				if (withdrawals_count != 0 || withdrawals_money != 0) {
					// 查询转账记录 （条件：当日）
					TransferAccountsQueryInfo info=new TransferAccountsQueryInfo();
					info.setTaTime(new Date());
					List<TransferAccounts> list = this.transferAccountService.selectTransferAccountsList(info);
					if (list != null && !list.isEmpty()) {
						if (list.size() >= withdrawals_count) {
							model.addAttribute("message", "该会员今日转账次数已达上限,请明日再进行转账！");
							returnstr = "-2";
							return returnstr;
						}
						double totalMoney = 0;
						for (TransferAccounts t : list) {
							totalMoney += t.getTaMoeny();
						}
						// 加上本次转账金额
						totalMoney += edit.getTaMoeny();
						// 判断系统转账每日上限 是否超出
						if (totalMoney >= withdrawals_money) {
							model.addAttribute("message", "该会员今日转账金额已达上限,请明日再进行转账！");
							returnstr = "-3";
							return returnstr;
						}

						// 判断余额
						String returstr = pdYe(edit, member);
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
		private String pdYe(TransferAccountsEdit transferAccounts, Member member) {
			String returnstr = "";
			// 获取转账币种类型
			String waccounttype = transferAccounts.getTaType();
			switch (waccounttype) {
			case "1":
				// 电子币
				// 判断是否有相关金钱可以转账
				if (transferAccounts.getTaMoeny() > member.getElectroniccurrency()) {
					returnstr = "-4";
				}
				break;
			case "2":
				// 奖金币
				// 判断是否有相关金钱可以转账
				if (transferAccounts.getTaMoeny() > member.getGoldAward()) {
					returnstr = "-4";
				}
				break;
			}

			return returnstr;
		}
}
