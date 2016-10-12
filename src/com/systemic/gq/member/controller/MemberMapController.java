package com.systemic.gq.member.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.systemic.gq.entity.Rule;
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
//		System.out.println("直属关系tree的json值：" + gsonString);

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

	////////////////////////////////////// 网络结构图/////////////////////////////////////////////////////////////////////////

	/**
	 * 跳转网络结构图页面
	 */
	@RequestMapping(value = "/toMemberNetWork.do", method = RequestMethod.GET)
	public String toMemberNetWork(HttpServletRequest request, Model model) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		model.addAttribute("command", staff);
		return "gq/member/memberMap/memberNetWork";
	}

	/**
	 * 查询网络结构图
	 */
	@RequestMapping(value = "/selectMemberNetWork.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String selectMemberNetWor(HttpServletRequest request) {
		String staffid = request.getParameter("staffId");// 会员id
		String cennum = request.getParameter("cennum");// 展现层数
		Member memberinfo = null;
		List<Member> tempList = new ArrayList<Member>();
		if (staffid != null && !"".equals(staffid)) {
			// 查询传参用户
			memberinfo = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffid);
		} else {
			// 查询当前登陆用户
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			staffid = staff.getId();
			memberinfo = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
		}
		//添加根节点
		tempList.add(memberinfo);
		// 循环查询几层
		switch (cennum) {
		case "2":
			List<Member> ndoeMemberlist = this.springMemberService.selectMemberListByNode(memberinfo.getStaffId());
			if (ndoeMemberlist != null && !ndoeMemberlist.isEmpty()) {
				for (Member member : ndoeMemberlist) {
					tempList.add(member);
				}
				ndoeMemberlist = null;
			}
			break;
		case "3":
			 ndoeMemberlist = this.springMemberService.selectMemberListByNode(memberinfo.getStaffId());
			if (ndoeMemberlist != null && !ndoeMemberlist.isEmpty()) {
				for (Member member : ndoeMemberlist) {
					tempList.add(member);
					List<Member> ndoeMemberlist3_1 = this.springMemberService.selectMemberListByNode(member.getStaffId());
					if (ndoeMemberlist3_1 != null && !ndoeMemberlist3_1.isEmpty()) {
						for (Member member3_1 : ndoeMemberlist3_1) {
							tempList.add(member3_1);
						}
						ndoeMemberlist3_1 = null;
					}
				}
				ndoeMemberlist = null;
			}
			break;
		case "4":
			ndoeMemberlist = this.springMemberService.selectMemberListByNode(memberinfo.getStaffId());
			if (ndoeMemberlist != null && !ndoeMemberlist.isEmpty()) {
				for (Member member : ndoeMemberlist) {
					tempList.add(member);
					List<Member> ndoeMemberlist3_1 = this.springMemberService.selectMemberListByNode(member.getStaffId());
					if (ndoeMemberlist3_1 != null && !ndoeMemberlist3_1.isEmpty()) {
						for (Member member3_1 : ndoeMemberlist3_1) {
							tempList.add(member3_1);
							
							List<Member> ndoeMemberlist4_1 = this.springMemberService.selectMemberListByNode(member3_1.getStaffId());
							if (ndoeMemberlist4_1 != null && !ndoeMemberlist4_1.isEmpty()) {
								for (Member member4 : ndoeMemberlist4_1) {
									tempList.add(member4);
								}
								ndoeMemberlist4_1 = null;
							}
						}
						ndoeMemberlist = null;
					}
				}
				ndoeMemberlist = null;
			}
			break;		
		default:
			break;
		}
		//查询节点 统计
		List list = selectNode(tempList,Integer.parseInt(cennum));

		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);

		return gsonString;
	}

	private List selectNode(List<Member> tempList,int cen) {
		TreeModel tm;
		List list = new ArrayList();
		Rule reule = ConsoleHelper.getInstance().getRuleService().selectRuleBY();
		// 系统区域数量
		int reginNum = reule.getAreaNumber();
		//传入 最大td数量和系统区域
		tm = regionNumAndMaxTd(cen, reginNum);
		list.add(tm);
		
		if (tempList != null && !tempList.isEmpty()) {
			for (Member member : tempList) {
				tm = settlementNode(member,reginNum);
				if (tm != null) {
					list.add(tm);
				}
			}
		}
		return list;
	}

	public TreeModel regionNumAndMaxTd(int cen, int reginNum) {
		TreeModel tm;
		double totalTd=Math.pow(reginNum, (cen - 1));
		tm=new TreeModel();
		tm.setRegion(String.valueOf(reginNum));
		tm.setTdnum(String.valueOf(totalTd));
		tm.setTrnuml(String.valueOf(cen));
		return tm;
	}

	private TreeModel settlementNode(Member memberinfo,int reginNum) {
		String regionStr;
		TreeModel tm;
		Member nodeRegionMember;
		String temp = "";// 临时变量
		String temp2 = "";// 临时变量
		String temp3 = "";// 临时变量
		String temp4 = "";// 临时变量
		for (int i = 0; i < reginNum; i++) {
			// 根据当前节点id，区域id： 查询当前节点下规定区域节点 信息
			nodeRegionMember = this.springMemberService.selectMemberByNode(memberinfo.getStaffId(), String.valueOf(i));
			if (nodeRegionMember != null) {
				temp = nodechildMemberTotalMoney(nodeRegionMember.getStaffId());
				if (temp != null && !temp.equals("")) {
					temp2 += "区域" + (i + 1) + ":" + temp.split(",")[1]+" ";
					temp3 += "区域" + (i + 1) + "单数:" + temp.split(",")[0]+" ";
					temp4 += "区域" + (i + 1) + "新增:"+temp.split(",")[2]+" ";
				}
			} else {
				temp2 += "区域" + (i + 1) + ":0"+" ";
				temp3 += "区域" + (i + 1) + "单数:0"+" ";
				temp4 += "区域" + (i + 1) + "新增:0.0"+" ";
			}

		}
		regionStr = temp2 + "</br>" + temp4 + "</br>" + temp3 + "</br>";

		tm = addstaffmemberNetWorkNode(memberinfo, regionStr);
		return tm;
	}

	private TreeModel addstaffmemberNetWorkNode(Member member, String regionStr) {
		/**
		 * 查询我网络图 返回：会员编号、区域、团队业绩 、区域总业绩、区域人数、(激活/未激活)、类型 nodename
		 * 例子：总业绩：60000.00 <br>
		 * </a>左区：60000.00 中区 ：0.00 右区 ：0.00<br/>
		 * 左区新增：0.00 中区新增 ：0.00 右区新增 ：0.00<br/>
		 * 左区单数：2 中区单数：0 右区单数：0 <br/>
		 */
		// 节点 title
		String nodename = "";
		// 团队业绩
		String teamTotal = "0";
		// 临时变量
		String totalAndCount = "";
		// 查询传入会员编号的所有子节点的 数量和总金额! 以逗号隔开，逗号前 count 逗号后total
		totalAndCount = childMemberTotalMoney(member.getStaffId());
		if (totalAndCount != null && !"".equals(totalAndCount)) {
			teamTotal = totalAndCount.split(",")[1];
		}

		nodename = "总业绩：" + teamTotal + " <br></a>" + regionStr + " <br/>";

		TreeModel tm = new TreeModel();
		tm.setPid(member.getReferenceId());
		tm.setId(member.getStaffId());
		tm.setName(nodename);
		tm.setRegion(member.getRegion());
		tm.setConnectionId(member.getNote());
		if (member.getIsActivation() == 0) {
			tm.setIcon(TreeModel.NETWORK_Activation_NO);
		} else {
			tm.setIcon(TreeModel.NETWORK_Activation_YES);
		}
		return tm;
	}

	private String nodechildMemberTotalMoney(String staffid) {
		double totalMoney = 0;
		double newAddTotalMoney=0;
		int count = 0;
		String returnstr = "";
		// 查询出当前父节点下所有子节点
		List<TreeModel> reemodellist = TreeUnit.getChildNodes(memberAlllist(), staffid);
		if (reemodellist != null && !reemodellist.isEmpty()) {
			for (TreeModel treeModel : reemodellist) {
				totalMoney += treeModel.getMember().getStock().getMoney();
				if(ConUnit.isSameDate(treeModel.getMember().getCreateTime(), new Date())){
					newAddTotalMoney+=treeModel.getMember().getStock().getMoney();
				}
			}
			count = reemodellist.size();
			// 清空static list 中数据
			TreeUnit.getReturnList().clear();

			returnstr = count + "," + totalMoney+","+newAddTotalMoney;
		}

		return returnstr;
	}

	
	//////////////////////////////////////////////////业绩查询/////////////////////////////////////////
	
	@RequestMapping(value = "/toMemberPerformanceQuery.do", method = RequestMethod.GET)
	public String toMemberPerformanceQuery(HttpServletRequest request, Model model) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		model.addAttribute("command", staff);
		return "gq/member/memberPerformanceQuery";
	}
	
	/**
	 * 业绩查询
	 */
	@RequestMapping(value = "/doMemberPerformanceQuery.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String MemberPerformanceQuery(HttpServletRequest request) {
		String staffid = request.getParameter("staffId");
		TreeModel tm = null;
		List list = new ArrayList();
		Member memberinfo = null;
		/**
		 * 查询我直接推荐的所有会员 返回：会员编号、注册金额、注册时间、团队人数、团队业绩 、(激活/未激活)
		 */
		if (staffid != null && !"".equals(staffid)) {
			// 查询传参用户
			memberinfo= ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffid);
		} else {
			// 查询当前登陆用户
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			staffid = staff.getId();
			memberinfo= ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffid);
		}
		// 查询子节点
		List<Member> recommendMemberList = this.springMemberService.selectMemberListByStaffid(staffid);
		if (recommendMemberList != null && !recommendMemberList.isEmpty()) {
			recommendMemberList.add(memberinfo);
		}else{
			recommendMemberList=new ArrayList<Member>();
			recommendMemberList.add(memberinfo);
		}
		
		for (Member member : recommendMemberList) {
			tm = addMemberPerformance(member, staffid);
			if (tm != null) {
				list.add(tm);
			}
		}
		
		
		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);

		return gsonString;
	}
	
	/**
	 * 封装查询到的业绩
	 * @param member 查询会员
	 * @param staffid 父节点
	 * @return
	 */
	private TreeModel addMemberPerformance(Member member, String staffid) {
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String nodename = "";
			// 团队人数
			String teamnumber = "0";
			// 团队业绩
			String teamTotal = "0";
			int ztnum=0;//直推人数
			double zttotalmoney=0;//直推金额
			double newtotalmoeny=0;//今日新增
			// 临时变量
			String totalAndCount = "";
			// 查询传入会员编号的所有子节点的 数量和总金额
			// 以逗号隔开，逗号前 count 逗号后total
			totalAndCount = childMemberTotalMoney(member.getStaffId());
			if (totalAndCount != null && !"".equals(totalAndCount)) {
				teamnumber = totalAndCount.split(",")[0];
				teamTotal = totalAndCount.split(",")[1];
			}
			List<Member> recommendMemberList = this.springMemberService.selectMemberListByStaffid(staffid);
			if (recommendMemberList != null && !recommendMemberList.isEmpty()) {
				for (Member member2 : recommendMemberList) {
					zttotalmoney+=member2.getStock().getMoney();
					if(ConUnit.isSameDate(member2.getCreateTime(), new Date())){
						newtotalmoeny+=member2.getStock().getMoney();
					}
				}
				ztnum=recommendMemberList.size();
			}
			recommendMemberList=null;
			// 案例：直接开户共2单，60000.00团队共2单，60000.00，当日新增业绩0.00
			nodename = "直接开户共"+ztnum+"单,"+zttotalmoney+"<br/>团队共"+teamnumber+"单,"+teamTotal+"当日新增"+newtotalmoeny;
					

			TreeModel tm = new TreeModel();
			tm.setPid(staffid);
			tm.setId(member.getStaffId());
			tm.setName(nodename);
			
			tm.setRegisterTime(sdf.format(member.getCreateTime()));
			if(member.getIsActivation()==0){
				tm.setActivationTime("");
				tm.setStatus("投资金额:"+member.getStock().getMoney()+"(未激活)");
			}else{
				tm.setActivationTime(sdf.format(member.getActivationTime()));
				tm.setStatus("投资金额:"+member.getStock().getMoney()+"(已激活)");
			}
			return tm;
	}
	
}
