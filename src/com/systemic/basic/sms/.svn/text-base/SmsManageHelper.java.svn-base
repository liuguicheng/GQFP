package com.systemic.basic.sms;

import java.util.Observable;


public abstract class SmsManageHelper extends Observable{
	
	/**
     * 单实例的类工厂 Spring IOC控制
     */
	private static SmsManageHelper instance = null;
	
	
	public static void setInstance(SmsManageHelper instance) {
		SmsManageHelper.instance = instance;
	}
	
	/**
	 * 返回SmsManagehHelper的实例对象
	 */
	public static SmsManageHelper getInstance(){
		if (instance==null){
			throw new RuntimeException("未创建WebsiteHelper的实例对象！");
		}
		return instance;
	}
	/**
	 * 传入发送内容、手机号码
	 * @param contend
	 * @param mobileNum
	 * @return
	 */
	public abstract void smsClientSupport(String content,String mobileNum);
	
	/**
	 * 传入发送信息类型、手机号码
	 * @param contend
	 * @param mobileNum
	 * @return
	 */
	public abstract String smsSubClientSupport(String smsType);
	
	/**
	 * 传入发送信息类型、手机号码
	 * @param contend
	 * @param mobileNum
	 * @return
	 */
	public abstract String smsSendClientSupport(String smsType,String mobileNum);

}
