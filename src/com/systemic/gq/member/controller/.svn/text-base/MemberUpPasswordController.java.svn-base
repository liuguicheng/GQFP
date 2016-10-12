package com.systemic.gq.member.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.beans.utils.EncryptHelper;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.OperateLog;
import com.console.entity.Staff;
import com.console.entity.StaffSecurity;
import com.systemic.gq.entity.Member;
import com.systemic.gq.member.service.ISpringMemberService;
import com.systemic.unit.ConUnit;
import com.systemic.unit.ErrorDataMsg;

@Controller
@RequestMapping(value = "/memberUpPassword")
public class MemberUpPasswordController {

	@Autowired
	private ISpringMemberService springMemberService;
	
	@RequestMapping(value="/toUpPassword.do")
	public String toUpPassword(HttpServletRequest request, Model model){
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		model.addAttribute("command", member);
		return "gq/member/memberUpPassword";
	}
	
	
	
	
	@RequestMapping(value="/upLoginPasswrodAjax.do",produces="text/plain;charset=gbk")
	@ResponseBody
	public String upLoginPasswrodAjax(HttpServletRequest request){
		String oldlogin=request.getParameter("oldpassword")!=null?request.getParameter("oldpassword").trim():null;
		String password=request.getParameter("password")!=null?request.getParameter("password").trim():null;
		String con_password=request.getParameter("con_password")!=null?request.getParameter("con_password").trim():null;
		String id=request.getParameter("id");
		
		String msg="";
		ErrorDataMsg edm=new ErrorDataMsg();
		edm.setCode(0);
		if(oldlogin!=null&&password!=null&&con_password!=null&&(password.equals(con_password))){
			Member member=	springMemberService.selectMemberByStaffid(id);
			
			if(member==null){
				edm.setMessage("用户不存在");
				msg=ConUnit.tojson(edm);
				return msg;
			}
			
			if(member.getPassword().equals(EncryptHelper
	                .md5Encoding(oldlogin))){
				member.setPassword(con_password);
				springMemberService.updateMermber(member);
				edm.setCode(1);
				edm.setMessage("修改成功");
				String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上-修改登陆密码";
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_UP_LOGINPASSWORD, member, logContent);
			}else{
				edm.setMessage("原密码不正确");
			}
			
		}else{
			edm.setMessage("修改失败");
			
		}
		msg=ConUnit.tojson(edm);
		
		return msg;
	}
	
	
	
	@RequestMapping(value="/upSecondPasswrodAjax.do",produces="text/plain;charset=gbk")
	@ResponseBody
	public String upSecondPasswrodAjax(HttpServletRequest request){
		String oldlogin=request.getParameter("oldpasswodTwo")!=null?request.getParameter("oldpasswodTwo").trim():null;
		String password=request.getParameter("passwodTwo")!=null?request.getParameter("passwodTwo").trim():null;
		String con_password=request.getParameter("con_passwodTwo")!=null?request.getParameter("con_passwodTwo").trim():null;
		String id=request.getParameter("id");
		
		String msg="";
		ErrorDataMsg edm=new ErrorDataMsg();
		edm.setCode(0);
		if(oldlogin!=null&&password!=null&&con_password!=null&&(password.equals(con_password))){
			Member member=	springMemberService.selectMemberByStaffid(id);
			if(member==null){
				edm.setMessage("用户不存在");
				msg=ConUnit.tojson(edm);
				return msg;
			}
			
			if(member.getPasswodTwo().equals(EncryptHelper
	                .md5Encoding(oldlogin))){
				member.setPasswodTwo(EncryptHelper
		                .md5Encoding(password));
				springMemberService.updateMermber(member);
				edm.setCode(1);
				edm.setMessage("修改成功");
				String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上-修改二级密码";
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_UP_SECONDPASSWORD, member, logContent);
			}else{
				edm.setMessage("原密码不正确");
			}
			
		}else{
			edm.setMessage("修改失败");
			
		}
		msg=ConUnit.tojson(edm);
		
		return msg;
	}
	
	
	@RequestMapping(value="/upThirdPasswrodAjax.do",produces="text/plain;charset=gbk")
	@ResponseBody
	public String upThirdPasswrodAjax(HttpServletRequest request){
		String oldlogin=request.getParameter("oldpasswodThree")!=null?request.getParameter("oldpasswodThree").trim():null;
		String password=request.getParameter("passwordThree")!=null?request.getParameter("passwordThree").trim():null;
		String con_password=request.getParameter("con_passwordThree")!=null?request.getParameter("con_passwordThree").trim():null;
		String id=request.getParameter("id");
		
		String msg="";
		ErrorDataMsg edm=new ErrorDataMsg();
		edm.setCode(0);
		if(oldlogin!=null&&password!=null&&con_password!=null&&(password.equals(con_password))){
			Member member=	springMemberService.selectMemberByStaffid(id);
			if(member==null){
				edm.setMessage("用户不存在");
				msg=ConUnit.tojson(edm);
				return msg;
			}
			
			if(member.getPasswordThree().equals(EncryptHelper
	                .md5Encoding(oldlogin))){
				member.setPasswordThree(EncryptHelper
		                .md5Encoding(password));
				springMemberService.updateMermber(member);
				edm.setCode(1);
				edm.setMessage("修改成功");
				String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上-修改三级密码";
				ConsoleHelper.getInstance().getLogService().saveOperateLogForMember(OperateLog.LOG_TYPE_UP_THRIDPASSWORD, member, logContent);
			}else{
				edm.setMessage("原密码不正确");
			}
			
		}else{
			edm.setMessage("修改失败");
			
		}
		msg=ConUnit.tojson(edm);
		
		return msg;
	}
	
}
