package com.systemic.gq.stock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.OperateLog;
import com.console.entity.Staff;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Rule;
import com.systemic.gq.entity.Stock;
import com.systemic.gq.stock.command.RuleEdit;
import com.systemic.gq.stock.command.StockEdit;
import com.systemic.gq.stock.service.IRuleService;
@Controller
public class RuleController {
	@Autowired
	private IRuleService ruleService;
	
	//去新增页面
	@RequestMapping(value = "/gq/ruleEdit.do",method=RequestMethod.GET)
	public String toRuleEdit(HttpServletRequest request,
			HttpServletResponse response,Model model) {
		 	Rule rule =this.ruleService.selectRuleBY();
		 	model.addAttribute("command",rule);
		 	return "gq/gq/ruleEdit";
	}
//添加页面
	@RequestMapping(value = "/gq/ruleEdit.do",method=RequestMethod.POST)
	public String stockEdit(HttpServletRequest request,
			HttpServletResponse response,Model model,Long token,RuleEdit ruleedit) {
		try{
			this.ruleService.insertStock(ruleedit);
			
			String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上-修改规则";
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_RULE, member, logContent);
			
			
			model.addAttribute("message", "操作成功");
		}catch(Exception e){
			model.addAttribute("message", "操作失败"+e.getMessage());
		}
		model.addAttribute("command", ruleedit);
		model.addAttribute("token", token);
		return "redirect:../gq/ruleEdit.do";
	}
	
}
