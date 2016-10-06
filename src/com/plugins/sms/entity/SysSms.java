package com.plugins.sms.entity;

import java.util.Date;

/**
 * SysSms entity. @author MyEclipse Persistence Tools
 */

public class SysSms implements java.io.Serializable {
    private static final long serialVersionUID = 2564209094321363882L;

    /** 管理权限：可以查看所有人发送的短信 */
    public static final String OPERATE_CODE_MANAGE = "408001";

    /** 短信状态：发送失败 */
    public static final String STATE_SEND_FAIL = "9";
    /** 短信状态：提交失败 */
    public static final String STATE_CREATE_FAIL = "8";
    /** 短信状态：已提交 */
    public static final String STATE_SENDED = "0";
    /** 短信状态：发送成功 */
    public static final String STATE_RECEIVED = "1";
    /** 短信状态：收到回复 */
    public static final String STATE_REPLYED = "2";

    /** 记录ID */
    private String recordId;
    /** 接收号码 */
    private String receiveNum;
    /** 接收人姓名 */
    private String receiverName;
    /** 接收人ID */
    private String receiverId;
    /** 发送人姓名 */
    private String senderName;
    /** 发送人ID */
    private String senderId;
    /** 发送时间 */
    private Date sendTime;
    /** 短信状态 */
    private String smsState;
    /** 短信息内容 */
    private String smsInfo;
    /** 短信息ID */
    private String smsId;

    /** 创建时间 */
    private Date createTime;

    // Property accessors

    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getReceiveNum() {
        return this.receiveNum;
    }

    public void setReceiveNum(String receiveNum) {
        this.receiveNum = receiveNum;
    }

    public String getReceiverName() {
        return this.receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverId() {
        return this.receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSmsState() {
        return smsState;
    }

    public void setSmsState(String smsState) {
        this.smsState = smsState;
    }

    public String getSmsInfo() {
        return this.smsInfo;
    }

    public void setSmsInfo(String smsInfo) {
        this.smsInfo = smsInfo;
    }

    public String getSmsId() {
        return this.smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}