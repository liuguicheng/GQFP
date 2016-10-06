
package com.plugins.sn.service.dao.hibernate;

import java.util.List;

import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.plugins.sn.entity.SysSerialNumber;
import com.plugins.sn.service.dao.ISerialNumberDao;


public class HibernateSerialNumberDao extends HibernateCommonDao implements ISerialNumberDao {

    /**
     * @see com.plugins.sn.service.dao.ISerialNumberDao#selectSysSerialNumber(java.lang.String, java.lang.String)
     */
    public List selectSysSerialNumber(String classifyIdentifier, String prefixIdentifier) {
        return super.doQuery(new StringBuffer(" from ").append(SysSerialNumber.class.getName())
                .append(" as sn where sn.classifyIdentifier=?")
                .append(" and sn.prefixIdentifier=?").toString()
                ,new Object[]{classifyIdentifier,prefixIdentifier});
    }

}
