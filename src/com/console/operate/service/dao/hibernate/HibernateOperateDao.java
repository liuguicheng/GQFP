package com.console.operate.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.support.QueryUtils;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.console.entity.Operate;
import com.console.operate.command.OperateQueryInfo;
import com.console.operate.service.dao.IOperateDao;

public class HibernateOperateDao extends HibernateCommonDao implements IOperateDao{

	public List selectAllOperate(OperateQueryInfo info) {
		Object[] values = new Object[2];
        int idx = 0;
        StringBuffer hql = new StringBuffer("from ").append(Operate.class.getName()).append(" where 1=1 ");
        if (info != null) {
            if (info.getOperateCode() != null && info.getOperateCode().trim().length() > 0) {
                hql.append(" and operateCode like ?");
                values[idx++] = "%" + QueryUtils.convertObjectToMultiKeyWord(QueryUtils.convertSqlLikePattern(info.getOperateCode())) + "%";
            }
            if (info.getOperateName() != null && info.getOperateName().trim().length() > 0) {
                hql.append(" and operateName like ?");
                values[idx++] = "%" + QueryUtils.convertObjectToMultiKeyWord(QueryUtils.convertSqlLikePattern(info.getOperateName())) + "%";
            }
        }
        hql.append(" order by operateCode ");

        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
        return super.doQuery(hql.toString(), param);
	}

}
