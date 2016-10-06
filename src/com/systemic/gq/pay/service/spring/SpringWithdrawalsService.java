package com.systemic.gq.pay.service.spring;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.systemic.gq.entity.Withdrawals;
import com.systemic.gq.pay.command.WithdrawalsEdit;
import com.systemic.gq.pay.command.WithdrawalsQueryInfo;
import com.systemic.gq.pay.service.ISpringWithdrawalsService;
import com.systemic.gq.pay.service.dao.IWithdrawalsDao;

public class SpringWithdrawalsService implements ISpringWithdrawalsService {

	
	IWithdrawalsDao withdrawalsDao;
	
	
	@Override
	public Page selectWithdrawalsLog(WithdrawalsQueryInfo info) {
		// TODO Auto-generated method stub
		return withdrawalsDao.selectWithdrawalsLog(info);
	}


	/**
	 * @param withdrawalsDao the withdrawalsDao to set
	 */
	public void setWithdrawalsDao(IWithdrawalsDao withdrawalsDao) {
		this.withdrawalsDao = withdrawalsDao;
	}


	@Override
	public void saveWithdrawals(WithdrawalsEdit withdrawals) {
		
		Withdrawals wd=null;
		if (StringUtils.isNotBlank(withdrawals.getWId())) {
			wd = selectWithdrawalsById(withdrawals.getWId());
		} else {
			wd = new Withdrawals();
			BeanUtils.copyProperties(withdrawals, wd, new String[] { "WId" });
			wd.setApplyTime(new Date());
		}
		wd.setApplyLocalHost(ConsoleHelper.getUserIp());
		this.withdrawalsDao.save(wd);
		
		
	}



	@Override
	public List<Withdrawals> selectWithdrawalsList(WithdrawalsQueryInfo info) {
		return withdrawalsDao.selectWithdrawalsList(info);
	}


	@Override
	public Withdrawals selectWithdrawalsById(String wId) {
		return (Withdrawals) this.withdrawalsDao.load(Withdrawals.class, wId);
	}


	@Override
	public void updateWithdrawals(Withdrawals withdrawals) {
		withdrawalsDao.update(withdrawals);
	}


}
