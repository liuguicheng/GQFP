/**
 * Description:
 * History:  2010-9-20 Create
 **/

package com.plugins.msg.service.spring;

import java.util.Date;
import java.util.List;

import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.entity.SysMessage;
import com.plugins.msg.service.IMsgService;
import com.plugins.msg.service.dao.IMsgDao;

/**
 * @description
 */
public class SpringMsgService implements IMsgService {

	private IMsgDao msgDao;

	/**
	 * @see com.plugins.msg.service.IMsgService#insertMessage(java.lang.String,
	 *      java.util.List)
	 */
	public void insertMessage(String msgContent, List<Staff> receiveMen) {
		insertMessage(msgContent, receiveMen, null);

	}

	/**
	 * @see com.plugins.msg.service.IMsgService#insertMessage(java.lang.String,
	 *      java.util.List, java.lang.String)
	 */
	public void insertMessage(String msgContent, List<Staff> receiveMen, String url) {
		for (Staff receiveMan : receiveMen) {
			insertMessage(msgContent, receiveMan, url);
		}

	}

	/**
	 * @see com.plugins.msg.service.IMsgService#insertMessage(java.lang.String,
	 *      com.console.entity.Staff)
	 */
	public void insertMessage(String msgContent, Staff receiveMan) {
		insertMessage(msgContent, receiveMan, null);

	}

	/**
	 * @see com.plugins.msg.service.IMsgService#insertMessage(java.lang.String,
	 *      com.console.entity.Staff, java.lang.String)
	 */
	public void insertMessage(String msgContent, Staff receiveMan, String url) {
		insertMessage(msgContent, receiveMan.getId(), url);
	}

	/**
	 * @see com.plugins.msg.service.IMsgService#insertMessage(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void insertMessage(String msgContent, String receiveManId, String url) {
		SysMessage msg = new SysMessage();
		msg.setContent(msgContent);
		msg.setSendTime(new Date());
		msg.setSendMan("0"); // messageÕÊºÅµÄId
		msg.setReceiveMan(receiveManId);
		msg.setIsReaded(ConsoleHelper.NO);
		this.msgDao.save(msg);
	}

	/**
	 * @see com.plugins.msg.service.IMsgService#insertMessageCorp(java.lang.
	 *      String, String Id, java.lang.String)
	 */
	public void insertMessageCorp(String msgContent, String buildCorpId, String url) {
		SysMessage msg = new SysMessage();
		msg.setContent(msgContent);
		msg.setSendTime(new Date());
		msg.setSendMan("0"); // messageÕÊºÅµÄId
		msg.setReceiveMan(buildCorpId);
		// by ydl 20130906
		msg.setIsReaded(ConsoleHelper.NO);
		this.msgDao.save(msg);
	}

	/**
	 * @param msgDao
	 *            the msgDao to set
	 */
	public void setMsgDao(IMsgDao msgDao) {
		this.msgDao = msgDao;
	}

	@Override
	public Page selectMessage(MessageQueryInfo info) {
		return this.msgDao.selectMessage(info);
	}

	@Override
	public void deleteMessage(String[] ids) {
		for (String id : ids) {
			SysMessage msg = (SysMessage) this.msgDao.load(SysMessage.class, id);
			if (msg != null) {
				msg.setIsReaded(ConsoleHelper.YES);
				this.msgDao.update(msg);
			}
		}
	}

	@Override
	public Object selectMessageCount(String staffId) {
		return this.msgDao.selectMessageCount(staffId);
	}

	@Override
	public SysMessage insertMessage(String msgContent, String receiveManId, String senderId, String url) {
		SysMessage msg = new SysMessage();
		msg.setContent(msgContent);
		msg.setSendTime(new Date());
		msg.setSendMan(senderId); // messageÕÊºÅµÄId
		msg.setReceiveMan(receiveManId);
		msg.setChatState(ConsoleHelper.NO);
		// setUrl
		// by ydl 20130906
		msg.setIsReaded(ConsoleHelper.YES);
		this.msgDao.save(msg);
		return msg;

	}

	@Override
	public List<SysMessage> selectMessageHistory(String sendMan, String receiveMan, String sendTime) {
		return this.msgDao.selectMessageHistory(sendMan, receiveMan, sendTime);
	}

	@Override
	public void insertMessageCorp(SysMessage mesg) {
		this.msgDao.save(mesg);

	}

	@Override
	public Page selectInstantMessage(MessageQueryInfo info) {
		return this.msgDao.selectInstantMessage(info);
	}

	@Override
	public void deletePhyMessage(String[] ids) {
		for (String id : ids) {
			SysMessage msg = (SysMessage) this.msgDao.load(SysMessage.class, id);
			if (msg != null) {
				this.msgDao.delete(msg);
			}
		}

	}

	@Override
	public Page selectChatMessage(MessageQueryInfo info) {
		return this.msgDao.selectChatMessage(info);
	}

	@Override
	public void insertMessageFor(String msgContent, String title, String messageType, String sendId) {

		SysMessage msg = new SysMessage();
		msg.setContent(msgContent);
		msg.setSendTime(new Date());
		msg.setSendMan(sendId); // messageÕÊºÅµÄId
		msg.setMessageTitel(title);
		msg.setIsReaded(ConsoleHelper.NO);
		msg.setMessageType(messageType);
		this.msgDao.save(msg);
	}

	@Override
	public SysMessage selectMessageById(String id) {
		return (SysMessage) this.msgDao.load(SysMessage.class, id);
	}

	@Override
	public void updateMessage(SysMessage sysmessage) {
		this.msgDao.update(sysmessage);

	}

	@Override
	public void deleteMessageForDel(String[] ids) {
		for (String id : ids) {
			SysMessage msg = (SysMessage) this.msgDao.load(SysMessage.class, id);
			if (msg != null) {
				this.msgDao.delete(msg);
			}
		}
		
	}

	@Override
	public void insertMessageForEmail(String receiveMan, String content, String title, String string, String memberId,String sendman) {
		SysMessage msg = new SysMessage();
		msg.setContent(content);
		msg.setSendTime(new Date());
		msg.setSendMan(sendman); // 
		msg.setSendManId(memberId);
		msg.setMessageTitel(title);
		msg.setIsReaded(ConsoleHelper.NO);
		msg.setMessageType(string);
		msg.setReceiveMan(receiveMan);
		this.msgDao.save(msg);
	}
}
