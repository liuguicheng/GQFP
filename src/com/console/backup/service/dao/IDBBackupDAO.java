package com.console.backup.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.console.backup.command.DBBackupInfo;

public interface IDBBackupDAO extends ICommonDao {
	
	Page selectBackupList(DBBackupInfo dbbackinfo);

}
