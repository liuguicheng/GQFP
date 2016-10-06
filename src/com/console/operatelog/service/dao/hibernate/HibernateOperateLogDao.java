package com.console.operatelog.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.console.operatelog.command.OperateLogQueryInfo;
import com.console.operatelog.service.dao.IOperateLogDao;

public class HibernateOperateLogDao extends HibernateCommonDao implements IOperateLogDao{


	private IQueryStringUtil operateLogQueryUtil;

	/**
	 * @param operateLogQueryUtil the operateLogQueryUtil to set
	 */
	public void setOperateLogQueryUtil(IQueryStringUtil operateLogQueryUtil) {
		this.operateLogQueryUtil = operateLogQueryUtil;
	}

	/**
	 * 日志信息分页
	 * @see com.console.operatelog.service.dao.IOperateLogDao#selectOperateLogList(com.console.operatelog.command.OperateLogQueryInfo)
	 */
	public Page selectOperateLogList(OperateLogQueryInfo info) {
		IQueryObject qo = this.operateLogQueryUtil.getQueryObject(info);
		if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List data = super.doQuery(qo.getQueryString(), qo.getParam());
            return super.putDataToPage(data);
        }
		return super.find(qo.getQueryString(), qo.getParam(),info.getPageNumber().intValue());
	}

}
