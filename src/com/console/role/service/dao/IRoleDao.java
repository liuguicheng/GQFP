package com.console.role.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;


public interface IRoleDao extends ICommonDao {

    /** 取角色信息列表.
     * @param roleType 按何种角色类型过滤
     * @return 角色列表
     */
    List selectRoleList();

    /** 获取拥有该角色的人员列表
     * @param roleId 角色Id
     * @return 人员列表
     */
    List selectRoleStaffList(String roleId);

    /**
     * 获取拥有该角色的人员列表
     * @param roleName
     * @param dept
     * @return
     */
    public List selectRoleStaff(String roleName, Integer dept);

}
