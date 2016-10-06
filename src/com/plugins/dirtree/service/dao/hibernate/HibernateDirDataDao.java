package com.plugins.dirtree.service.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.internal.util.collections.EmptyIterator;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.plugins.dirtree.entity.DirData;
import com.plugins.dirtree.service.dao.IDirDataDao;

public class HibernateDirDataDao extends HibernateCommonDao implements
		IDirDataDao {

	public List selectDirDataBy(String typeid, String parentid, String isvalid) {
		// TODO Auto-generated method stub
	    Object[] values = new Object[10];
        int idx = 0;
		StringBuffer sql = new StringBuffer("from " + DirData.class.getName()
				+ " as dt where 1=1 ");

		if (typeid != null && typeid.trim().length() > 0){
			sql.append(" and dt.dirTypeId = ?"  );
		    values[idx++] = typeid;
		}
		if (parentid != null && parentid.trim().length() > 0) {
				sql.append(" and dt.parentId = ? and dt.dirId != ?"  );
				 values[idx++] = parentid;
				 values[idx++] = parentid;
	    } else {
				sql.append(" and (dt.parentId = '' or dt.parentId is null or dt.dirId = dt.parentId) ");
		}
		if (isvalid != null && isvalid.trim().length() > 0){
			sql.append(" and dt.isValid = ?" );
		    values[idx++] = isvalid;
		}

		sql.append("  order by dt.sortOrder ");
		Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
		return doQuery(sql.toString(),param);
	}

	@Override
	public List selectDirData(String typeid, String isvalid) {
		// TODO Auto-generated method stub
	    Object[] values = new Object[2];
        int idx = 0;
		StringBuffer sql = new StringBuffer("from " + DirData.class.getName()
				+ " as dt where 1=1 ");

		if (typeid != null && typeid.trim().length() > 0){
			sql.append(" and dt.dirTypeId = ?" );
			values[idx++] = typeid;
        }
		if (isvalid != null && isvalid.trim().length() > 0){
			sql.append(" and dt.isValid = ?"  );
			values[idx++] = isvalid;
		}

		sql.append("  order by dt.sortOrder ");
		Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
		return doQuery(sql.toString(),param);
	}

	@Override
	public Integer selectUsableOrderNumber(String typeId,String parentId) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select max(dt.sortOrder) from ")
				.append(DirData.class.getName()).append(
						" as dt where dt.parentId =?").append(" and dt.dirTypeId = ?").append(
						" and dt.dirId != ?").append(" and dt.isValid=?");

		Iterator it = super.iterate(hql.toString(),
				new Object[] { parentId,typeId, parentId ,DirData.NORMAL_STATE});
		int number = 1;
		if (it.hasNext()) {
			try {
				number = ((Integer) it.next()).intValue();
				number++;
			} catch (Exception ex) {
			}
		}
		if (!(it instanceof EmptyIterator)) {
			super.closeIterator(it);
		}
		return new Integer(number);
	}
}
