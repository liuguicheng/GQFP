package com.systemic.gq.pay.service;

import java.util.List;

import org.springline.orm.Page;

import com.systemic.gq.entity.Withdrawals;
import com.systemic.gq.pay.command.WithdrawalsEdit;
import com.systemic.gq.pay.command.WithdrawalsQueryInfo;

public interface ISpringWithdrawalsService {

	
	Page selectWithdrawalsLog(WithdrawalsQueryInfo info);

	void saveWithdrawals(WithdrawalsEdit withdrawals);
	
	List<Withdrawals> selectWithdrawalsList(WithdrawalsQueryInfo info);

	Withdrawals selectWithdrawalsById(String wId);

	void updateWithdrawals(Withdrawals withdrawals);

}
