package com.console.role.service.spring;

import java.util.HashSet;
import java.util.List;

import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;
import org.springline.beans.dictionary.support.DictionaryUtils;

import com.console.ConsoleHelper;
import com.console.entity.Operate;
import com.console.entity.Power;
import com.console.entity.Role;
import com.console.entity.Staff;
import com.console.role.command.RoleGrantInfo;
import com.console.role.command.RoleInfo;
import com.console.role.service.IRoleService;
import com.console.role.service.dao.IRoleDao;
import com.plugins.sn.service.SNHelper;

/**
 * @description
 */
public class SpringRoleService implements IRoleService, ICacheConnector {
    /**
     * 角色管理的Dao
     */
    private IRoleDao roleDao;

    /**
     * @param roleDao
     */
    public void setRoleDao(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }


    /**
     * @see com.console.role.service.IRoleService#insertRole(com.console.entity.Role)
     */
    public void saveRole(RoleInfo info) {
        Role role = new Role();
        if (info.getId() != null && info.getId().trim().length() > 0) {
            role = this.selectRole(info.getId());
        } else {
            role.setId(SNHelper.getSNService().getSerialNumber(Role.class.getName(), "id", false));
        }
        role.setName(info.getName());
        role.setRemark(info.getRemark());
        if (info.getSortOrder() == null) {
            info.setSortOrder(Integer.valueOf(role.getId()));
        }
        role.setSortOrder(info.getSortOrder());
        role.setHomeUrl(info.getHomeUrl());
        //系统权限
        if (role.getPowers() == null) {
            role.setPowers(new HashSet());
        }
        role.getPowers().clear();
        if (info.getPowerId() != null && info.getPowerId().length > 0) {
            for (int i = 0; i < info.getPowerId().length; i++) {
                role.getPowers().add(this.roleDao.load(Power.class, info.getPowerId()[i]));
            }
        }
      //页面操作权限
//        if(role.getOperates() == null){
//        	role.setOperates(new HashSet());
//        }
//        role.getOperates().clear();
//        if(info.getOperateId() != null && info.getOperateId().length > 0){
//        	for(int i = 0; i < info.getOperateId().length; i++){
//        		role.getOperates().add(this.roleDao.load(Operate.class, info.getOperateId()[i]));
//        	}
//        }

        this.roleDao.save(role);
        CacheHelper.getInstance().dispatchRefreshEvent(Role.DIC_ROLE);
    }

    /**
     * @see com.console.role.service.IRoleService#deleteRole(java.lang.Integer)
     */
    public void deleteRole(String id) {
        Role role = this.selectRole(id);
        if (role.getPowers().size() > 0 || role.getStaffSet().size() > 0 || role.getOperates().size() > 0) {
            throw new RuntimeException("该角色已被引用，不能删除！");
        }
        this.roleDao.delete(role);
        CacheHelper.getInstance().dispatchRefreshEvent(Role.DIC_ROLE);
    }

    /**
     * @see com.console.role.service.IRoleService#selectRole(java.lang.Integer, boolean)
     */
    public Role selectRole(String id) {
        Role role = (Role) this.roleDao.load(Role.class, id);
        this.roleDao.initialize(role.getPowers());
        this.roleDao.initialize(role.getStaffSet());
        this.roleDao.initialize(role.getOperates());
        return role;
    }

    /**
     * @see com.console.role.service.IRoleService#selectRoleList(java.lang.String)
     */
    public List selectRoleList() {
        return this.roleDao.selectRoleList();
    }

    /**
     * @see com.console.role.service.IRoleService#grantPowerToRole(java.lang.Integer, java.lang.String[])
     */
    public void grantPowerToRole(String roleId, String[] powerId) {
        Role role = this.selectRole(roleId);

        role.getPowers().clear();
        if (powerId != null) {
            for (int i = 0; i < powerId.length; ++i) {
                role.getPowers().add(this.roleDao.load(Power.class, powerId[i]));
            }
        }
        this.roleDao.save(role);
    }

    /**
     * @see com.console.role.service.IRoleService#grantRoleToStaff(java.lang.Integer, java.lang.String[])
     */
    public void grantRoleToStaff(String staffId, String[] roleId) {

        Staff grantStaff = ConsoleHelper.getInstance().getManageService().selectStaff(staffId);

        grantStaff.getSystemRole().clear();
        if (roleId != null) {
            for (int i = 0; i < roleId.length; ++i) {
                grantStaff.getSystemRole().add(
                        this.roleDao.load(Role.class, roleId[i]));
            }
        }
        this.roleDao.save(grantStaff);
    }

    /**
     * @see com.console.role.service.IRoleService#updateRoleGrantInfo(RoleGrantInfo)
     */
    public void updateRoleGrantInfo(RoleGrantInfo info) {
        Role role = this.selectRole(info.getRoleId());

        role.getStaffSet().clear();
        if (info.getStaffId() != null) {
            for (int i = 0; i < info.getStaffId().length; ++i) {
                role.getStaffSet().add((Staff) this.roleDao.load(Staff.class, info.getStaffId()[i]));
            }
        }
        this.roleDao.save(role);
    }


    /**
     * @see com.console.role.service.IRoleService#selectRoleStaffList(java.lang.Integer)
     */
    public List selectRoleStaffList(String roleId) {
        return this.roleDao.selectRoleStaffList(roleId);
    }

    /**
     * 按角色进行页面操作授权
     * @see com.console.role.service.IRoleService#grantOperateToRole(java.lang.String, java.lang.String[])
     */
    public void grantOperateToRole(String roleId, String[] operateId) {

        Role role = this.selectRole(roleId);

        role.getOperates().clear();
        if (operateId != null) {
            for (int i = 0; i < operateId.length; ++i) {
                role.getOperates().add(this.roleDao.load(Operate.class, operateId[i]));
            }
        }

        this.roleDao.save(role);
    }


	/**
	 * @see com.console.role.service.IRoleService#selectRoleStaff(java.lang.String, java.lang.Integer)
	 */
	public List selectRoleStaff(String roleName, Integer dept) {
		return this.roleDao.selectRoleStaff(roleName, dept);
	}


    /**
     * @see org.springline.beans.cache.ICacheConnector#loadCacheObject(java.lang.String)
     */
    @Override
    public Object loadCacheObject(String objName) {
        List roles = this.roleDao.loadAll(Role.class, "id");
        return DictionaryUtils.listToDictionaryItemMap(roles, "id", "name");
    }


}
