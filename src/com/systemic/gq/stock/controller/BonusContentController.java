package com.systemic.gq.stock.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springline.beans.cache.CacheHelper;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.OperateLog;
import com.console.entity.Staff;
import com.systemic.gq.entity.BonusContent;
import com.systemic.gq.entity.Member;
import com.systemic.gq.stock.command.BonusContentEdit;
import com.systemic.gq.stock.command.BonusContentInfo;
import com.systemic.gq.stock.command.StockInfo;
import com.systemic.gq.stock.service.IBonusContentService;
@Controller
public class BonusContentController {

	
	
	//列表页面
		@RequestMapping(value = "/gq/bonusContentList.do")
		public String proposalInfoManage(HttpServletRequest request,
				HttpServletResponse response, Model model, BonusContentInfo info) {
			Page page = ConsoleHelper.getInstance().getBonuscontentService().selectPageBonusConten(info);
			model.addAttribute("page", page);
			model.addAttribute("message", request.getParameter("message"));
			return "gq/gq/bonusContentList";
		}
		
		//去新增页面
		@RequestMapping(value = "/gq/bonusContentEdit.do",method=RequestMethod.GET)
		public String tobonusContentEdit(HttpServletRequest request,
				HttpServletResponse response,Model model) {
			String id=request.getParameter("id");
			if(id!=null&&!"".equals(id)){
			 	BonusContent bonuscontent =ConsoleHelper.getInstance().getBonuscontentService().selectBonusContentByid(id);
			 	model.addAttribute("command",bonuscontent);
			}
			return "gq/gq/bonusContentEdit";
		}
		
		//新增或修改
		@RequestMapping(value = "/gq/bonusContentEdit.do",method=RequestMethod.POST)
		public String addOrUpdate(HttpServletRequest request,
				HttpServletResponse response, Model model,Long token,  BonusContentEdit info) {
			
			try{
				ConsoleHelper.getInstance().getBonuscontentService().insertBonusConten(info);
				
				String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上-修改股权奖励种类";
				Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
				Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_BONUSCONTENT_UPDATE, member, logContent);
				
				model.addAttribute("message", "操作成功");
			}catch(Exception e){
				model.addAttribute("message", "操作失败"+e.getMessage());
			}
			model.addAttribute("command", info);
			model.addAttribute("token", token);
			return "redirect:../gq/bonusContentList.do";
		}
		
				//删除
				@RequestMapping(value = "/gq/bonusContentDel.do",method=RequestMethod.POST)
				public String dodel(HttpServletRequest request,
						HttpServletResponse response, Model model, BonusContentEdit info) {
					try{
						if(info.getId()!=null){
							String idstr[]=info.getId().split(",");
							ConsoleHelper.getInstance().getBonuscontentService().deleteBonusContent(idstr);
							model.addAttribute("message", "删除成功");
							
							//日志记录
							String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上-删除股权奖励种类";
							Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
							Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
							ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_BONUSCONTENT_DEL, member, logContent);
							
							
						}
					}catch(Exception e){
						System.out.println(e.getMessage());
						model.addAttribute("message", "删除失败"+e.getMessage());
					}
					
					return "redirect:../gq/bonusContentList.do";
				}
		
	
}
