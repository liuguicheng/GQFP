package com.systemic.gq.pay.service.dao.hibernate;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.pay.command.PayLogQueryInfo;
import com.systemic.gq.pay.service.dao.IPayLogDao;

public class HibernatePayLogDao extends HibernateCommonDao implements
		IPayLogDao {
	IQueryStringUtil payLogQueryStringUtil;
	
	public void setPayLogQueryStringUtil(IQueryStringUtil payLogQueryStringUtil) {
		this.payLogQueryStringUtil = payLogQueryStringUtil;
	}

	@Override
	public Page selectPayLog(PayLogQueryInfo info) {
		IQueryObject qo = this.payLogQueryStringUtil.getQueryObject(info);
		return super.find(qo.getQueryString(), qo.getParam(), info.getPageNumber(), info.getPageSize());
	}

}
