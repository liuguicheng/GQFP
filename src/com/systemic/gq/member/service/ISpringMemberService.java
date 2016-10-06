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
	void saveMermber(MemberEditInfo info);
	/**删除会员*/
	void deleteMember(String[] id);
	
	List<Member> selectMemberBy(MemberInfo info);
	/**更新会员*/
	void updateMermber(Member member);
	/**根据登陆id 查询会员信息 */
	Member selectMemberByStaffid(String referenceId);
	void updateMermberInfo(Member member);
}
