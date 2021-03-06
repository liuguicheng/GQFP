package com.console.manage.service.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;
import org.springline.beans.dictionary.support.DictionaryUtils;
import org.springline.beans.tree.ITreeNode;
import org.springline.beans.tree.support.paternity.PaternityTreeBuilder;
import org.springline.beans.utils.EncryptHelper;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Role;
import com.console.entity.Staff;
import com.console.entity.StaffSecurity;
import com.console.main.service.IMainService;
import com.console.manage.command.DepartmentInfo;
import com.console.manage.command.DepartmentPowerInfo;
import com.console.manage.command.DepartmentQueryInfo;
import com.console.manage.command.StaffInfo;
import com.console.manage.command.StaffQueryInfo;
import com.console.manage.command.StaffTypeQueryInfo;
import com.console.manage.service.IManageService;
import com.console.manage.service.dao.IManageDao;
import com.plugins.sn.service.SNHelper;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Stock;

/**
 * @description
 */
public class SpringManageService implements IManageService, ICacheConnector {

	/**
	 * dao, 注入
	 */
	private IManageDao manageDao;


	/**
	 * IOC 调用入口服务的相关接口
	 */
	private IMainService mainService;

	/**
	 * 为兼容以往的代码，故不改变该函数的返回值，以后再行处理，直接返回ITreeNode 
	 *
	 * @see com.console.manage.service.IManageService#selectDepartmentTree(String)
	 */
	public List selectDepartmentTree(String rootId) {
		ITreeNode rootNode = (ITreeNode) CacheHelper.getInstance().getCacheObject(Department.TREE_DIC_IDENTIFICATION);
		if (rootId != null && rootId.trim().length() > 0) {
			List result = new ArrayList();
			result.add(rootNode.find(rootId.toString()));
			return result;
		}
		return rootNode.getChildren();
	}

	/**
	 * @see com.console.manage.service.IManageService#selectSubDepartment(String)
	 */
	public List selectSubDepartment(String depId) {
		return this.manageDao.selectSubDepartment(depId);
	}

	/**
	 * @see com.console.manage.service.IManageService#selectAllRootDepartment()
	 */
	public List selectAllRootDepartment() {
		List aList=this.manageDao.selectAllRootDepartment();
		return aList;
	}

	/**
	 * @see com.console.manage.service.IManageService#selectAllUnitDepts(java.lang.String)
	 */
	public List selectAllUnitDepts(String filterStr) {
		return this.manageDao.selectAllUnitDepts(filterStr);
	}

	/**
	 * @see com.console.manage.service.IManageService#selectDepartment(String)
	 */
	public Department selectDepartment(String depId) {
		Department dept = (Department) this.manageDao.load(Department.class,
			depId);
		this.manageDao.initialize(dept.getPowerSet());
		return dept;
	}

	/**
	 * @see com.console.manage.service.IManageService#doGrantPowerToDepartment(com.console.manage.command.DepartmentPowerInfo)
	 */
	public void doGrantPowerToDepartment(DepartmentPowerInfo info) {
		Department dept = this.selectDepartment(info.getDepId());

		dept.getPowerSet().clear();
		if (info != null && info.getPowerId() != null) {
			for (int i = 0; i < info.getPowerId().length; ++i) {
				dept.getPowerSet().add(
					this.manageDao.load(Power.class, info.getPowerId()[i]));
			}
		}
		this.manageDao.save(dept);

	}

	/**
	 * @see com.console.manage.service.IManageService#selectUsableOrderNumber(String)
	 */
	public Integer selectUsableOrderNumber(String parentId) {
		return this.manageDao.selectUsableOrderNumber(parentId);
	}

	/**
	 * @see com.console.manage.service.IManageService#doSortDepartment(String[])
	 */
	public void doSortDepartment(String[] depId) {
		for (int i = 0; i < depId.length; i++) {
			Department dep = this.selectDepartment(depId[i]);
			dep.setOrderNumber(new Integer(i + 1));
			this.manageDao.save(dep);
		}
		// 排序之后，树字典也需重排
		CacheHelper.getInstance().dispatchRefreshEvent(Department.TREE_DIC_IDENTIFICATION);
	}

