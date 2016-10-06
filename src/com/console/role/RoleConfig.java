/**
 * Description:
 * History:  2014-10-22 Create
**/

package com.console.role;

import org.springframework.beans.factory.InitializingBean;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;

import com.console.entity.Role;
import com.console.role.service.IRoleService;

/**
 * @description 
 */
public final class RoleConfig implements InitializingBean {

    private IRoleService roleService;

    /**
     * @param roleService the roleService to set
     */
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        CacheHelper.getInstance().registerConnector(Role.DIC_ROLE, 
                (ICacheConnector) roleService);
        
    }
    
}
