package com.systemic.gq.pay.service.dao.hibernate;


import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.entity.TransferAccounts;
import com.systemic.gq.entity.Withdrawals;
import com.systemic.gq.pay.command.TransferAccountsQueryInfo;
import com.systemic.gq.pay.service.dao.ITransferAccountsDao;

public  class HibernateTransferAccountsDao extends HibernateCommonDao implements ITransferAccountsDao {

	IQueryStringUtil transferAccountStringUtil;
	

	/**
	 * @param transferAccountStringUtil the transferAccountStringUtil to set
	 */
	public void setTransferAccountStringUtil(IQueryStringUtil transferAccountStringUtil) {
		this.transferAccountStringUtil = transferAccountStringUtil;
	}


	@Override
	public Page selectTransferAccountsPage(TransferAccountsQueryInfo raqinfo) {
		Object[] values = new Object[25];
        int idx = 0;
        StringBuffer select = new StringBuffer(" from ").append(TransferAccounts.class.getName()).append(" as pl ");
        StringBuffer where = new StringBuffer(200);
       if (raqinfo.getTaTurnoutAccount() != null && raqinfo.getTaTurnoutAccount().trim().length() > 0) {
            where.append(" pl.taTurnoutAccount=?     ");
            values[idx++] =  raqinfo.getTaTurnoutAccount();
        }
        
        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
		IQueryObject io = this.transferAccountStringUtil.getQueryObject(raqinfo,where.toString(), param);
		select.append(" where ").append(io.getWhereClause());
//        select.append(" order by ");
//        select.append(" me.applyTime desc  ");
		if (raqinfo.getNotPage() != null && raqinfo.getNotPage().booleanValue()) {
            List data = super.doQuery(select.toString(), io.getParam());
            raqinfo.setNotPage(false);
            return super.putDataToPage(data);
        }
		return super.find(io.getQueryString(), io.getParam(), raqinfo.getPageNumber(), raqinfo.getPageSize());
	
	}
	
	public List<TransferAccounts> selectTransferAccountsList(TransferAccountsQueryInfo info){
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append("from ").append(TransferAccounts.class.getName()).append(" as m where 1=1 ");
		if (info != null) {
			if (info.getTaTime() != null) {
				queryStr.append(" and datediff(NOW(),m.taTime)=0  ");
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
