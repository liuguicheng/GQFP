package com.console.power.service.spring;

import java.util.List;

import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;
import org.springline.beans.tree.support.layered.LayerTreeBuilder;

import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Role;
import com.console.power.command.PowerGrantInfo;
import com.console.power.command.PowerQueryInfo;
import com.console.power.service.IPowerService;
import com.console.power.service.dao.IPowerDao;
import com.plugins.sn.service.SNHelper;

public class SpringPowerService implements IPowerService, ICacheConnector {
	/** IPowerDao实例. */
	private IPowerDao powerDao;

	/**
	 * @see com.console.power.service.IPowerService#setPowerDao(com.console.power.service.dao.IPowerDao)
	 */
	public void setPowerDao(IPowerDao powerDao) {
		this.powerDao = powerDao;
	}

	/**
	 * @see com.console.power.service.IPowerService#savePower(com.console.entity.Power)
	 */
	public Power savePower(Power power) {
		if ((power.getCode().length() % Power.LEVEL_LENGTH) != 0) {
			throw new IllegalArgumentException("权限编号的长度必需为"
					+ Power.LEVEL_LENGTH + "的整数倍");
		}
		if (power.getId() == null || power.getId().trim().length() == 0) {
			power.setId(SNHelper.getSNService().getSerialNumber(
				Power.class.getName(), "id", false));
		}
		this.powerDao.save(power);
		CacheHelper.getInstance().dispatchRefreshEvent(Power.TREE_DIC_IDENTIFICATION);
		return power;

	}

	/**
	 * @see com.console.power.service.IPowerService#deletePower(java.lang.String)
	 */
	public void deletePower(String[] id) {
		if (id == null || id.length == 0) {
			return;
		}
		for (String powerId : id) {
			Power power = this.selectPower(powerId);

			List<Role> roles = this.selectRoleByPowerId(powerId);

			for (Role role : roles) {
				role.getPowers().remove(power);
				this.powerDao.update(role);
			}

			this.powerDao.deleteAll(power.getDepartments());
			this.powerDao.delete(power);
		}
        CacheHelper.getInstance().dispatchRefreshEvent(Power.TREE_DIC_IDENTIFICATION);

	}

	/**
	 * @see com.console.power.service.IPowerService#selectPower(java.lang.String)
	 */
	public Power selectPower(String id) {
		Power power = (Power) this.powerDao.load(Power.class, id);
		this.powerDao.initialize(power.getDepartments());
		return power;
	}

	/**
	 * @see com.console.power.service.IPowerService#updatePowerGrantInfo(com.console.power.command.PowerGrantInfo)
	 */
	public void updatePowerGrantInfo(PowerGrantInfo info) {
		Power power = this.selectPower(info.getPowerId().toString());

		power.getDepartments().clear();
		if (info != null && info.getDepId() != null) {
			String[] ids = info.getDepId().split(",");
			for (int i = 0; i < ids.length; ++i) {
				power.getDepartments().add(
					this.powerDao.load(Department.class, new Integer(ids[i])));
			}
		}
		this.powerDao.save(power);
	}


	/**
	 * @see com.console.power.service.IPowerService#selectAllPower()
	 */
	public List selectAllPower(PowerQueryInfo info) {
		return this.powerDao.selectAllPower(info);
	}

	/**
	 * @param powerId
	 * @return
	 */
	private List<Role> selectRoleByPowerId(String powerId) {
		List<Role> role = this.powerDao.selectRoleByPowerId(powerId);
		return role;
	}

    /**
     * @see org.springline.beans.cache.ICacheConnector#loadCacheObject(java.lang.String)
     */
    @Override
    public Object loadCacheObject(String objName) {
          return LayerTreeBuilder.createTree(this.selectAllPower(null));
    }

	// /**
	// * @see
	// com.console.power.service.IPowerService#selectChildPower(java.lang.String)
	// */
	// public List selectChildPower(String parentPowerCode) {
	// return this.powerDao.selectChildPower(parentPowerCode);
	// }
}
