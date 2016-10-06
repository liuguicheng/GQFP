package com.console.backup.service;

import org.springline.orm.Page;

import com.console.backup.command.DBBackupEdit;
import com.console.backup.command.DBBackupInfo;
import com.console.entity.DBBackup;

public interface IDBBackupService {
	
	Page selectBackupList(DBBackupInfo dbbackinfo);

	void save(DBBackupEdit bedit);

	DBBackup loadById(String id);

	void delete(DBBackup bdbackupinf);
}
