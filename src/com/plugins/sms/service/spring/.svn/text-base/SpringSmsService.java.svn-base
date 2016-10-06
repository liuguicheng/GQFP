package com.plugins.sms.service.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springline.orm.Page;

import com.plugins.sms.SmsReplyHandlerChain;
import com.plugins.sms.command.SmsEditInfo;
import com.plugins.sms.command.SmsInfo;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.command.SmsReplyEditInfo;
import com.plugins.sms.command.SmsReplyQueryInfo;
import com.plugins.sms.entity.SysSms;
import com.plugins.sms.entity.SysSmsHistory;
import com.plugins.sms.entity.SysSmsReply;
import com.plugins.sms.entity.SysSmsReplyHistory;
import com.plugins.sms.service.ISmsService;
import com.plugins.sms.service.dao.ISmsDao;

public class SpringSmsService implements ISmsService {

    /** dao注入 */
    private ISmsDao smsDao;

    public void setSmsDao(ISmsDao smsDao) {
        this.smsDao = smsDao;
    }

    @Override
    public Page selectSmsPage(SmsQueryInfo info) {
        Assert.notNull(info);
        return this.smsDao.selectSmsPage(info);
    }

    @Override
    public SysSms selectSms(String recordId) {
        Assert.notNull(recordId);
        return (SysSms) this.smsDao.load(SysSms.class, recordId);
    }

    @Override
    public SysSms saveSms(SmsEditInfo info) {
        Assert.notNull(info);
        SysSms entity = null;
        if (StringUtils.isNotBlank(info.getRecordId())) {
            entity = (SysSms) this.smsDao.load(SysSms.class, info.getRecordId());
        } else {
            entity = new SysSms();
        }
        BeanUtils.copyProperties(info, entity, new String[] { "recordId" });
        this.smsDao.save(entity);
        return entity;
    }

    @Override
    public void deleteSms(String[] ids) {
        Assert.notNull(ids);
        for (String id : ids) {
            SysSms entity = (SysSms) this.smsDao.load(SysSms.class, id);
            this.smsDao.delete(entity);
        }
    }

    @Override
    public Page selectSmsHistoryPage(SmsQueryInfo info) {
        Assert.notNull(info);
        return this.smsDao.selectSmsHistoryPage(info);
    }

    @Override
    public SysSmsHistory selectSmsHistory(String recordId) {
        Assert.notNull(recordId);
        return (SysSmsHistory) this.smsDao.load(SysSmsHistory.class, recordId);
    }

    @Override
    public void deleteSmsHistory(String[] ids) {
        Assert.notNull(ids);
        for (String id : ids) {
            SysSmsHistory entity = (SysSmsHistory) this.smsDao.load(SysSmsHistory.class, id);
            this.smsDao.delete(entity);
        }
    }

    @Override
    public Page selectSmsViewPage(SmsQueryInfo info) {
        Assert.notNull(info);
        return this.smsDao.selectSmsViewPage(info);
    }

    @Override
    public Page selectSmsReplyPage(SmsReplyQueryInfo info) {
        Assert.notNull(info);
        return this.smsDao.selectSmsReplyPage(info);
    }

    @Override
    public Page selectSmsReplyHistoryPage(SmsReplyQueryInfo info) {
        Assert.notNull(info);
        return this.smsDao.selectSmsReplyHistoryPage(info);
    }

    @Override
    public Page selectSmsReplyViewPage(SmsReplyQueryInfo info) {
        Assert.notNull(info);
        return this.smsDao.selectSmsReplyViewPage(info);
    }

    @Override
    public SysSmsReply saveSmsReply(SmsReplyEditInfo info) {
        Assert.notNull(info);
        SysSmsReply entity = null;
        if (StringUtils.isNotBlank(info.getReplyId())) {
            entity = (SysSmsReply) this.smsDao.load(SysSmsReply.class, info.getReplyId());
        } else {
            entity = new SysSmsReply();
        }
        BeanUtils.copyProperties(info, entity, new String[] { "replyId" });
        this.smsDao.save(entity);
        return entity;
    }

    @Override
    public boolean doSendSms(SmsInfo info) {
        Assert.notNull(info.getReceiverNames());
        Assert.notNull(info.getReceiverIds());
        boolean result = true;
        try {
            Date now = new Date();
           // List<String> relList = SmsAdapterFactory.getInstance().getAdapterService() .sendMessage(info.getSmsInfo(), info.getReceiveNums());

            // 保存短信息记录
            String[] rNums = info.getReceiveNums().split(",");
            String[] rNames = info.getReceiverNames().split(",");
            String[] rIds = info.getReceiverIds().split(",");
            if (rNums != null) {
                int len = rNums.length;
                SysSms sms = null;
                for (int i = 0; i < len; i++) {
                    if (StringUtils.isNotBlank(rNums[i])) {
                        sms = new SysSms();
                        sms.setSmsInfo(info.getSmsInfo());
                        sms.setSenderId(info.getSenderId());
                        sms.setSenderName(info.getSenderName());
                        sms.setReceiveNum(rNums[i]);
                        if (rIds != null && rIds.length == rNums.length) {
                            sms.setReceiverId(rIds[i]);
                        }
                        if (rNames != null && rNames.length == rNums.length) {
                            sms.setReceiverName(rNames[i]);
                        }
                        
                        //sms.setSmsId(); 默认为空的
                      // 向短信服务提交成功，记录
                      sms.setSmsState(SysSms.STATE_SENDED);
                        sms.setCreateTime(now);
                        this.smsDao.save(sms);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        return result;
    }
}
