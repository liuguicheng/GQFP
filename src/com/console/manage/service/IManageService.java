package com.console.manage.service;

import java.util.List;

import org.springline.orm.Page;

import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Staff;
import com.console.manage.command.DepartmentInfo;
import com.console.manage.command.DepartmentPowerInfo;
import com.console.manage.command.DepartmentQueryInfo;
import com.console.manage.command.StaffInfo;
import com.console.manage.command.StaffQueryInfo;
import com.console.manage.command.StaffTypeQueryInfo;
import com.systemic.gq.entity.Member;

/**
 * @description
 */
public interface IManageService {

	/**
	 * 取得部门树，使用 <code>DepartmentTree</code> 实例.
	 * 
	 * @param rootId
	 *            TODO
	 * @return 部门树根结点列表.
	 */
	List selectDepartmentTree(String rootId);

	/**
	 * 取指定部门编号的直接下属部门
	 * 
	 * @param depId
	 *            部门编号
	 * @return <code>Department</code>实例组成的<code>List</code>
	 */
	List selectSubDepartment(String depId);

	/**
	 * 取得所有单位的列表. 单位标识 ：祖宗Id ＝ 自身Id
	 * 
	 * @param filterStr
	 *            按名字进行过滤时的过滤条件
	 * @return <code>Department</code>实例组成的<code>List</code>
	 */
	List selectAllUnitDepts(String filterStr);

	/**
	 * 取指定的部门 <b>注：本方法不过滤无效部门</b>
	 * 
	 * @param depId
	 *            部门编号
	 * @return Department实例
	 */
	Department selectDepartment(String depId);
	List selectDepartmentName(String depName);

	/**
	 * 获取某部门的子部门的可用序号
	 * 
	 * @param parentId
	 *            部门Id
	 * @return 最大序号加1
	 */
	Integer selectUsableOrderNumber(String parentId);

	/**
	 * 保存部门
	 * 
	 * @param info
	 *            部门编辑信息
	 */
	void saveDepartment(DepartmentInfo info);

	/**
	 * 对子部门进行排序
	 * 
	 * @param depId
	 *            排序后的子部门编号数组
	 */
	void doSortDepartment(String[] depId);

	/**
	 * 删除部门
	 * 
	 * @param id
	 *            部门Id
	 */
	void deleteDepartment(String[] id);

	/**
	 * 按单位设置权限
	 * 
	 * @param info
	 *            授权信息
	 */
	void doGrantPowerToDepartment(DepartmentPowerInfo info);

	// 以下为员工管理

	/**
	 * 根据员工编号取员工信息. <b>注：本方法不过滤无效用户</b>
	 * 
	 * @param id
	 *            员工编号
	 * @return Staff实例
	 */
	Staff selectStaff(String id);

	/**
	 * 取指定部门的员工列表.
	 * 
	 * @param depId
	 *            部门编号
	 * @return Staff实例组成的List
	 */
	List selectDepartmentStaff(String depId);

	/**
	 * 取指定部门的员工ID列表
	 * 
	 * @param depId
	 * @return
	 */
	List selectDepartmentStaffId(String depId);

	/**
	 * 修改员工信息.
	 * 
	 * @param staffInfo
	 *            Staff 实例
	 */
	Staff updateStaff(StaffInfo staffInfo);

	/**
	 * 删除员工
	 * 
	 * @param staffId
	 *            员工Id
	 * @return 被删除的员工对象
	 */
	Staff deleteStaff(String staffId);

	Staff loadStaff(String staffId);

	/**
	 * 根据模块名及部门获取有相应使用权限的人员列表
	 * 
	 * @param moduleName
	 *            模块名
	 * @param departmentId
	 *            部门Id
	 * @return List of Staff
	 */
	List selectStaffList(String moduleName, String departmentId);

	/**
	 * 获取该部门人员的最大排序号
	 * 
	 * @param departmentId
	 *            部门Id
	 * @return 最大排序号
	 */
	Integer selectStaffUsableOrderNumber(String departmentId);

	/**
	 * 人员排序
	 * 
	 * @param staffId
	 *            排序后的人员Id数组
	 */
	void doSortStaff(String[] staffId);

	/**
	 * 新增方法 为了控制文件共享权限
	 */
	List<Department> selectAllDepartment();

	/**
	 * 获取所有部门列表，查询用
	 * 
	 * @param info
	 * @return
	 */
	List<Department> selectAllDepartments(DepartmentQueryInfo info);

	/**
	 * 取指定部门和指定页面权限的员工列表.
	 * 
	 * @param depId
	 *            部门编号
	 * @param operateCode
	 *            页面权限编码
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
	 * 获取所有人员列表 选择用.
	 * 
	 * @return Staff实例组成的List
	 */
	List selectAllStaff(StaffQueryInfo info);

	/**
	 * 劳保部门选择框
	 */
	List selectInsuDepartmentTree();

	/**
	 * 获取所有父部门是自己的部门列表
	 * 
	 * @return
	 */
	List selectAllRootDepartment();

	// List selectAllStaff(StaffInfo info);
	Page selectStaffList(StaffTypeQueryInfo info);

	/**
	 * 获取所有有效人员列表
	 *
	 * @return Staff实例组成的List
	 */
	List selectAllVaildStaff();
	
	  /**
     * 根据url查找权限
     * 
     * @param urlpath 访问路径 
     * @return Power 对应的权限菜单
     */
	Power selectPowerByUrl(String urlpath) ;
	 /**根据staffId获取会员信息
     * @param staffId
     * @return
     */
    Member selectMemberByStaffId(String staffId);
}
