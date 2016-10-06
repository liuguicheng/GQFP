/**
 * Description:
 * History:  2009-7-22 Create
 **/

package com.console.main.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dictionary.support.DictionaryUtils;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.Staff;
import com.console.main.service.dao.IMainDao;

/**
 * @description
 */
public class HibernateMainDao extends HibernateCommonDao implements IMainDao {

	/**
	 * @see com.console.main.service.dao.IMainDao#selectStaff(java.lang.String)
	 */
	public Staff selectStaff(String loginName) {
		List staffList = super.doQuery("from " + Staff.class.getName()
				+ " as staff"
				+ " where staff.loginName = ? and staff.valid = ? ",
				new Object[] { loginName, ConsoleHelper.YES });
		if (staffList.isEmpty()) {
			return null;
		}
		return (Staff) staffList.get(0);
	}

	@Override
	public Staff selectAllStaff(String loginName) {
		List staffList = super.doQuery("from " + Staff.class.getName()
				+ " as staff" + " where staff.loginName = ?",
				new Object[] { loginName });
		if (staffList.isEmpty()) {
			return null;
		}
		return (Staff) staffList.get(0);
	}

}
