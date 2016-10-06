package com.systemic.gq.stock.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.OperateLog;
import com.console.entity.Staff;
import com.systemic.gq.entity.BonusContent;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Stock;
import com.systemic.gq.stock.command.StockEdit;
import com.systemic.gq.stock.command.StockInfo;
import com.systemic.gq.stock.service.IBonusContentService;
import com.systemic.gq.stock.service.IStockService;


@Controller
public class StockController {
	@Autowired
	private IStockService stockService;
	
	
	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}

	@Autowired
	private IBonusContentService bonuscontentService;

	public void setBonuscontentService(IBonusContentService bonuscontentService) {
		this.bonuscontentService = bonuscontentService;
	}
	
	
	//列表页面
	@RequestMapping(value = "/gq/stockList.do")
	public String proposalInfoManage(HttpServletRequest request,
			HttpServletResponse response, Model model, StockInfo info) {
		Page page = this.stockService.selectPageStock(info);
		model.addAttribute("page", page);
		model.addAttribute("message", request.getParameter("message"));
		return "gq/gq/stockList";
	}
	
	//去新增页面
			@RequestMapping(value = "/gq/stockEdit.do",method=RequestMethod.GET)
			public String toStockEdit(HttpServletRequest request,
					HttpServletResponse response,Model model) {
				String id=request.getParameter("id");
				List stringConverList=new ArrayList();//存储用户选择的股权奖励
				List bonuscontentlist=queryBonusContentList();//查询系统设置的股权奖励
				List removeUserBonusContentList=new ArrayList();//去除用户选择的股权奖励
				if(id!=null&&!"".equals(id)){
				 	Stock stock =this.stockService.selectStock(id);
				 	stringConverList=doStringConvertList(stock.getBonusContentID());
				 	if(bonuscontentlist!=null){
				 		removeUserBonusContentList=doTOUserBonusContentlist(stock.getBonusContentID(),bonuscontentlist);
				 	}
				 	model.addAttribute("command",stock);
				}
				model.addAttribute("userbonuscontentstr", stringConverList);
				model.addAttribute("removeuserbonusbontentlist", removeUserBonusContentList);
				model.addAttribute("bonuscontentlist",bonuscontentlist);
				return "gq/gq/stockEdit";
			}
	//添加页面
			@RequestMapping(value = "/gq/stockEdit.do",method=RequestMethod.POST)
			public String stockEdit(HttpServletRequest request,
					HttpServletResponse response,Model model,Long token,StockEdit stockedit) {
				try{
					String bonusContentStr=parametersALL(request);
					stockedit.setBonusContentID(bonusContentStr);
					this.stockService.insertStock(stockedit);
					model.addAttribute("message", "操作成功");
					
					String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上-修改股权种类";
					Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
					Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
					ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_STOCK_UPDATE, member, logContent);
					
					
				}catch(Exception e){
					model.addAttribute("message", "操作失败"+e.getMessage());
				}
				model.addAttribute("command", stockedit);
				model.addAttribute("token", token);
				return "redirect:../gq/stockList.do";
			}
	
			//删除
			@RequestMapping(value = "/gq/stockDel.do",method=RequestMethod.POST)
			public String dodel(HttpServletRequest request,
					HttpServletResponse response, Model model, StockEdit info) {
				try{
					if(info.getId()!=null){
						String idstr[]=info.getId().split(",");
						stockService.deleteStock(idstr);
						model.addAttribute("message", "删除成功");
						
						String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上-删除股权种类";
						Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
						Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
						ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_STOCK_DEL, member, logContent);
						
					}
				}catch(Exception e){
					System.out.println(e.getMessage());
					model.addAttribute("message", "删除失败"+e.getMessage());
				}
				
				return "redirect:../gq/stockList.do";
			}	
			
			
		//获取用户选择的股权奖励的种类	
		private String parametersALL(HttpServletRequest request){
			String bonusContentStr="";
			List<BonusContent> bonusContentList=queryBonusContentList();
			String par="";
			String bonustype="";
			if(!bonusContentList.isEmpty()){
				for (BonusContent bonusContent : bonusContentList) {
					bonustype=bonusContent.getBonusType();
					par=request.getParameter("bonuscontentstr_"+bonustype);
					bonusContentStr+=par+",";
				}
				bonusContentStr = bonusContentStr.substring(0,bonusContentStr.length()-1);
				System.out.println(bonusContentStr);
			}
			return bonusContentStr;
		}
		
		//根据用户选的股权字符串转换成list，方便页面循环判断
		private List  doStringConvertList(String bonusContentString){
			ArrayList<String> arrayList=null;
			if(bonusContentString!=null&&!"".equals(bonusContentString)){
			 	String rest[]=	bonusContentString.split(",");
			 	if(rest.length>0){
//			 		for (String string : rest) {
//				 		bonusContentStr.add(string);
//					}
			 		//字符串转数组
			 		arrayList = new ArrayList<String>(Arrays.asList(rest));
			 		System.out.println(arrayList);
			 	}
		 	}
			return arrayList;
		}
		//去除用户选择的股权奖励种类
		private List doTOUserBonusContentlist(String bonusContentString,List<BonusContent> bonuscontentlist){
			List<BonusContent> removeBonusContentlist=new ArrayList<BonusContent>();
			removeBonusContentlist.addAll(bonuscontentlist);
			if(bonusContentString!=null&&!"".equals(bonusContentString)){
			 	String rest[]=	bonusContentString.split(",");
			 	if(rest.length>0){
			 		for (String string : rest) {
			 			for (BonusContent bous : removeBonusContentlist) {
							if(bous.getId().equals(string)){
								removeBonusContentlist.remove(bous);
								break;
							}
						}
					}
			 	}
		 	}
			return removeBonusContentlist;
		}
		private List  queryBonusContentList	(){
			return bonuscontentService.selectBonusContentList(null);
		}
			
}
