/**
 * Description:
 * History:  2013-6-13 Create
 **/

package com.systemic.basic.sms.command;

import org.springline.web.mvc.SpringlineCommand;

/**
 * @description
 */
public class SmsEditInfo extends SpringlineCommand {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3551004172347521384L;
	/** 短信内容id */
	private String smsId;
	/** 短信内容类型 */
	private String smsType;
	/** 短信内容 */
	private String smsContent;
	/**备注*/
	private String remark;

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
