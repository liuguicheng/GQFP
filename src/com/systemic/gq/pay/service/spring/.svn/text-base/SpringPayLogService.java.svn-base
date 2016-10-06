package com.systemic.gq.pay.service.spring;

import java.util.Date;

import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.entity.PayLog;
import com.systemic.gq.pay.command.PayLogQueryInfo;
import com.systemic.gq.pay.service.ISpringPayLogService;
import com.systemic.gq.pay.service.dao.IPayLogDao;

public class SpringPayLogService implements ISpringPayLogService {
	private IPayLogDao payLogDao;

	public void setPayLogDao(IPayLogDao payLogDao) {
		this.payLogDao = payLogDao;
	}

	@Override
	public void savePayLog(String logContent,double money,String operationType,String memberId) {
		PayLog pl = new PayLog();
		pl.setRecordTime(new Date());
		pl.setHandleId(memberId);
		pl.setHandleLocalHost(ConsoleHelper.getUserIp());
		pl.setLogContent(logContent);
		pl.setOperationType(operationType);
		pl.setPayMoeny(money);
		this.payLogDao.save(pl);

	}

	@Override
	public Page selectPayLog(PayLogQueryInfo info) {

		return this.payLogDao.selectPayLog(info);
	}

	@Override
	public PayLog loadPayLog(String id) {
		return (PayLog) this.payLogDao.load(PayLog.class, id);
	}

}
