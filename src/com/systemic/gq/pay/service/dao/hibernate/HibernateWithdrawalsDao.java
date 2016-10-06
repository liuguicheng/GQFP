package com.systemic.gq.pay.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Withdrawals;
import com.systemic.gq.pay.command.WithdrawalsQueryInfo;
import com.systemic.gq.pay.service.dao.IWithdrawalsDao;

public class HibernateWithdrawalsDao extends HibernateCommonDao implements IWithdrawalsDao {
	IQueryStringUtil withdrawalsQueryStringUtil;

	/**
	 * @param withdrawalsQueryStringUtil
	 *            the withdrawalsQueryStringUtil to set
	 */
	public void setWithdrawalsQueryStringUtil(IQueryStringUtil withdrawalsQueryStringUtil) {
		this.withdrawalsQueryStringUtil = withdrawalsQueryStringUtil;
	}

	@Override
	public Page selectWithdrawalsLog(WithdrawalsQueryInfo info) {
		Object[] values = new Object[25];
		int idx = 0;
		StringBuffer select = new StringBuffer(" from ").append(Withdrawals.class.getName()).append(" as me ");
		StringBuffer where = new StringBuffer(200);

		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		IQueryObject io = this.withdrawalsQueryStringUtil.getQueryObject(info, where.toString(), param);
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

	@Override
	public List<Withdrawals> selectWithdrawalsList(WithdrawalsQueryInfo info) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(Withdrawals.class.getName()).append(" as m where 1=1 ");
		if (info != null) {
			if (info.getApplyTime() != null) {
				queryStr.append(" and datediff(NOW(),m.applyTime)=0  ");
			}
			Object[] param = new Object[idx];
			System.arraycopy(values, 0, param, 0, idx);
			List list = super.doQuery(queryStr.toString(), param);
			if (!list.isEmpty()) {
				return list;
			}
		}
		return null;
	}

}
