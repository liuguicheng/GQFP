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
	 * ��Աͼ�ס�ֱ����ϵͼ
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
	 * ��ѯ�ڵ�
	 */
	@RequestMapping(value = "/xzChildNode.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String xzChildNode(HttpServletRequest request) {
		String staffid = request.getParameter("staffId");
		List list = new ArrayList();
		TreeModel tm = null;
		Member memberinfo = null;
		/**
		 * ��ѯ��ֱ���Ƽ������л�Ա ���أ���Ա��š�ע���ע��ʱ�䡢�Ŷ��������Ŷ�ҵ�� ��(����/δ����)
		 */
		if (staffid != null && !"".equals(staffid)) {
			// ��ѯ�����û�
			memberinfo = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staffid);
		} else {
			// ��ѯ��ǰ��½�û�
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
	 * ֱ����ϵ
	 */
	@RequestMapping(value = "/directoriesRelationship.do", produces = "text/plain;charset=gbk")
	@ResponseBody
	public String directoriesRelationship(HttpServletRequest request) {
		String staffid = request.getParameter("staffId");
		TreeModel tm = null;
		List list = new ArrayList();
		Member memberinfo = null;
		/**
		 * ��ѯ��ֱ���Ƽ������л�Ա ���أ���Ա��š�ע���ע��ʱ�䡢�Ŷ��������Ŷ�ҵ�� ��(����/δ����)
		 */
		if (staffid != null && !"".equals(staffid)) {
			// ��ѯ�����û�
		} else {
			// ��ѯ��ǰ��½�û�
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			staffid = staff.getId();
		}
		// ��ѯ�ӽڵ�
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
		System.out.println("ֱ����ϵtree��jsonֵ��" + gsonString);

		return gsonString;
	}

	/**
	 * ���ݽڵ��ѯ�����ӽڵ� �����ҵ��
	 */
	private String childMemberTotalMoney(String staffid) {
		double totalMoney = 0;
		int count = 0;
		String returnstr = "";
		// ��ѯ����ǰ���ڵ��������ӽڵ�
		List<TreeModel> reemodellist = TreeUnit.getChildNodes(memberAlllist(), staffid);
		if (reemodellist != null && !reemodellist.isEmpty()) {
			for (TreeModel treeModel : reemodellist) {
				if (!staffid.equals(treeModel.getId())) {
					totalMoney += treeModel.getMember().getStock().getMoney();
				}
			}
			count = reemodellist.size() - 1;
			// ���static list ������
			TreeUnit.getReturnList().clear();

			returnstr = count + "," + totalMoney;
		}

		return returnstr;
	}

	/**
	 * ��ѯ�����û���װ�����нڵ�
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
	 * ��װҳ��չʾnode����
	 * 
	 * @param member
	 *            �ڵ���Ϣ����Ա��Ϣ��
	 * @param staffid
	 *            ���ڵ�
	 * @param nodename
	 *            �ڵ�name
	 * @param teamnumber
	 *            �Ƿ����ӽڵ�
	 * @return
	 */
	private TreeModel addstaffmemberNode(Member member, String staffid) {
		String nodename = "";
		// �Ŷ�����
		String teamnumber = "0";
		// �Ŷ�ҵ��
		String teamTotal = "0";
		// ��ʱ����
		String totalAndCount = "";
		// ��ѯ�����Ա��ŵ������ӽڵ�� �������ܽ��
		// �Զ��Ÿ���������ǰ count ���ź�total
		totalAndCount = childMemberTotalMoney(member.getStaffId());
		if (totalAndCount != null && !"".equals(totalAndCount)) {
			teamnumber = totalAndCount.split(",")[0];
			teamTotal = totalAndCount.split(",")[1];
		}
		// �ڵ�����
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

	/////////////////////////////����ṹͼ//////////////////////////////////////////
	
	/**
	 * ��ѯ����ṹͼ
	 */
	@RequestMapping(value = "/toMemberNetWork.do", method = RequestMethod.GET)
	public String toMemberNetWork(HttpServletRequest request, Model model) {
		Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
		model.addAttribute("command", staff);
		return "gq/member/memberMap/memberNetWork";
	}
	
}
