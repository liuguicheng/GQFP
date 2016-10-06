package com.plugins.sms.support;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springline.orm.Page;

import com.plugins.sms.SmsHelper;
import com.plugins.sms.command.SmsEditInfo;
import com.plugins.sms.command.SmsInfo;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.entity.SysSms;
import com.plugins.sms.service.ISmsService;

public class SmsHelperImpl extends SmsHelper implements InitializingBean {

    /** 本地短信服务 */
    private ISmsService smsService;

    public void setSmsService(ISmsService smsService) {
        this.smsService = smsService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setInstance(this);
    }

    /**
     * 发送短信息
     * 
     * @param info
     */
    public boolean sendSms(SmsInfo info) {
        if (this.smsService == null) {
            throw new DataIntegrityViolationException("未创建SmsHelper实例对象！");
        }

        return this.smsService.doSendSms(info);
    }

    /**
     * 查询短信息发送记录
     * 
     * @param info
     * @return
     */
    public Page selectSmsPage(SmsQueryInfo info) {
        if (smsService == null) {
            throw new DataIntegrityViolationException("未创建SmsHelper实例对象！");
        }

        return this.smsService.selectSmsViewPage(info);
    }

    /**
     * 新增、修改短信发送记录
     * 
     * @param info
     * @return
     */
    public SysSms saveSms(SmsEditInfo info) {
        if (this.smsService == null) {
            throw new DataIntegrityViolationException("未创建SmsHelper实例对象！");
        }

        return this.smsService.saveSms(info);
    }
}
