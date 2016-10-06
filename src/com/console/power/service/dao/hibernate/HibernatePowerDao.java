package com.console.power.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.support.QueryUtils;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.console.entity.Power;
import com.console.entity.Role;
import com.console.power.command.PowerQueryInfo;
import com.console.power.service.dao.IPowerDao;

/**
 * The <code>HibernatePowerDao</code> class 是基于 Hibernate 的 IPowerDao 实现.
 */
public class HibernatePowerDao extends HibernateCommonDao implements IPowerDao {

	/**
	 * @see com.console.power.service.dao.IPowerDao#selectAllPower()
	 */
	public List selectAllPower(PowerQueryInfo info) {
		Object[] values = new Object[2];
		int idx = 0;
		StringBuffer hql = new StringBuffer("from ").append(
			Power.class.getName()).append(" where 1=1 ");
		if (info != null) {
			if (info.getPowerCode() != null
					&& info.getPowerCode().trim().length() > 0) {
				hql.append(" and code like ?");
				values[idx++] = "%"
						+ QueryUtils.convertObjectToMultiKeyWord(QueryUtils
							.convertSqlLikePattern(info.getPowerCode())) + "%";
			}
			if (info.getPowerName() != null
					&& info.getPowerName().trim().length() > 0) {
				hql.append(" and name like ?");
				values[idx++] = "%"
						+ QueryUtils.convertObjectToMultiKeyWord(QueryUtils
							.convertSqlLikePattern(info.getPowerName())) + "%";
			}
			if (info.getModuleName() != null
                    && info.getModuleName().trim().length() > 0) {
                    hql.append(" and moduleName = ?");
                    values[idx++] = QueryUtils.convertObjectToMultiKeyWord(QueryUtils
                    .convertSqlLikePattern(info.getModuleName())) ;
    }
		}
		hql.append(" order by code ");

		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		return super.doQuery(hql.toString(), param);
	}

	/**
	 * @see com.console.power.service.dao.IPowerDao#selectChildPower(java.lang.String)
	 */
	public List selectChildPower(String parentPowerCode) {
        int codeLength = Power.LEVEL_LENGTH;

        if (null != parentPowerCode) {
            codeLength = parentPowerCode.length() + Power.LEVEL_LENGTH;
        }
        Object[] values = new Object[5];
        int idx = 0;
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("from ").append(Power.class.getName()).append(
            " as p where LEN(p.code) = ? ");
        values[idx++] = Integer.toString(codeLength);
        if (null != parentPowerCode) {
            queryStr.append(" and p.code like ? ");
             values[idx++] = parentPowerCode + "%";
        }

        queryStr.append(" order by p.code ");
        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
        return super.doQuery(queryStr.toString(),param);
    }


	/**
	 * @see com.console.power.service.dao.IPowerDao#selectRoleByPowerId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Role> selectRoleByPowerId(String powerId) {
		StringBuffer sb = new StringBuffer(255);
		sb.append("select rl from ").append(Role.class.getName()).append(
			" as rl join rl.powers as ps where ps.id = ? ");

		List<Role> list = super.doQuery(sb.toString(),
			new Object[] { powerId });

		return list;
	}

}
