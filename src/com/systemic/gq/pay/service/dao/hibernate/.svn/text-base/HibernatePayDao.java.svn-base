package com.systemic.gq.pay.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.pay.command.PayQueryInfo;
import com.systemic.gq.pay.service.dao.IPayDao;

public class HibernatePayDao extends HibernateCommonDao implements IPayDao {
	IQueryStringUtil payQueryStringUtil;
	
	public void setPayQueryStringUtil(IQueryStringUtil payQueryStringUtil) {
		this.payQueryStringUtil = payQueryStringUtil;
	}

	@Override
	public Page selectPay(PayQueryInfo info) {
		Object[] values = new Object[25];
        int idx = 0;
        StringBuffer select = new StringBuffer(" from ").append(PayInfo.class.getName()).append(" as me ");
        StringBuffer where = new StringBuffer(200);
       /* if (info.getMemberId() != null && info.getMemberId().trim().length() > 0) {
            where.append(" a.memberId like ?    and ");
            values[idx++] =  "%"+info.getMemberId()+"%" ;
        }*/
        
        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
		IQueryObject io = this.payQueryStringUtil.getQueryObject(info,where.toString(), param);
		select.append(" where ").append(io.getWhereClause());
        select.append(" order by ");
        select.append(" me.applyTime desc  ");
		if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List data = super.doQuery(select.toString(), io.getParam());
            info.setNotPage(false);
            return super.putDataToPage(data);
        }
		return super.find(io.getQueryString(), io.getParam(), info.getPageNumber(), info.getPageSize());
	}

}
