/**
 * Description:
 * History:  2014-10-23 Create
**/

package com.console.power;

import org.springframework.beans.factory.InitializingBean;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;

import com.console.entity.Power;
import com.console.power.service.IPowerService;

/**
 * @description 
 */
public class PowerConfig implements InitializingBean {

    private IPowerService powerService;
    
    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        CacheHelper.getInstance().registerConnector(Power.TREE_DIC_IDENTIFICATION, 
                (ICacheConnector) powerService);
    }

    /**
     * @param powerService the powerService to set
     */
    public void setPowerService(IPowerService powerService) {
        this.powerService = powerService;
    }

}
