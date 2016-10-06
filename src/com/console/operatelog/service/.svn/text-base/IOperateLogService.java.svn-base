package com.console.operatelog.service;

import org.springline.orm.Page;

import com.console.entity.Staff;
import com.console.operatelog.command.OperateLogQueryInfo;
import com.systemic.gq.entity.Member;

public interface IOperateLogService {

	/**
	 * 保存日志
	 * @param logType 日志类型
	 * @param staff   操作人
	 * @param logContent  日志内容
	 */
	void saveOperateLog(String logType,Staff staff,String logContent);

	/**
	 *   删除日志信息.
	 * @param id
	 */
	void deleteOperateLog(String[] id);

	/**
     *  日志信息分页
     * @param info
     * @return page 分页对象
     */
	Page selectOperateLogList(OperateLogQueryInfo info);
	
	
	/**
	 * 保存日志
	  * @param logType 日志类型
	 * @param member   操作人（修改后）
	 * @param logContent  日志内容
	 */
	void saveOperateLogForMember(String logType,Member member,String logContent); 
}
