/**
 * Description:
 * History:  2014-7-9 Create
 **/

package com.plugins.sms;

import java.util.ArrayList;
import java.util.List;

import com.plugins.sms.entity.SysSmsReply;

/**
 * 短信回复响应对象链
 * 
 * @description
 */
public final class SmsReplyHandlerChain {

    public static SmsReplyHandlerChain getInstance() {
        return new SmsReplyHandlerChain();
    }

    private static List<ISmsReplyHandler> handlers = new ArrayList<ISmsReplyHandler>();
    /** 当前处理链的位置 */
    private int curInx;

    /**
     * 注册handler
     * 
     * @param handler
     */
    public static void registerHandler(ISmsReplyHandler handler) {
        handlers.add(handler);
    }

    /**
     * 决定下一步处理的节点
     * 
     * @param reply
     */
    public boolean handleReply(SysSmsReply reply) {
        System.out.println("curInx:" + curInx + ",size:" + handlers.size());
        if (handlers != null && handlers.size() > 0) {
            // if (curInx >= handlers.size()) {
            // // 已经走完整个链，复位
            // curInx = 0;
            // } else {
            // // 继续下一位置
            // if (handlers.get(curInx++).handleReply(reply, this)) {
            // // 已经处理成功，复位
            // curInx = 0;
            // return true;
            // }
            // }
            if (curInx < handlers.size()) {
                return handlers.get(curInx++).handleReply(reply, this);
            }
        }
        return false;
    }
}
