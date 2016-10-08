package com.systemic.gq.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.systemic.gq.entity.Member;
import com.systemic.gq.member.service.ISpringMemberService;
import com.systemic.unit.ConUnit;
import com.systemic.unit.TreeModel;
import com.systemic.unit.TreeUnit;

@Controller
@RequestMapping(value = "/memberMap")
public class MemberMapController {

	/**
	 * 会员图谱、直属关系图
	 */

	@Autowired
	private ISpringMemberService springMemberService;

	@RequestMapping(value = "/todirectoriesRelationship.do", method = RequestMethod.GET)
	public String todirectoriesRelationship(HttpServletRequest request, Model model) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		model.addAttribute("command", staff);
		return "gq/member/memberMap/MemberdirectoriesRelationship";
	}

	/**
	 * 查询节点
	 */
	@RequestMapping(value = "/xzChildNode.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String xzChildNode(HttpServletRequest request) {
		String staffid = request.getParameter("staffId");
		List list = new ArrayList();
		TreeModel tm = null;
		Member memberinfo = null;
		/**
		 * 查询我直接推荐的所有会员 返回：会员编号、注册金额、注册时间、团队人数、团队业绩 、(激活/未激活)
		 */
		if (staffid != null && !"".equals(staffid)) {
			// 查询传参用户
			memberinfo = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffid);
		} else {
			// 查询当前登陆用户
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			memberinfo = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			staffid = staff.getId();
		}

		tm = addstaffmemberNode(memberinfo, memberinfo.getReferenceId());
		if (tm != null) {
			list.add(tm);
		}
		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);

		return gsonString;
	}

	/**
	 * 直属关系
	 */
	@RequestMapping(value = "/directoriesRelationship.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String directoriesRelationship(HttpServletRequest request) {
		String staffid = request.getParameter("staffId");
		TreeModel tm = null;
		List list = new ArrayList();
		Member memberinfo = null;
		/**
		 * 查询我直接推荐的所有会员 返回：会员编号、注册金额、注册时间、团队人数、团队业绩 、(激活/未激活)
		 */
		if (staffid != null && !"".equals(staffid)) {
			// 查询传参用户
		} else {
			// 查询当前登陆用户
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			staffid = staff.getId();
		}
		// 查询子节点
		List<Member> recommendMemberList = this.springMemberService.selectMemberListByStaffid(staffid);
		if (recommendMemberList != null && !recommendMemberList.isEmpty()) {
			for (Member member : recommendMemberList) {
				tm = addstaffmemberNode(member, staffid);
				if (tm != null) {
					list.add(tm);
				}
			}
		}

		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);
		System.out.println("直属关系tree的json值：" + gsonString);

		return gsonString;
	}

	/**
	 * 根据节点查询所有子节点 并相加业绩
	 */
	private String childMemberTotalMoney(String staffid) {
		double totalMoney = 0;
		int count = 0;
		String returnstr = "";
		// 查询出当前父节点下所有子节点
		List<TreeModel> reemodellist = TreeUnit.getChildNodes(memberAlllist(), staffid);
		if (reemodellist != null && !reemodellist.isEmpty()) {
			for (TreeModel treeModel : reemodellist) {
				if (!staffid.equals(treeModel.getId())) {
					totalMoney += treeModel.getMember().getStock().getMoney();
				}
			}
			count = reemodellist.size() - 1;
			// 清空static list 中数据
			TreeUnit.getReturnList().clear();

			returnstr = count + "," + totalMoney;
		}

		return returnstr;
	}

	/**
	 * 查询所有用户封装成所有节点
	 */
	private List<TreeModel> memberAlllist() {
		List<TreeModel> reeemodel = null;
		List<Member> memberlist = this.springMemberService.selectMember();
		if (memberlist != null && !memberlist.isEmpty()) {
			reeemodel = new ArrayList<TreeModel>();
			for (Member member : memberlist) {
				TreeModel model = new TreeModel();
				model.setId(member.getStaffId());
				model.setPid(member.getNote());
				model.setMember(member);
				reeemodel.add(model);
			}
		}
		return reeemodel;
	}

	/**
	 * 封装页面展示node数据
	 * 
	 * @param member
	 *            节点信息（会员信息）
	 * @param staffid
	 *            父节点
	 * @param nodename
	 *            节点name
	 * @param teamnumber
	 *            是否有子节点
	 * @return
	 */
	private TreeModel addstaffmemberNode(Member member, String staffid) {
		String nodename = "";
		// 团队人数
		String teamnumber = "0";
		// 团队业绩
		String teamTotal = "0";
		// 临时变量
		String totalAndCount = "";
		// 查询传入会员编号的所有子节点的 数量和总金额
		// 以逗号隔开，逗号前 count 逗号后total
		totalAndCount = childMemberTotalMoney(member.getStaffId());
		if (totalAndCount != null && !"".equals(totalAndCount)) {
			teamnumber = totalAndCount.split(",")[0];
			teamTotal = totalAndCount.split(",")[1];
		}
		// 节点名称
		nodename = member.getStaffId() + "[" + member.getStock().getMoney() + ","
				+ ConUnit.formateDateToString(member.getCreateTime()) + "," + teamnumber + "," + teamTotal + "]";

		TreeModel tm = new TreeModel();
		tm.setPid(staffid);
		tm.setId(member.getStaffId());
		tm.setName(nodename);
		if (member.getIsActivation() == 0) {
			tm.setIcon(TreeModel.ICOIMG_LS);
			tm.setIconClose(TreeModel.ICOIMG_LS);
			tm.setIconOpen(TreeModel.ICOIMG_LS);
		} else {
			tm.setIcon(TreeModel.ICOIMG_ZS);
			tm.setIconClose(TreeModel.ICOIMG_ZS);
			tm.setIconOpen(TreeModel.ICOIMG_ZS);
		}
		if (Integer.parseInt(teamnumber) > 0) {
			tm.setIsParent(TreeModel.OPEN_TRUE);
		}

		return tm;
	}

	/////////////////////////////网络结构图//////////////////////////////////////////
	
	/**
	 * 查询网络结构图
	 */
	@RequestMapping(value = "/toMemberNetWork.do", method = RequestMethod.GET)
	public String toMemberNetWork(HttpServletRequest request, Model model) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		model.addAttribute("command", staff);
		return "gq/member/memberMap/memberNetWork";
	}
	
}
