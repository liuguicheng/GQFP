package com.systemic.basic.sms.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;

import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.command.SmsQueryInfo;

public interface ISmsDao extends ICommonDao {
	/**
	 * 查询所有短信内容信息
	 * 
	 * @return
	 */
	List<SysSmsType> selectAllSms(SmsQueryInfo info);

}
