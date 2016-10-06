package com.systemic.gq.pay.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.systemic.gq.entity.Withdrawals;
import com.systemic.gq.pay.command.WithdrawalsQueryInfo;

public interface IWithdrawalsDao extends ICommonDao {

	Page selectWithdrawalsLog(WithdrawalsQueryInfo info);
	List<Withdrawals> selectWithdrawalsList(WithdrawalsQueryInfo info);
}
