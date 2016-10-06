package com.plugins.dirtree.service.dao.hibernate;


import java.util.Iterator;
import java.util.List;

import org.hibernate.internal.util.collections.EmptyIterator;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.plugins.dirtree.entity.DirType;
import com.plugins.dirtree.service.dao.IDirTypeDao;

public class HibernateDirTypeDao extends HibernateCommonDao implements
		IDirTypeDao {

	public boolean checkEditDirTypeCode(String oldTypeCode, String newTypeCode) {
		// TODO Auto-generated method stub
		 boolean flag = false;
	        //当进行修改时，先用<>过滤掉旧的typeCode数据，再根据传入的新的typeCode值进行匹配
	        String hql = "from " + DirType.class.getName() 
	                + " as dt where dt.dirTypeCode<>?  and dt.dirTypeCode=?" ;
	        List list = doQuery(hql,new Object[]{oldTypeCode,newTypeCode});
	        if (list.size() > 0) {
	            flag = true;
	        }
	        return flag;
	}

	public List selectValidDirTypes() {
		// TODO Auto-generated method stub
		return doQuery("from "+DirType.class.getName()
		        +" as dt where dt.isValid = ? order by dt.sortOrder ",new Object[]{DirType.NORMAL_STATE});
	}

	public DirType selectDirTypeByCode(String dirTypeCode) {
		// TODO Auto-generated method stub
		List dirTypelist= doQuery("from "+DirType.class.getName()
		        +" as dt where dt.dirTypeCode = ?",new Object[]{dirTypeCode});
		return (DirType) dirTypelist.get(0);
	}

	@Override
	public Integer selectUsableOrderNumber() {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select max(dt.sortOrder) from ")
				.append(DirType.class.getName()).append(" as dt where dt.isValid=?");

		Iterator it = super.iterate(hql.toString(),
				new Object[] {DirType.NORMAL_STATE});
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
