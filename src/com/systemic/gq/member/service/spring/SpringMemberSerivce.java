package com.systemic.gq.member.service.spring;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.utils.EncryptHelper;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.console.entity.Role;
import com.console.entity.Staff;
import com.console.entity.StaffSecurity;
import com.plugins.sn.service.SNHelper;
import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberEditInfo;
import com.systemic.gq.member.command.MemberInfo;
import com.systemic.gq.member.service.ISpringMemberService;
import com.systemic.gq.member.service.dao.IMemberDao;

public class SpringMemberSerivce implements ISpringMemberService {
	private IMemberDao memberDao;

	public void setMemberDao(IMemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public Page selectMeber(MemberInfo info) {

		return this.memberDao.selectMeber(info);
	}

	@Override
	public Member loadMermber(String id) {

		return (Member) this.memberDao.load(Member.class, id);
	}

	@Override
	public void saveMermber(MemberEditInfo info) {
		Member member = new Member();
		if (StringUtils.isNotBlank(info.getId())) {
			BeanUtils.copyProperties(info, member);
		} else {
			//������½�˺�
			Staff staff = new Staff();
			staff.setId(SNHelper.getSNService().getSerialNumber(
					Staff.class.getName(), "id", false));
			staff.setName(info.getUserName());
			
			// �жϵ�¼���Ƿ�ʹ��
			Staff tmp = ConsoleHelper.getInstance().getMainService().selectAllStaff(info.getUserName());
			if (tmp != null && !tmp.getId().equals(info.getId())) {
				throw new RuntimeException("��¼����"
						+ info.getUserName() + "���ѱ�ʹ�ã�");
			}
			BeanUtils.copyProperties(info, staff, new String[] { "id",
					"password" });
			BeanUtils.copyProperties(info, member, new String[] { "memberId" });
			staff.setLoginName(info.getUserName());//��¼��
			staff.setPassword(info.getPassword());//����
			// �������룬������
			if (info.getPassword() != null
					&& info.getPassword().trim().length() > 0) {
			    //�ж������Ƿ�������������CHANGE_TIME�ֶ�
			    if(!staff.getPassword().equals(EncryptHelper.md5Encoding(info.getPassword()))){
			        StaffSecurity ss = ConsoleHelper.getInstance().getMainService().loadStaffSecurity(staff.getId());
		            ss.setChangeTime(new Date());
		            this.memberDao.update(ss);
	             }
	            staff.setPassword(EncryptHelper
	                .md5Encoding(info.getPassword()));
	            member.setPassword(EncryptHelper
		                .md5Encoding(info.getPassword()));
			}
			if(info.getPasswodTwo()!=null&&info.getPasswodTwo().trim().length()>0){
				 member.setPasswodTwo(EncryptHelper
			                .md5Encoding(info.getPasswodTwo()));
			}
			if(info.getPasswordThree()!=null&&info.getPasswordThree().trim().length()>0){
				 member.setPasswordThree(EncryptHelper
			                .md5Encoding(info.getPasswordThree()));
			}
			Set<Role> set = new HashSet<Role>();
			Role role = (Role) this.memberDao.load(Role.class, "21");//21Ϊ��Ա��ɫID
			set.add(role);
			staff.setSystemRole(set);
			staff.setDepartment(ConsoleHelper.getInstance().getMainService().selectDepartment("1"));
			staff.setValid(ConsoleHelper.YES);
			staff.setSysTemplate(ConsoleHelper.YES);
			this.memberDao.save(staff);
			CacheHelper.getInstance().dispatchRefreshEvent(Staff.SIMPLE_DIC_IDENTIFICATION);
			member.setStaffId(staff.getId());//ϵͳ��¼
			// TODO: Animate this.group instead ѡ���Ȩ�ȼ������� ʹ��Stock id������
			member.setProductgradeId(info.getProductgradeId());
			member.setCreateTime(new Date());
			member.setRegion(info.getRegion());
			member.setIsok(1);
		} 
		this.memberDao.save(member);
		CacheHelper.getInstance().dispatchRefreshEvent(Member.SIMPLE_DIC_IDENTIFICATION);
		
	}

	@Override
	public void deleteMember(String[] ids) {
			for (String id :ids ) {
				Member member = this.loadMermber(id);
				if(member != null){
					this.memberDao.delete(member);
				}
				try {
					ConsoleHelper.getInstance().getManageService().deleteStaff(member.getStaffId());
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ɾ����Ա����");
					throw new RuntimeException("ɾ����Ա����!����ϵ������Ա!"+this.getClass());
				}
			}
			// TODO: Animate this.group instead 
	}

	@Override
	public List<Member> selectMemberBy(MemberInfo info) {
		return this.memberDao.selectMemberBy(info);
	}

	@Override
	public void updateMermber(Member member) {
		
		if(member.getPassword()!=null&&!"".equals(member.getPassword().trim())){
			
			StaffSecurity ss = ConsoleHelper.getInstance().getMainService().loadStaffSecurity(member.getStaffId());
			if(ss!=null){
				ss.setChangeTime(new Date());
				this.memberDao.update(ss);
			}
            Staff staff=ConsoleHelper.getInstance().getMainService().selectStaffById(member.getStaffId());
            if(staff!=null){
            	ConsoleHelper.getInstance().getMainService().updatePassword(staff, member.getPassword());
            	CacheHelper.getInstance().dispatchRefreshEvent(Staff.SIMPLE_DIC_IDENTIFICATION);
            }
            member.setPassword(EncryptHelper
	                .md5Encoding(member.getPassword()));
            
		}
		
		memberDao.update(member);
		CacheHelper.getInstance().dispatchRefreshEvent(Member.SIMPLE_DIC_IDENTIFICATION);
	}

	@Override
	public Member selectMemberByStaffid(String referenceId) {
		// TODO Auto-generated method stub
		return memberDao.selectMemberByStaffid(referenceId);
	}

	@Override
	public void updateMermberInfo(Member member) {
		this.memberDao.save(member);
		CacheHelper.getInstance().dispatchRefreshEvent(Member.SIMPLE_DIC_IDENTIFICATION);
	}

	
}