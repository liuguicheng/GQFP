package com.console.operate.command;

import org.springline.web.mvc.SpringlineCommand;

public class OperateQueryInfo extends SpringlineCommand{

	/**
	 *
	 */
	private static final long serialVersionUID = -9090878053454262218L;
	/** 权限编码. */
    private String operateCode;
    /** 权限名称. */
    private String operateName;

	/**
	 * @return the operateName
	 */
	public String getOperateName() {
		return operateName;
	}
	/**
	 * @param operateName the operateName to set
	 */
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	/**
	 * @return the operateCode
	 */
	public String getOperateCode() {
		return operateCode;
	}
	/**
	 * @param operateCode the operateCode to set
	 */
	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}
}
