package com.console.backup.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springline.orm.Page;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.console.backup.command.DBBackupInfo;
import com.console.backup.service.IDBBackupService;

public class BackupController extends PaginationQueryController {

	private IDBBackupService dbbackupService;
	private String recoveryBackupView;

	@Override
	protected Page selectQueryResult(HttpServletRequest arg0, HttpServletResponse arg1, PaginationInfo arg2, Map arg3)
			throws Exception {
		DBBackupInfo dbbackupinfo = (DBBackupInfo) arg2;
		return dbbackupService.selectBackupList(dbbackupinfo);
	}

	
	
	public IDBBackupService getDbbackupService() {
		return dbbackupService;
	}

	public void setDbbackupService(IDBBackupService dbbackupService) {
		this.dbbackupService = dbbackupService;
	}

	/**
	 * @return the recoveryBackupView
	 */
	public String getRecoveryBackupView() {
		return recoveryBackupView;
	}

	/**
	 * @param recoveryBackupView
	 *            the recoveryBackupView to set
	 */
	public void setRecoveryBackupView(String recoveryBackupView) {
		this.recoveryBackupView = recoveryBackupView;
	}

}
