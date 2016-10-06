package com.console.manage.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.console.entity.Department;
import com.console.entity.Power;
import com.console.manage.command.DepartmentQueryInfo;
import com.console.manage.command.StaffQueryInfo;
import com.console.manage.command.StaffTypeQueryInfo;
import com.systemic.gq.entity.Member;

/**
 * @description
 */
public interface IManageDao extends ICommonDao {

	/**
	 * 取指定部门编号的直接下属部门
	 * 
	 * @param depId 部门编号
	 * @return <code>Department</code>实例组成的<code>List</code>
	 */
	List selectSubDepartment(String depId);

	/**
	 * 取得所有单位的列表. 单位标识 ：祖宗Id ＝ 自身Id
	 * 
	 * @param filterStr 按名字进行过滤时的过滤条件
	 * @return <code>Department</code>实例组成的<code>List</code>
	 */
	List selectAllUnitDepts(String filterStr);

	/**
	 * 取得所有部门的列表
	 * 
	 * @return <code>Department</code>实例组成的<code>List</code>
	 */
	List selectAllDepartment();

	/**
	 * 获取某部门的子部门的可用序号
	 * 
	 * @param parentId 部门Id
	 * @return 最大序号加1
	 */
	Integer selectUsableOrderNumber(String parentId);

	/**
	 * 取指定部门的员工列表.
	 * 
	 * @param depId 部门编号
	 * @return Staff实例组成的List
	 */
	List selectDepartmentStaff(String depId);

	/**
	 * 根据模块名及部门获取有相应使用权限的人员列表
	 * 
	 * @param moduleName 模块名
	 * @param departmentId 部门Id
	 * @return List of Staff
	 */
	List selectStaffList(String moduleName, String departmentId);

	/**
	 * 获取该部门人员的最大排序号
	 * 
	 * @param departmentId 部门Id
	 * @return 最大人员序号
	 */
	List selectStaffUsableOrderNumber(String departmentId);

	/**
	 * 根据部门名称获取某个部门下的部门对象
	 * 
	 * @param name 部门名称
	 * @param parentId 父部门Id
	 * @return 部门对象
	 */
	Department selectDepartment(String name, String parentId);

	/**
	 * 根据部门的对照号查找对应的部门
	 * 
	 * @param depCode 对照号
	 * @return 对应的部门
	 */
	Department selectDepartmentByCode(String depCode);

	/**
	 * 取指定部门和指定页面权限的员工列表.
	 * 
	 * @param depId 部门编号
	 * @param operateCode 页面权限编码
	 * @return Staff实例组成的List
	 */
	List selectOperateStaff(String depId, String operateCode);

	// /**
	// * 取指定工作队列内的用户清单
	// * @param workQueue
	// * @return
	// */
	// List<Staff> selectWorkQueueStaff(String workQueue);
	/**
	 * 劳保部门选择框
	 */
	List selectInsuDepartmentTree();

	/**
	 * 取指定部门的员工ID列表
	 * 
	 * @param depId
	 * @return
	 */
	List selectDepartmentStaffId(String depId);

	/**
	 * 获取所有父部门是自己的部门列表
	 * 
	 * @return
	 */
	List selectAllRootDepartment();
	
    /**
     * 获取所有部门列表，查询用
     * 
     * @param info
     * @return
     */
    List<Department> selectAllDepartments(DepartmentQueryInfo info);
	
    /**
     * 获取所有人员列表 选择用.
     * 
     * @return Staff实例组成的List
     */
    List selectAllStaff(StaffQueryInfo info);
    //List selectAllStaff(StaffInfo info);
    Page selectStaffList(StaffTypeQueryInfo info);
    
    /**
     * 根据url查找权限
     * 
     * @param urlpath 访问路径 
     * @return Power 对应的权限菜单
     */
    public Power selectPowerByUrl(String urlpath);
    List selectDepartmentName(String depName);
    
    /**根据staffId获取会员信息
     * @param staffId
     * @return
     */
    Member selectMemberByStaffId(String staffId);
}
