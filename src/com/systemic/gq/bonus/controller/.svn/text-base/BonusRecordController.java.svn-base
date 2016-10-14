package com.systemic.gq.bonus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.systemic.gq.bonus.command.BonusRecordInfo;
import com.systemic.gq.bonus.settlement.SettlementHelper;
/**
 * 奖金 控制器
 * @author lgc
 *
 */
@Controller
public class BonusRecordController {
	
	// 列表页面
	@RequestMapping(value = "/bonus/bonusRecordList.do")
	public String proposalInfoManage(HttpServletRequest request, HttpServletResponse response, Model model,
			BonusRecordInfo info) {

		Page page = ConsoleHelper.getInstance().getBonusRecordService().selectPageBonusRecord(info);
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return "gq/bonus/bonusRecordList";
	}

	@RequestMapping(value = "/bonus/bonusTest.do")
	public String propotest(HttpServletRequest request, HttpServletResponse response, Model model,
			BonusRecordInfo info) {
		SettlementHelper.settlementStaticBonus();
		return "gq/bonus/bonusTest";
	}

}
