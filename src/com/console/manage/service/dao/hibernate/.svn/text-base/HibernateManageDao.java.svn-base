package com.console.manage.service.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.internal.util.collections.EmptyIterator;
import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.beans.dataquery.support.QueryUtils;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.Operate;
import com.console.entity.Power;
import com.console.entity.Role;
import com.console.entity.Staff;
import com.console.manage.command.DepartmentQueryInfo;
import com.console.manage.command.StaffQueryInfo;
import com.console.manage.command.StaffTypeQueryInfo;
import com.console.manage.service.dao.IManageDao;
import com.systemic.gq.entity.Member;

public class HibernateManageDao extends HibernateCommonDao implements IManageDao {
	private IQueryStringUtil staffStringUtil;

	public void setStaffStringUtil(IQueryStringUtil staffStringUtil) {
		this.staffStringUtil = staffStringUtil;
	}

	/**
	 * @see com.console.manage.service.dao.IManageDao#selectSubDepartment(java.lang.Integer)
	 */
	public List selectSubDepartment(String depId) {
        Object[] values = new Object[50];
        int idx = 0;
        StringBuffer hql = new StringBuffer(" from ").append(
            Department.class.getName()).append(" as dep where dep.parentId =?")
            .append(" and dep.id != ?").append(" and dep.state=? order by dep.orderNumber");
        values[idx++] = depId;
        values[idx++] = depId;
        values[idx++] = Department.NORMAL_STATE;
        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
        return super.doQuery(hql.toString(), param);
    }

	/**
	 * @see com.console.manage.service.dao.IManageDao#selectAllUnitDepts(java.lang.String)
	 */
	public List selectAllUnitDepts(String filterStr) {
        Object[] values = new Object[50];
        int idx = 0;
        StringBuffer hql = new StringBuffer("from ").append(
            Department.class.getName()).append(
            " as dep where dep.id = dep.ancestorDep ").append(
            " and dep.state = ? ");
        values[idx++] =  Department.NORMAL_STATE;
        if (filterStr != null && filterStr.trim().length() > 0) {
            hql.append(" and dep.name like ? ");
            values[idx++] = "%"
                    + QueryUtils.convertObjectToMultiKeyWord(QueryUtils
                        .convertSqlLikePattern(filterStr)) + "%";
        }
        hql.append("order by dep.orderNumber asc");
        Object[] params = new Object[idx];
        System.arraycopy(values, 0, params, 0, idx);
        return super.doQuery(hql.toString(), params);
    }


	/**
	 * @see com.console.manage.service.dao.IManageDao#selectAllDepartment()
	 */
	public List selectAllDepartment() {
        return super.doQuery(
            "from " + Department.class.getName() + " as dep"
                    + " where dep.state = ? "  
                    + " order by dep.parentId,dep.orderNumber asc", new Object[]{Department.NORMAL_STATE});
    }

	/**
	 * @see com.console.manage.service.dao.IManageDao#selectUsableOrderNumber(java.lang.Integer)
	 */
	public Integer selectUsableOrderNumber(String parentId) {
        StringBuffer hql = new StringBuffer("select max(dep.orderNumber) from ")
            .append(Department.class.getName()).append(
                " as dep where dep.parentId =?").append(" and dep.id !=?")
            .append(" and dep.state =?");
        Iterator it = super.iterate(hql.toString(),
            new Object[] { parentId, parentId, Department.NORMAL_STATE });
        int number = 1;
        if (it.hasNext()) {
            try {
                number = ((Integer) it.next()).intValue();
                number++;
            } catch (Exception ex) {
                throw new RuntimeException("操作失败！");
            }
        }
        if (!(it instanceof EmptyIterator)) {
            Hibernate.close(it); //.closeIterator(it);
        }
        return new Integer(number);

    }

	/**
	 * @see com.console.manage.service.dao.IManageDao#selectDepartmentStaff(java.lang.Integer)
	 */
	public List selectDepartmentStaff(String depId) {
        return super.doQuery(
            "from " + Staff.class.getName() + " as staff"
                    + " where staff.department.id = ? and staff.valid = ?"
                    + " order by staff.orderNumber asc , staff.loginName asc",
            new Object[]{depId,ConsoleHelper.YES});
    }