	/**
	 * @see com.console.manage.service.IManageService#saveDepartment(com.console.manage.command.DepartmentInfo)
	 */
	public void saveDepartment(DepartmentInfo info) {
		Department department;
		if (info.getId() == null || info.getId().trim().length() == 0) { // 新增
			department = new Department();
			department.setId(SNHelper.getSNService().getSerialNumber(
				Department.class.getName(), "id", false));
			department.setState(Department.NORMAL_STATE);
		} else {
			department = this.selectDepartment(info.getId());
		}
		try {
			BeanUtils.copyProperties(info, department, new String[] { "id" });
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		if (info.getName() != null && info.getParentId() != null) {
			Department dept = this.manageDao.selectDepartment(info.getName(),
				info.getParentId());
			if (dept != null && !dept.getId().equals(info.getId())
					&& !Department.HIDDEN_STATE.equals(dept.getState())) {
				throw new RuntimeException("部门名称『"
						+ info.getName() + "』已被使用，请重新编辑部门名称！");
			}
		}
		this.manageDao.save(department);

		if (info.getIsIndependent() != null
				&& info.getIsIndependent().booleanValue()) {
			department.setAncestorDep(department.getId());
		} else {
			if (department.getParentId() != null
					&& department.getParentId() != "") {
				Department parentDep = this.selectDepartment(department
					.getParentId());
				department.setAncestorDep(parentDep.getAncestorDep());
			} else {// 在parentId为空的时候，即多根的情况，此时应该把父部门id和祖宗部门id设置为自己
				department.setParentId(department.getId());
				department.setAncestorDep(department.getId());
			}
		}
		this.manageDao.save(department);
		// 修改之后，两个字典都要刷新
        CacheHelper.getInstance().dispatchRefreshEvent(Department.TREE_DIC_IDENTIFICATION);
        CacheHelper.getInstance().dispatchRefreshEvent(Department.SIMPLE_DIC_IDENTIFICATION);
	}

	/**
	 * @see com.console.manage.service.IManageService#deleteDepartment(String[])
	 */
	public void deleteDepartment(String[] id) {
		if (id == null) {
			return;
		}
		for (int i = 0; i < id.length; i++) {
			Department department = this.selectDepartment(id[i]);
			if (department == null) {
				continue;
			}
			if (this.selectSubDepartment(id[i]).size() > 0) {
				throw new RuntimeException("有部门从属于部门『"
						+ department.getName() + "』，不能删除！");
			} else if (this.selectDepartmentStaff(id[i]).size() > 0) {
				throw new RuntimeException("有员工从属于部门『"
						+ department.getName() + "』，不能删除！");
			} else {
				department.setState(Department.HIDDEN_STATE);
				this.manageDao.save(department);
			}
		}
		// 修改之后，两个字典都要刷新
        CacheHelper.getInstance().dispatchRefreshEvent(Department.TREE_DIC_IDENTIFICATION);
        CacheHelper.getInstance().dispatchRefreshEvent(Department.SIMPLE_DIC_IDENTIFICATION);
	}

	/**
	 * @see com.console.manage.service.IManageService#selectStaff(java.lang.Integer,
	 *      boolean)
	 */
	public Staff selectStaff(String id) {
		Staff staff = (Staff) this.manageDao.load(Staff.class, id);
		// this.manageDao.initialize(staff.getFlowRole());
		this.manageDao.initialize(staff.getSystemRole());
		// this.manageDao.initialize(staff.getWorkQueue());
		return staff;
	}

	/**
	 * @see com.console.manage.service.IManageService#selectDepartmentStaff(String)
	 */
	public List selectDepartmentStaff(String depId) {
		return this.manageDao.selectDepartmentStaff(depId);
	}

	/**
	 * @see com.console.manage.service.IManageService#selectDepartmentStaffId(java.lang.String)
	 */
	public List selectDepartmentStaffId(String depId) {
		return this.manageDao.selectDepartmentStaffId(depId);
	}

	/**
	 * @see com.console.manage.service.IManageService#updateStaff(com.console.manage.command.StaffInfo)
	 */
	public Staff updateStaff(StaffInfo staffInfo) {
		Staff staff = null;
		if (staffInfo.getId() == null || staffInfo.getId().trim().length() == 0) {
			staff = new Staff();
			staff.setId(SNHelper.getSNService().getSerialNumber(
				Staff.class.getName(), "id", false));
			staff.setPassword(staffInfo.getPassword());
			staffInfo.setSysTemplate("1");
			staff.setRegDate(new Date());
			staff.setStaffType(staffInfo.getStaffType());
			staff.setIdNum(staffInfo.getIdNum());
			staff.setInDate(staffInfo.getInDate());
			staff.setRoomNum(staffInfo.getRoomNum());
			
		} else { // 修改
			staff = this.selectStaff(staffInfo.getId());
			if (staffInfo.getSysTemplate() == null
					|| staffInfo.getSysTemplate().trim().length() <= 0) {
				if (staff.getSysTemplate() != null) {
					staffInfo.setSysTemplate(staff.getSysTemplate());
				} else {
					staffInfo.setSysTemplate("1");
				}
			}
		}
		
		// 判断登录名是否被使用
		Staff tmp = this.mainService.selectStaff(staffInfo.getLoginName());
		if (tmp != null && !tmp.getId().equals(staffInfo.getId())) {
			throw new RuntimeException("登录名『"
					+ staffInfo.getLoginName() + "』已被使用！");
		}
		BeanUtils.copyProperties(staffInfo, staff, new String[] { "id",
				"department", "password" });
		// 设置密码，并加密
		if (staffInfo.getPassword() != null
				&& staffInfo.getPassword().trim().length() > 0) {
			// if
			// (staff.getPassword().equals(EncryptHelper.md5Encoding(staffInfo.getPassword())))
			// {
			// throw new
			// InvalidDataAccessResourceUsageException("最新的密码不能与上次使用的密码相同！");
			// }
		    //判断密码是否变更，变更则更新CHANGE_TIME字段
		    if(!staff.getPassword().equals(EncryptHelper.md5Encoding(staffInfo.getPassword()))){
		        StaffSecurity ss = this.mainService.loadStaffSecurity(staff.getId());
	            ss.setChangeTime(new Date());
	            this.manageDao.update(ss);
             }
            staff.setPassword(EncryptHelper
                .md5Encoding(staffInfo.getPassword()));
		}
		// 设置科室
		//staff.setDepartment(this.selectDepartment(staffInfo.getDepartment()));
		// 设置角色，必选项
		if (staffInfo.getRoleId() != null) {
			Set set = new HashSet();
			for (int i = 0; i < staffInfo.getRoleId().length; i++) {
				Role role = (Role) this.manageDao.load(Role.class, staffInfo
					.getRoleId()[i]);
				set.add(role);
			}
			staff.setSystemRole(set);
		}
		// 设置工作队列，queue不是必选项
		// setPEWorkQueue(staff, staffInfo.getQueueName());
		// 保存到数据库
		this.manageDao.save(staff);
		// 保存到ldap
		try {
			staffInfo.setDepartmentName(staff.getDepartment().getName());
			// this.ldapManageDao.saveUser(staffInfo);
			ConsoleHelper.getInstance().notifyObservers(staffInfo);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			throw new RuntimeException("操作Ldap出现错误，可能无法连接Ldap服务器，请联系系统管理员。");
		}

		// 字典注入
        CacheHelper.getInstance().dispatchRefreshEvent(Staff.SIMPLE_DIC_IDENTIFICATION);
		
		if (staff.getSystemRole() != null) {
			Iterator roleIterator = staff.getSystemRole().iterator();
			while (roleIterator.hasNext()) {
				Role role = (Role) roleIterator.next();
				Iterator powerIterator = role.getPowers().iterator();
				while (powerIterator.hasNext()) {
					Power power = (Power) powerIterator.next();
					staff.addPowerId(power.getCode());
				}
			}
		}
//		String [] stringArr= staffInfo.getId().split(",");
//		this.doSortStaff(stringArr);
		int order = this.selectStaffUsableOrderNumber(staff.getDepartment().getId());
		staff.setOrderNumber(order);
		return staff;
	}

	// /**跟PE引擎同步，设置队列权限
	// * @param staff
	// * @param queueNames
	// */
	// private void setPEWorkQueue(Staff staff, String[] queueNames) {
	// IWFAdministrator admin = WFProxy.getInstance().getAdministrator();
	//
	// Set<String> newQueues = new HashSet();
	// if (queueNames != null) {
	// for(String queue : queueNames) {
	// newQueues.add(queue);
	// }
	// }
	// Set<String> oldQueues = staff.getWorkQueue();
	// admin.updateStaffQueue(staff.getLoginName(), oldQueues, newQueues);
	// staff.setWorkQueue(newQueues);
	// }

	/**
	 * @see com.console.manage.service.IManageService#deleteStaff(java.lang.Integer)
	 */
	public Staff deleteStaff(String staffId) {
		Staff staff = this.selectStaff(staffId);
		// this.ldapManageDao.deleteUser(staff);
		ConsoleHelper.getInstance().notifyObservers(staff);
		if (staff != null) {
			staff.setValid(ConsoleHelper.NO);
			staff.setLoginName("-" + staff.getLoginName() + "-"
					+ staff.getId().toString() + "-");
			this.manageDao.save(staff);
		}
        CacheHelper.getInstance().dispatchRefreshEvent(Staff.SIMPLE_DIC_IDENTIFICATION);
		return staff;

	}

	/**
	 * @see com.console.manage.service.IManageService#selectStaffList(java.lang.String,
	 *      Integer)
	 */
	public List selectStaffList(String moduleName, String departmentId) {
		return this.manageDao.selectStaffList(moduleName, departmentId);
	}

	/**
	 * （non Javadoc） 获取该部门人员的最大排序号
	 *
	 * @see com.console.manage.service.IManageService#selectStaffUsableOrderNumber(java.lang.Integer)
	 */
	public Integer selectStaffUsableOrderNumber(String departmentId) {
		Integer order = new Integer(1);
		List list = this.manageDao.selectStaffUsableOrderNumber(departmentId);
		if (list != null && list.get(0) != null) {
			order = ((Integer) list.get(0));
			order = new Integer(order.intValue() + 1);
		}
		return order;
	}

	/**
	 * @see com.console.manage.service.IManageService#setManageDao(com.console.manage.service.dao.IManageDao)
	 */
	public void setManageDao(IManageDao manageDao) {
		this.manageDao = manageDao;
	}

	/**
	 * （non Javadoc）
	 *
	 * @see com.console.manage.service.IManageService#doSortStaff(java.lang.Integer[])
	 */
	public void doSortStaff(String[] staffId) {
		for (int i = 0; i < staffId.length; i++) {
			Staff staff = (Staff) this.manageDao.load(Staff.class, staffId[i]);
			staff.setOrderNumber(new Integer(i + 1));
			this.manageDao.save(staff);
		}

	}

	//
	//
	// /**
	// * @see
	// com.console.manage.service.IManageService#selectValidDuty(com.console.duty.command.DutyInfo)
	// */
	// public boolean selectValidDuty(DutyInfo info) {
	// return this.manageDao.selectValidDuty(info);
	// }
	//
	// /**
	// * @see
	// com.console.manage.service.IManageService#selectDuty(java.lang.Integer)
	// */
	// public Duty selectDuty(Integer dutyId) {
	// return (Duty) this.manageDao.load(Duty.class, dutyId);
	// }
	//
	// /**
	// * @see com.console.manage.service.IManageService#deleteDuty(Integer[])
	// */
	// public void deleteDuty(Integer[] deleteId) {
	// Duty duty;
	// for (int i = 0; i < deleteId.length; i++) {
	// duty = (Duty) this.manageDao.load(Duty.class, deleteId[i]);
	// if (duty != null) {
	// this.manageDao.delete(duty);
	// }
	// }
	// }
	//
	// /**
	// * @see com.console.manage.service.IManageService#selectMaxDutyDisplayNo()
	// */
	// public Integer selectMaxDutyDisplayNo() {
	// return this.manageDao.selectMaxDutyDisplayNo();
	// }

	/**
	 * @param mainService the mainService to set
	 */
	public void setMainService(IMainService mainService) {
		this.mainService = mainService;
	}

	public List<Department> selectAllDepartment() {
		return this.manageDao.selectAllDepartment();
	}

	// /**
	// * @see
	// com.console.manage.service.IManageService#selectWorkQueueStaff(java.lang.String)
	// */
	// public List<Staff> selectWorkQueueStaff(String workQueue) {
	// return this.manageDao.selectWorkQueueStaff(workQueue);
	// }

	/**
	 * 取得指定部门和页面权限编码的人员列表
	 *
	 * @see com.console.manage.service.IManageService#selectOperateStaff(java.lang.String,
	 *      java.lang.String)
	 */
	public List selectOperateStaff(String depId, String operateCode) {

		return this.manageDao.selectOperateStaff(depId, operateCode);
	}

	/**
	 * 获取所有人员列表 选择用.
	 *
	 * @see com.console.manage.service.IManageService#selectAllStaff()
	 */
	public List selectAllStaff(StaffQueryInfo info) {
	    return this.manageDao.selectAllStaff(info);
		//return this.manageDao.loadAll(Staff.class, "department.orderNumber,id");
	}

	/**
	 * 劳保部门选择框
	 */
	public List selectInsuDepartmentTree() {
		List depts = this.manageDao.selectInsuDepartmentTree();
		ITreeNode rootNode = PaternityTreeBuilder.createTree(depts);
		return rootNode.getChildren();
	}

    /**
     * @see org.springline.beans.cache.ICacheConnector#loadCacheObject(java.lang.String)
     */
    @Override
    public Object loadCacheObject(String objName) {
        if (Department.SIMPLE_DIC_IDENTIFICATION.equals(objName)) { // 部门字典
            List depts = this.manageDao.selectAllDepartment();
            return DictionaryUtils.listToDictionaryItemMap(depts, "id", "name");
        } else if (Department.TREE_DIC_IDENTIFICATION.equals(objName)) { // 部门树
            List depts = this.manageDao.selectAllDepartment();
            return PaternityTreeBuilder.createTree(depts);
        } else if (Staff.SIMPLE_DIC_IDENTIFICATION.equals(objName)) { // 人员字典
            List staffs = this.manageDao.loadAll(Staff.class, null);
            return DictionaryUtils.listToDictionaryItemMap(staffs, "id", "name");
        }else if(Member.SIMPLE_DIC_IDENTIFICATION.equals(objName)){
        	List member = this.manageDao.loadAll(Member.class,null);
        	return DictionaryUtils.listToDictionaryItemMap(member, "memberId", "userName");//会员字典
        }else if(Stock.SIMPLE_DIC_IDENTIFICATION.equals(objName)){
        	List stock = this.manageDao.loadAll(Stock.class,null);
        	return DictionaryUtils.listToDictionaryItemMap(stock, "id", "gradeNum");//股权字典
        }
        return null;
    }

	@Override
	public Staff loadStaff(String staffId) {
		return (Staff) this.manageDao.load(Staff.class, staffId);
	}

	@Override
	public Page selectStaffList(StaffTypeQueryInfo info) {
		
		return this.manageDao.selectStaffList(info);
	}

    @Override
    public List<Department> selectAllDepartments(DepartmentQueryInfo info) {
        Assert.notNull(info);
        return this.manageDao.selectAllDepartments(info);
    }
	@Override
	public List selectAllVaildStaff() {
		return this.manageDao.loadAll(Staff.class, "valid","1","department.orderNumber,id");
	}

//	@Override
//	public List selectAllStaff(StaffInfo info) {
//		
//		return this.manageDao.selectAllStaff(info);
//	}
	
    @Override
    public Power selectPowerByUrl(String urlpath) {
        return this.manageDao.selectPowerByUrl(urlpath);
    }

    @Override
    public List selectDepartmentName(String depName) {
        return this.manageDao.selectDepartmentName(depName);
    }

	@Override
	public Member selectMemberByStaffId(String staffId) {
		
		return this.manageDao.selectMemberByStaffId(staffId);
	}

}
