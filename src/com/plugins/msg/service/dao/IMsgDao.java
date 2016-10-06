/**
 * Description:
 * History:  2010-9-20 Create
 **/

package com.plugins.msg.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.entity.SysMessage;

/**
 * @description
 */
public interface IMsgDao extends ICommonDao {

    /**
     * 分页查询系统消息
     * 
     * @param staffId
     * @return
     */
    Page selectMessage(MessageQueryInfo info);
    
    /**
     * 分页查询聊天消息
     * 
     * @param staffId
     * @return
     */
    Page selectInstantMessage(MessageQueryInfo info);
    
    /**
     * 分页查询发送人、接收人聊天消息
     * 
     * @param info
     * @return
     */
    Page selectChatMessage(MessageQueryInfo info);

    /**
     * 查询未读系统消息数量
     * 
     * @param staffId
     * @return
     */
    Object selectMessageCount(String staffId);
    
    /**
     * 查询历史聊天消息
     * 
     * @param info
     * @return
     * @author mayu 2015-05-22
     */
    List<SysMessage> selectMessageHistory(String sendMan,String receiveMan,String sendTime);
}