	/**
	 * @see com.console.manage.service.dao.IManageDao#selectStaffList(java.lang.String,
	 *      Integer)
	 */
	public List selectStaffList(String moduleName, String departmentId) {
		Object[] values = new Object[2];
		int idx = 0;
		StringBuffer hql = new StringBuffer("select distinct staff from ").append(Staff.class.getName())
				.append(" as staff,").append(Role.class.getName()).append(" as role,").append(Power.class.getName())
				.append(" as power ").append(" where power.name=?")
				.append(" and staff in elements(role.staffSet) and power in elements(role.powers)");
		values[idx++] = moduleName;
		if (departmentId != null) {
			hql.append(" and staff.department.id=?");
			values[idx++] = departmentId;
		}
		Object[] conditions = new Object[idx];
		System.arraycopy(values, 0, conditions, 0, idx);
		return super.doQuery(hql.toString(), conditions);
	}

	/**
	 * （non Javadoc）
	 * 
	 * @see com.console.manage.service.dao.IManageDao#selectStaffUsableOrderNumber(java.lang.Integer)
	 */
	   public List selectStaffUsableOrderNumber(String departmentId) {
	        String hql = "select max(sf.orderNumber) from " + Staff.class.getName()
	                + " as sf where sf.valid = ? " 
	                + " and sf.department.id = ?";
	        return this.doQuery(hql, new Object[]{ConsoleHelper.YES,departmentId});
	    }

	/**
	 * @see com.console.manage.service.dao.IManageDao#selectDepartment(java.lang.String,
	 *      java.lang.Integer)
	 */
	public Department selectDepartment(String name, String parentId) {
		Object[] values = { name.trim(), parentId };
		String hql = " from " + Department.class.getName() + " as dp where dp.name = ? and dp.parentId = ?";
		List list = this.doQuery(hql, values);
		if (list != null && list.size() > 0) {
			return (Department) list.get(0);
		}
		return null;
	}

	/**
	 * @see com.console.manage.service.dao.IManageDao#selectDepartmentByCode(java.lang.String)
	 */
	public Department selectDepartmentByCode(String depCode) {
        StringBuffer sql = new StringBuffer(" from ").append(
            Department.class.getName()).append(" dep where dep.code=?");
        List result = doQuery(sql.toString(), new Object[]{depCode});
        return (result.size() == 0) ? null : (Department) result.get(0);
    }

	/**
	 * 取得指定部门和页面权限编码的人员列表
	 * 
	 * @see com.console.manage.service.dao.IManageDao#selectOperateStaff(java.lang.String,
	 *      java.lang.String)
	 */
	public List selectOperateStaff(String depId, String operateCode) {

		Object[] values = new Object[2];
		int idx = 0;
		StringBuffer hql = new StringBuffer("select distinct staff from ").append(Staff.class.getName())
				.append(" as staff,").append(Role.class.getName()).append(" as role,").append(Operate.class.getName())
				.append(" as op where");
		if (depId != null && depId.trim().length() > 0) {
			values[idx++] = depId;
			hql.append(" staff.department.id = ? and");
		}
		if (operateCode != null && operateCode.trim().length() > 0) {
			values[idx++] = operateCode;
			hql.append(" op.operateCode = ? and staff in elements(role.staffSet) and op in elements(role.operates)");
		}

		Object[] conditions = new Object[idx];
		System.arraycopy(values, 0, conditions, 0, idx);
		return this.doQuery(hql.toString(), conditions);
	}

	// /**
	// * @see
	// com.console.manage.service.dao.IManageDao#selectWorkQueueStaff(java.lang.String)
	// */
	// public List<Staff> selectWorkQueueStaff(String workQueue) {
	// return super.doQuery(
	// new StringBuffer(" from ").append(Staff.class.getName())
	// .append(" as staff where ? in elements(staff.workQueue)").toString(),
	// workQueue);
	//
	// }
	/**
	 * 劳保部门选择框
	 */
	public List selectInsuDepartmentTree() {
        StringBuffer hql = new StringBuffer(" from ").append(
            Department.class.getName()).append(
            " as dep where (dep.id=1) or (dep.id =20 or dep.id=33) ").append(
            " or (dep.parentId  in (50,71,72) or dep.id in (50,71,72))")
            .append(" and dep.state=?").append(
                " order by dep.orderNumber");
        return super.doQuery(hql.toString(),new Object[]{Department.NORMAL_STATE});
    }

	/**
	 * @see com.console.manage.service.dao.IManageDao#selectDepartmentStaffId(java.lang.String)
	 */
	public List selectDepartmentStaffId(String depId) {
        return super.doQuery(
            "select new list(staff.staffId) from " + Staff.class.getName()
                    + " as staff"
                    + " where staff.department.id = ? and staff.valid = ? "
                    + " order by staff.orderNumber asc , staff.loginName asc",
            new Object[]{depId,ConsoleHelper.YES});
    }

