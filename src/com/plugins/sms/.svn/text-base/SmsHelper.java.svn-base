package com.plugins.sms;

import org.springline.orm.Page;

import com.plugins.sms.command.SmsEditInfo;
import com.plugins.sms.command.SmsInfo;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.entity.SysSms;

/**
 * 单实例Helper对象
 * @description 
 */
public abstract class SmsHelper {

    private static SmsHelper instance;

    public static SmsHelper getInstance() {
        return instance;
    }

    protected void setInstance(SmsHelper instance) {
        SmsHelper.instance = instance;
    }


    /**
     * 发送短信息
     * 
     * @param info
     */
    public abstract boolean sendSms(SmsInfo info);

    /**
     * 查询短信息发送记录
     * 
     * @param info
     * @return
     */
    public abstract Page selectSmsPage(SmsQueryInfo info);
    
    /**
     * 新增、修改短信发送记录
     * 
     * @param info
     * @return
     */
    public abstract  SysSms saveSms(SmsEditInfo info);
}
