package com.plugins.sms.service;

import org.springline.orm.Page;

import com.plugins.sms.command.SmsEditInfo;
import com.plugins.sms.command.SmsInfo;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.command.SmsReplyEditInfo;
import com.plugins.sms.command.SmsReplyQueryInfo;
import com.plugins.sms.entity.SysSms;
import com.plugins.sms.entity.SysSmsHistory;
import com.plugins.sms.entity.SysSmsReply;

/**
 * 短信息服务接口
 * 
 * @author ydl 2014-5-15
 * 
 */
public interface ISmsService {

    /**
     * 分页查询短信发送记录
     * 
     * @param info
     * @return
     */
    Page selectSmsPage(SmsQueryInfo info);

    /**
     * 根据业务ID查询短信发送记录
     * 
     * @param recordId
     * @return
     */
    SysSms selectSms(String recordId);

    /**
     * 新增、修改短信发送记录
     * 
     * @param info
     * @return
     */
    SysSms saveSms(SmsEditInfo info);

    /**
     * 删除短信发送记录
     * 
     * @param ids
     */
    void deleteSms(String[] ids);

    /**
     * 分页查询短信发送历史记录
     * 
     * @param info
     * @return
     */
    Page selectSmsHistoryPage(SmsQueryInfo info);

    /**
     * 根据业务ID查询短信发送历史记录
     * 
     * @param recordId
     * @return
     */
    SysSmsHistory selectSmsHistory(String recordId);

    /**
     * 删除短信发送历史记录
     * 
     * @param ids
     */
    void deleteSmsHistory(String[] ids);

    /**
     * 分页查询短信发送所有记录，包括历史和当前的
     * 
     * @param info
     * @return
     */
    Page selectSmsViewPage(SmsQueryInfo info);

    /**
     * 分页查询短信回复当前记录
     * 
     * @param info
     * @return
     */
    Page selectSmsReplyPage(SmsReplyQueryInfo info);

    /**
     * 分页查询短信回复历史记录
     * 
     * @param info
     * @return
     */
    Page selectSmsReplyHistoryPage(SmsReplyQueryInfo info);

    /**
     * 分页查询短信回复所有记录，包括历史和当前的
     * 
     * @param info
     * @return
     */
    Page selectSmsReplyViewPage(SmsReplyQueryInfo info);

    /**
     * 保存回复
     * 
     * @param info
     * @return
     */
    SysSmsReply saveSmsReply(SmsReplyEditInfo info);

    /**
     * 下派短信
     * 
     * @return
     */
    boolean doSendSms(SmsInfo info);

}