	/**
	 * @see com.console.manage.service.dao.IManageDao#selectAllRootDepartment()
	 */
	@SuppressWarnings("unchecked")
	public List selectAllRootDepartment() {
		StringBuffer sql = new StringBuffer(" from ").append(Department.class.getName())
				.append(" as dep where dep.id = dep.parentId order by dep.orderNumber asc");
		List result = this.doQuery(sql.toString());
		return result.size() == 0 ? new ArrayList() : result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> selectAllDepartments(DepartmentQueryInfo info) {
		StringBuffer hql = new StringBuffer("from ").append(Department.class.getName())
				.append(" as d where d.state = ? ");
		List<Object> objects = new ArrayList<Object>();
		objects.add(Department.NORMAL_STATE);
		if (StringUtils.isNotBlank(info.getDepName())) {
			hql.append("and d.name like ? ");
			objects.add("%" + info.getDepName() + "%");
		}
		if (StringUtils.isNotBlank(info.getDepCode())) {
			hql.append("and d.code like ? ");
			objects.add(info.getDepCode());
		}
		if (StringUtils.isNotBlank(info.getParentId())) {
			hql.append("and d.parentId like ? ");
			objects.add(info.getParentId());
		}
		if (StringUtils.isNotBlank(info.getId())) {
            hql.append("and d.id = ? ");
            objects.add(info.getId());
        }
		hql.append(" order by d.parentId ,d.orderNumber ");
		return super.doQuery(hql.toString(), objects.toArray());
	}

	@Override
	public List selectAllStaff(StaffQueryInfo info) {
		Object[] values = new Object[3];
		int idx = 0;
		StringBuffer hql = new StringBuffer("from ").append(Staff.class.getName()).append(" as s where s.valid=1 ");
		if (info != null) {
			if (info.getName() != null && info.getName().trim().length() > 0) {
				String name = info.getName().replaceAll("，", ",");
				// 多人名同时查询，如AA，BB,CC
				if (name.indexOf(",") >= 0) {
					hql.append(" and instr(?, s.name) > 0");
					values[idx++] = "," + name + ",";
				} else { // 模糊查询
					hql.append(" and s.name like ? ");
					values[idx++] = "%" + info.getName() + "%";
				}
			}
			if (info.getDepartmentName() != null && info.getDepartmentName().trim().length() > 0) {
				hql.append(" and s.department.name like ? ");
				values[idx++] = "%" + info.getDepartmentName() + "%";
			}
			if (StringUtils.isNotBlank(info.getStaffType())) {
				hql.append(" and s.staffType = ? ");
				values[idx++] = info.getStaffType();
			}
            //if (StringUtils.isNotBlank(info.getDepCode())) {
             //   hql.append(" and s.department.id like '" + info.getDepCode() + "' ");
            //}
            
            hql.append(" and s.department like '10%' ");
		}
		hql.append(" order by s.department.parentId,s.department.orderNumber,s.orderNumber asc");

		Object[] param = new Object[idx];
		System.arraycopy(values, 0, param, 0, idx);
		return super.doQuery(hql.toString(), param);
	}

	@Override
	public Page selectStaffList(StaffTypeQueryInfo info) {
		IQueryObject qo = this.staffStringUtil.getQueryObject(info);
		if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
			List<StaffQueryInfo> data = super.doQuery(qo.getQueryString(), qo.getParam());
			return super.putDataToPage(data);
		}
		return super.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
	}

	// @Override
	// public List selectAllStaff(StaffInfo info) {
	// StringBuffer hql = new StringBuffer("from
	// ").append(Staff.class.getName()).append(" as s where s.valid='1' ");
	// return super.doQuery(hql.toString());
	// }
	
	  @Override
	    public Power selectPowerByUrl(String urlpath) {
	        StringBuffer hql = new StringBuffer(" from ");
	        hql.append(Power.class.getName()).append(" po where po.url= ?");
	        List result = super.doQuery(hql.toString(), new Object[]{urlpath});
	        return (result.size() == 0) ? null : (Power) result.get(0);
	    }

	  @Override
	    public List selectDepartmentName(String depName) {
	        StringBuffer hql = new StringBuffer(" from ").append(Department.class.getName()).
	                append(" as pm where pm.name = ? ");
	        return (List) this.doQuery(hql.toString(),new Object[]{depName});
	    }

	@Override
	public Member selectMemberByStaffId(String staffId) {
		StringBuffer hql = new StringBuffer(" from ").append(Member.class.getName()).append(" as m where m.staffId =? ");
		List list = this.doQuery(hql.toString(),new Object[]{staffId});
		if(list.size() > 0){
			return (Member) list.get(0);
		}
		return null;
	}
	  
}
