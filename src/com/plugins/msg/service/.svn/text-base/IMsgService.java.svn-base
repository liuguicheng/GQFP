/**
 * Description:
 * History:  2010-9-20 Create
 **/

package com.plugins.msg.service;

import java.util.List;

import org.springline.orm.Page;

import com.console.entity.Staff;
import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.entity.SysMessage;

/**
 * @description
 */
public interface IMsgService {

    /**
     * 发送系统消息
     * 
     * @param msgContent
     *            消息内容
     * @param receiveMen
     *            接收人清单
     */
    void insertMessage(String msgContent, List<Staff> receiveMen);

    /**
     * 发送系统消息，未启用
     * 
     * @param msgContent
     *            消息内容
     * @param receiveMen
     *            接收人清单
     * @param url
     *            回调的Url地址
     */
    void insertMessage(String msgContent, List<Staff> receiveMen, String url);

    /**
     * 发送系统消息
     * 
     * @param msgContent
     *            消息内容
     * @param receiveMen
     *            接收人
     * @param url
     *            回调的Url地址
     */
    void insertMessage(String msgContent, Staff receiveMan);

    /**
     * 发送系统消息，未启用
     * 
     * @param msgContent
     *            消息内容
     * @param receiveMen
     *            接收人
     * @param url
     *            回调的Url地址
     */
    void insertMessage(String msgContent, Staff receiveMan, String url);
    /**
     * 发送系统消息
     * 
     * @param msgContent
     *            消息内容
     * @param receiveMenId 
     *            接收人 Id
     * @param url
     *            回调的Url地址
     */
    void insertMessage(String msgContent, String receiveManId, String url);    
    
    /**
     * 发送系统消息
     * 
     * @param msgContent
     *            消息内容
     * @param receiveMenId 
     *            接收人 Id
     * @param url
     *            回调的Url地址
     * @return 
     */
    SysMessage insertMessage(String msgContent, String receiveManId,String senderId, String url); 
    /**
     * 给企业发送系统消息，未启用by my2013-12-05
     * 
     * @param msgContent
     *            消息内容
     * @param receiveMen
     *            接收人
     * @param url
     *            回调的Url地址
     */
    void insertMessageCorp(String msgContent, String buildCorpId, String url);
    /**
     * 改变聊天状态
     * 
     * @param msgContent
     *            消息内容
     * @param receiveMen
     *            接收人
     * @param url
     *            回调的Url地址
     */
    void insertMessageCorp(SysMessage mesg);

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
     * @param info
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
     * 查询历史聊天消息
     * 
     * @param info
     * @return
     * @author mayu 2015-05-22
     */
    List<SysMessage> selectMessageHistory(String sendMan,String receiveMan,String sendTime);


    /**
     * 查询未读系统消息数量
     * 
     * @param staffId
     * @return
     */
    Object selectMessageCount(String staffId);

    /**
     * 移除系统消息，逻辑删除
     * 
     * @param ids
     */
    void deleteMessage(String[] ids);
    
    /**
     * 删除消息，物理删除
     * 
     * @param ids
     */
    void deletePhyMessage(String[] ids);
    
    /**
     * 添加消息
     * 参数：标题、内容、消息类型、发送人
     */
    void insertMessageFor(String msgContent, String title, String messageType,String sendId);

	SysMessage selectMessageById(String id);

	void updateMessage(SysMessage sysmessage);

	void deleteMessageForDel(String[] ids);

	void insertMessageForEmail(String receiveMan, String content, String title, String string, String memberId,String sendman);
}
