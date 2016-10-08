package com.systemic.gq.member.service;

import java.util.List;

import org.springline.orm.Page;

import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberEditInfo;
import com.systemic.gq.member.command.MemberInfo;

public interface ISpringMemberService {
	/** 分页查询会员 */
	Page selectMeber(MemberInfo info);
	/**根据ID获取一条数据*/
	Member loadMermber(String id);
	/**保存会员*/
	Member saveMermber(MemberEditInfo info);
	/**删除会员*/
	void deleteMember(String[] id);
	
	List<Member> selectMemberBy(MemberInfo info);
	/**更新会员 并更新登陆账号密码*/
	void updateMermber(Member member);
	/**根据登陆id 查询会员信息 */
	Member selectMemberByStaffid(String referenceId);
	/**更新会员其他信息*/
	void updateMermberInfo(Member member);
	/**根据会员id 查询会员信息 */
	Member selectMemberById(String memberId);
	/**根据推荐人查询所有推荐会员*/
	List<Member> selectMemberListByStaffid(String id);
	/**
	 * 查询被推荐人下面推荐的人数总和
	 * @param staffId
	 * @return
	 */
	int selectMemberCountByStaffid(String staffId);
	/**
	 * 查询被推荐人下推荐人的总金额
	 * @param staffId
	 * @return
	 */
	double selectMemberMoneyTotalByStaffid(String staffId);
	/**
	 * 查询所有用户
	 * @return
	 */
	List<Member> selectMember();
}
