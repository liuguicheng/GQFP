package com.systemic.basic.sms.service;

import java.util.List;

import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.command.SmsEditInfo;
import com.systemic.basic.sms.command.SmsQueryInfo;

public interface ISmsService {
	/**
	 * 查询所有短信内容信息
	 * 
	 * @return
	 */
	List<SysSmsType> selectAllSms(SmsQueryInfo info);

	/**
	 * 根据Id获取短信内容信息
	 * 
	 * @param sysSmsId
	 * @return
	 */
	SysSmsType loadSmsById(String sysSmsId);

	/**
	 * 保存短信内容信息
	 * 
	 * @param info
	 * @return
	 */
	SysSmsType saveSms(SmsEditInfo info);

	/**
	 * 根据Id删除短信内容信息
	 * 
	 * @param ids
	 */
	void deleteSms(String[] ids);
	
	/**
	 * 通过短信类型查询短信内容信息
	 * 
	 * @param smsType
	 */
	List<SysSmsType> selectSmsTypeList(String smsType);
}
