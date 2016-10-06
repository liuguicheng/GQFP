package com.systemic.gq.pay.service.spring;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.orm.Page;

import com.systemic.gq.entity.TransferAccounts;
import com.systemic.gq.pay.command.TransferAccountsEdit;
import com.systemic.gq.pay.command.TransferAccountsQueryInfo;
import com.systemic.gq.pay.service.ITransferAccountService;
import com.systemic.gq.pay.service.dao.ITransferAccountsDao;

public class SpringTransferAccountService implements ITransferAccountService {

	private ITransferAccountsDao transferAccountDao;
	
	
	/**
	 * @param transferAccountDao the transferAccountDao to set
	 */
	public void setTransferAccountDao(ITransferAccountsDao transferAccountDao) {
		this.transferAccountDao = transferAccountDao;
	}


	@Override
	public Page selectTransferAccountsPage(TransferAccountsQueryInfo raqinfo) {
		// TODO Auto-generated method stub
		return transferAccountDao.selectTransferAccountsPage(raqinfo);
	}


	@Override
	public void save(TransferAccountsEdit edit) {
		TransferAccounts trf = null;
		if (StringUtils.isNotBlank(edit.getTaId())) {
			trf = selectTransferAccounts(edit.getTaId());
		} else {
			trf = new TransferAccounts();
			BeanUtils.copyProperties(edit, trf, new String[] { "taId" });
		}
		transferAccountDao.save(trf);
		
	}


	private TransferAccounts selectTransferAccounts(String taId) {
		// TODO Auto-generated method stub
		return (TransferAccounts) transferAccountDao.load(TransferAccounts.class, taId);
	}


	@Override
	public List<TransferAccounts> selectTransferAccountsList(TransferAccountsQueryInfo info) {
		// TODO Auto-generated method stub
		return transferAccountDao.selectTransferAccountsList(info);
	}

}
