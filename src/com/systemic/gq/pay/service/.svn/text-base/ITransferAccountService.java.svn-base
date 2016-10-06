package com.systemic.gq.pay.service;

import java.util.List;

import org.springline.orm.Page;

import com.systemic.gq.entity.TransferAccounts;
import com.systemic.gq.pay.command.TransferAccountsEdit;
import com.systemic.gq.pay.command.TransferAccountsQueryInfo;

public interface ITransferAccountService {

	public Page selectTransferAccountsPage(TransferAccountsQueryInfo raqinfo);

	public void save(TransferAccountsEdit edit);

	public List<TransferAccounts> selectTransferAccountsList(TransferAccountsQueryInfo info);
}
