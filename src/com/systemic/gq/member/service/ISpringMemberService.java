package com.systemic.gq.member.service;

import java.util.List;

import org.springline.orm.Page;

import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberEditInfo;
import com.systemic.gq.member.command.MemberInfo;

public interface ISpringMemberService {
	/** ��ҳ��ѯ��Ա */
	Page selectMeber(MemberInfo info);
	/**����ID��ȡһ������*/
	Member loadMermber(String id);
	/**�����Ա*/
	void saveMermber(MemberEditInfo info);
	/**ɾ����Ա*/
	void deleteMember(String[] id);
	
	List<Member> selectMemberBy(MemberInfo info);
	/**���»�Ա*/
	void updateMermber(Member member);
	/**���ݵ�½id ��ѯ��Ա��Ϣ */
	Member selectMemberByStaffid(String referenceId);
	void updateMermberInfo(Member member);
}
