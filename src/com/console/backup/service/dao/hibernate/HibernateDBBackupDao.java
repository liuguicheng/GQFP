package com.console.backup.service.dao.hibernate;


import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.console.backup.command.DBBackupInfo;
import com.console.backup.service.dao.IDBBackupDAO;
import com.console.entity.DBBackup;
import com.systemic.gq.entity.Withdrawals;

public class HibernateDBBackupDao extends HibernateCommonDao implements IDBBackupDAO {
	
	private IQueryStringUtil dbbackupQueryStringUtil;
	
	
	public void setDbbackupQueryStringUtil(IQueryStringUtil dbbackupQueryStringUtil) {
		this.dbbackupQueryStringUtil = dbbackupQueryStringUtil;
	}
	

	@Override
	public Page selectBackupList(DBBackupInfo dbbackinfo) {
		
		Object[] values = new Object[25];
		int idx = 0;
		StringBuffer select = new StringBuffer(" from ").append(DBBackup.class.getName()).append(" as me ");
		StringBuffer where = new StringBuffer(200);

		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		IQueryObject io = this.dbbackupQueryStringUtil.getQueryObject(dbbackinfo, where.toString(), param);
		select.append(" where ").append(io.getWhereClause());
		select.append(" order by ");
		select.append(" me.db_time desc  ");
		if (dbbackinfo.getNotPage() != null && dbbackinfo.getNotPage().booleanValue()) {
			List data = super.doQuery(select.toString(), io.getParam());
			dbbackinfo.setNotPage(false);
			return super.putDataToPage(data);
		}
		return super.find(io.getQueryString(), io.getParam(), dbbackinfo.getPageNumber(), dbbackinfo.getPageSize());

	}

}
