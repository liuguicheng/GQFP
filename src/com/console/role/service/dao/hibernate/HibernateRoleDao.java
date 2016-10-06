package com.console.role.service.dao.hibernate;

import java.util.List;

import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.console.entity.Role;
import com.console.entity.Staff;
import com.console.role.service.dao.IRoleDao;

/**
 * The <code>HibernateRoleDao</code> class 基于 Hibernate 的 IRoleDao 实现.
 */
public class HibernateRoleDao extends HibernateCommonDao implements IRoleDao {


    /**
     * @see com.console.role.service.dao.IRoleDao#selectRoleList(java.lang.String)
     */
    public List selectRoleList() {
        StringBuffer hql = new StringBuffer("from ").append(Role.class.getName()).append(" as role order by role.sortOrder");
        return super.doQuery(hql.toString());
    }

    /**
     * @see com.console.role.service.dao.IRoleDao#selectRoleStaffList(java.lang.Integer)
     */
    public List selectRoleStaffList(String roleId) {
        return super.doQuery(
                new StringBuffer(" from ").append(Staff.class.getName())
                .append(" as staff where staff.systemRole.id=?").toString(),new Object[]{roleId});
    }

	/**
	 * @see com.console.role.service.dao.IRoleDao#selectRoleStaff(java.lang.String, java.lang.Integer)
	 */
    public List selectRoleStaff(String roleName, Integer dept) {
        Object[] values = new Object[5];
        int idx = 0;
        StringBuffer hql = new StringBuffer("select staff from ").append(Staff.class.getName()).append(" as staff inner join staff.systemRole role where role.name=?");
         values[idx++] = roleName;
        if (dept != null) {
            hql.append(" and staff.department=?") ;
            values[idx++] = dept;
        }
        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
        return super.doQuery(hql.toString(), param);
    }


}
