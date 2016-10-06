package com.systemic.gq.member.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.entity.Member;
import com.systemic.gq.member.command.MemberInfo;
import com.systemic.gq.member.service.dao.IMemberDao;

public class HibernateMemberDao extends HibernateCommonDao implements
		IMemberDao {
	private IQueryStringUtil memberQueryStringUtil;

	public void setMemberQueryStringUtil(IQueryStringUtil memberQueryStringUtil) {
		this.memberQueryStringUtil = memberQueryStringUtil;
	}

	@Override
	public Page selectMeber(MemberInfo info) {
		Object[] values = new Object[25];
        int idx = 0;
        StringBuffer select = new StringBuffer(" from ").append(PayInfo.class.getName()).append(" as me ");
        StringBuffer where = new StringBuffer(200);
        
        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
		IQueryObject io = this.memberQueryStringUtil.getQueryObject(info,where.toString(), param);
		select.append(" where ").append(io.getWhereClause());
        
		if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List data = super.doQuery(select.toString(), io.getParam());
            info.setNotPage(false);
            return super.putDataToPage(data);
        }
		
		return super.find(io.getQueryString(), io.getParam(),
				info.getPageNumber(), info.getPageSize());
	}

	@Override
	public List<Member> selectMemberBy(MemberInfo info) {
		 
		 	Object[] values = new Object[5];
	        int idx = 0;
	        StringBuffer queryStr = new StringBuffer();
	        queryStr.append("from ").append(Member.class.getName()).append(
	            " as m where 1=1 ");
	        if(info!=null){
				if(info.getReferenceId()!=null&&!"".equals(info.getReferenceId())){
					 queryStr.append(" and m.reference_id = ? ");
		             values[idx++] = info.getReferenceId() ;
				}
				if(info.getRegion()!=null&&!"".equals(info.getRegion())){
					 queryStr.append(" and m.region = ? ");
		             values[idx++] = info.getRegion() ;
				}
				if(info.getNote()!=null&&!"".equals(info.getNote())){
					 queryStr.append(" and m.note = ? ");
		             values[idx++] = info.getNote() ;
				}
				if(info.getMemberId()!=null&&!"".equals(info.getMemberId())){
					queryStr.append(" and m.memberId != ? ");
		             values[idx++] = info.getMemberId() ;
				}
				if(info.getCreateTime()!=null){
					queryStr.append(" and datediff(NOW(),m.createTime)>30 ");
		            // values[idx++] = info.getCreateTime() ;
				}
				
			}
	        Object[] param = new Object[idx];
	        System.arraycopy(values, 0, param, 0, idx);
	        List list= super.doQuery(queryStr.toString(),param);
		if(!list.isEmpty()){
			return  list;
		}
		return null;
	}

	@Override
	public Member selectMemberByStaffid(String referenceId) {
		Object[] values = new Object[5];
        int idx = 0;
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("from ").append(Member.class.getName()).append(
            " as m where  m.staffId =?");
	         values[idx++] = referenceId ;
			
        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
        List list= super.doQuery(queryStr.toString(),param);
	if(!list.isEmpty()){
		return  (Member) list.get(0);
	}
	return null;
	}

}