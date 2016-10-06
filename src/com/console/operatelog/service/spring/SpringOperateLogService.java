package com.console.operatelog.service.spring;

import java.util.Date;

import org.springline.orm.Page;

import com.console.entity.OperateLog;
import com.console.entity.Staff;
import com.console.operatelog.command.OperateLogQueryInfo;
import com.console.operatelog.service.IOperateLogService;
import com.console.operatelog.service.dao.IOperateLogDao;
import com.systemic.gq.entity.Member;

public class SpringOperateLogService implements IOperateLogService{

	/** */
	private IOperateLogDao operateLogDao;

	/**
	 * @param operateLogDao the operateLogDao to set
	 */
	public void setOperateLogDao(IOperateLogDao operateLogDao) {
		this.operateLogDao = operateLogDao;
	}

	/**
	 *
	 * @see com.console.operatelog.service.IOperateLogService#saveOperateLog(java.lang.String, com.console.entity.Staff, java.lang.String)
	 */
	public void saveOperateLog(String logType, Staff staff, String logContent) {

		OperateLog log = new OperateLog();
		log.setLogType(logType);
		log.setOperatorId(staff.getId());
		log.setOperatorName(staff.getName());
		log.setOpDate(new Date());
		log.setLogContent(logContent);
		this.operateLogDao.save(log);
	}

	/**
	 * 日志信息分页
	 * @see com.console.operatelog.service.IOperateLogService#selectOperateLogList(com.console.operatelog.command.OperateLogQueryInfo)
	 */
	public Page selectOperateLogList(OperateLogQueryInfo info) {

		return this.operateLogDao.selectOperateLogList(info);
	}

	/**
	 * 删除
	 * @see com.console.operatelog.service.IOperateLogService#deleteOperateLog(java.lang.String[])
	 */
	public void deleteOperateLog(String[] id) {

		if(id == null || id.length == 0){
			return;
		}
		for(String logId : id){
			OperateLog log = (OperateLog) this.operateLogDao.load(OperateLog.class, logId);
			this.operateLogDao.delete(log);
		}
	}

	@Override
	public void saveOperateLogForMember(String logType, Member member, String logContent) {
		OperateLog log = new OperateLog();
		
		log.setLogType(logType);
		log.setOperatorId(member.getMemberId());
		log.setOperatorName(member.getUserName());
		log.setOpDate(new Date());
		log.setLogContent(logContent);
		this.operateLogDao.save(log);
		
	}

}
