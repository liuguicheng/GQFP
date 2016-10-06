package com.console.backup.service.spring;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.orm.Page;

import com.console.backup.command.DBBackupEdit;
import com.console.backup.command.DBBackupInfo;
import com.console.backup.service.IDBBackupService;
import com.console.backup.service.dao.IDBBackupDAO;
import com.console.entity.DBBackup;
import com.systemic.gq.entity.Rule;

public class SpringDBBackupService implements IDBBackupService {

	IDBBackupDAO dbbackupDao;
	
	
	/**
	 * @param dbbackupDao the dbbackupDao to set
	 */
	public void setDbbackupDao(IDBBackupDAO dbbackupDao) {
		this.dbbackupDao = dbbackupDao;
	}


	@Override
	public Page selectBackupList(DBBackupInfo dbbackinfo) {
		// TODO Auto-generated method stub
		return dbbackupDao.selectBackupList(dbbackinfo);
	}


	@Override
	public void save(DBBackupEdit bedit) {
		
		DBBackup dbbackup=new DBBackup();
		if(StringUtils.isNotBlank(dbbackup.getDb_id())){
			BeanUtils.copyProperties(bedit,dbbackup);
		}else{
			BeanUtils.copyProperties(bedit,dbbackup,new String[]{"db_id"});
		}
		dbbackupDao.save(dbbackup);
	}


	@Override
	public DBBackup loadById(String id) {
		return (DBBackup) dbbackupDao.load(DBBackup.class, id);
	}


	@Override
	public void delete(DBBackup bdbackupinf) {
		dbbackupDao.delete(bdbackupinf);
	}

}
