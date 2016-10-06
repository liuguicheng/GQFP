package com.systemic.basic.sms;

import org.springframework.beans.factory.InitializingBean;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;

import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.service.ISmsService;

public final class SmsHelperConfig implements InitializingBean {

    private ISmsService smsService;

    /**
     * @param roleService the roleService to set
     */
    public void setRoleService(ISmsService smsService) {
        this.smsService = smsService;
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        CacheHelper.getInstance().registerConnector(SysSmsType.DIC_SMS_CONTENT, (ICacheConnector) smsService);
        
    }
    
}
