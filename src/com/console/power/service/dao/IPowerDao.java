package com.console.power.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;

import com.console.entity.Role;
import com.console.power.command.PowerQueryInfo;

public interface IPowerDao extends ICommonDao {

	/**
	 * 取所有的权限信息.
	 * 
	 * @return 所有Power实例组成的List
	 */
	List selectAllPower(PowerQueryInfo info);

	/**
	 * 取父权限的子权限
	 * 
	 * @param parentPowerCode 父权限编号，当为null是，表示取所有顶级的权限
	 * @return 子权限列表
	 */
	List selectChildPower(String parentPowerCode);

	/**
	 * 根据powerId查询角色
	 * 
	 * @param powerId
	 * @return
	 */
	List<Role> selectRoleByPowerId(String powerId);
}
