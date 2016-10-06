package com.plugins.msg.entity;

import java.util.Date;

/**
 * SysMessageInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysMessage implements java.io.Serializable {

    // Fields

    /**
     *
     */
    private static final long serialVersionUID = 5358544456122828938L;
    /**
     * 简单字典标识
     */
    public static final String SIMPLE_DIC_IDENTIFICATION = "dicMessageType";
    private String sysMessageInfoId;
    private Date sendTime;
    private String sendMan;//发送者
    private String receiveMan;//接受者
    private String content;
    /** 是否已读 by ydl 20130906 */
    private String isReaded;
    /**添加聊天状态为了点击人聊天时就只显示当前聊天消息*/
    private String chatState;
    private String sendManId;
    private String messageType;//消息类型
    private String messageTitel;//标题
    // Constructors

    // Property accessors

    public String getSysMessageInfoId() {
        return this.sysMessageInfoId;
    }

    public void setSysMessageInfoId(String sysMessageInfoId) {
        this.sysMessageInfoId = sysMessageInfoId;
    }

    public Date getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendMan() {
        return this.sendMan;
    }

    public void setSendMan(String sendMan) {
        this.sendMan = sendMan;
    }

    public String getReceiveMan() {
        return this.receiveMan;
    }

    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(String isReaded) {
        this.isReaded = isReaded;
    }

	public String getChatState() {
		return chatState;
	}

	public void setChatState(String chatState) {
		this.chatState = chatState;
	}

	public String getSendManId() {
		return sendManId;
	}

	public void setSendManId(String sendManId) {
		this.sendManId = sendManId;
	}

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the messageTitel
	 */
	public String getMessageTitel() {
		return messageTitel;
	}

	/**
	 * @param messageTitel the messageTitel to set
	 */
	public void setMessageTitel(String messageTitel) {
		this.messageTitel = messageTitel;
	}

	
	
}