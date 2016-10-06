package com.plugins.sms.support;

import org.springframework.beans.factory.InitializingBean;

import com.plugins.sms.ISmsReplyHandler;
import com.plugins.sms.SmsReplyHandlerChain;
import com.plugins.sms.entity.SysSmsReply;

/**
 * 短信回复处理器
 * 
 * @author ydl 2014-6-25
 * 
 */
public class SmsReplyHandler implements ISmsReplyHandler, InitializingBean {

    /**
     * 处理回复
     * 
     * @param reply
     * @return
     */
    public boolean handleReply(SysSmsReply reply, SmsReplyHandlerChain chain) {
        return chain.handleReply(reply);
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        SmsReplyHandlerChain.registerHandler(this);
        
    }
}
