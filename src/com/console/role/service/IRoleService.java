package com.console.role.service;

import java.util.List;

import com.console.entity.Role;
import com.console.role.command.RoleGrantInfo;
import com.console.role.command.RoleInfo;

/**
 * @description
 */
public interface IRoleService {


    /**
     * 新增角色信息.
     * @param role  Role实例
     * @return  保存后的Role
     */
    void saveRole(RoleInfo role);

    /**
     * 删除角色信息.
     * @param id    角色编号
     */
    void deleteRole(String id);

    /**
     * 取指定的角色信息.
     * @param id            角色编号
     * @param initialize    是否初始化包含的权限集
     * @return  Role实例
     */
    Role selectRole(String id);

    /** 取角色信息列表.
     * @param roleType 按何种角色类型过滤
     * @return 角色列表
     */
    List selectRoleList();

    /** 按角色选择人员进行授权
     * @param info RoleGrantInfo
     */
    void updateRoleGrantInfo(RoleGrantInfo info);

    /** 获取拥有该角色的人员列表
     * @param roleId 角色Id
     * @return 人员列表
     */
    List selectRoleStaffList(String roleId);

    /**
     * 按角色进行页面操作授权
     * @param roleId
     * @param operateId
     */
    void grantOperateToRole(String roleId,String[] operateId);

    /**
     * 获取拥有该角色的人员列表
     * @param roleName
     * @param dept
     * @return
     */
    public List selectRoleStaff(String roleName, Integer dept);
}
