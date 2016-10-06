package com.plugins.sms.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.command.SmsReplyQueryInfo;

public interface ISmsDao extends ICommonDao {
    /**
     * 分页查询短信发送记录
     * 
     * @param info
     * @return
     */
    Page selectSmsPage(SmsQueryInfo info);

    /**
     * 分页查询短信发送历史记录
     * 
     * @param info
     * @return
     */
    Page selectSmsHistoryPage(SmsQueryInfo info);

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
}
