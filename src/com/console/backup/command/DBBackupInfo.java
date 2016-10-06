package com.console.backup.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

public class DBBackupInfo extends PaginationInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5150157589469720254L;
	/**
	 * 数据库备份记录
	 */
	private String db_id;
	private String db_bskcupName;// 备份文件
	private Date db_time;// 备份日期
	private String db_bskcpuUrl;//文件路径
	/**
	 * @return the db_id
	 */
	public String getDb_id() {
		return db_id;
	}

	/**
	 * @param db_id
	 *            the db_id to set
	 */
	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}

	/**
	 * @return the db_bskcupName
	 */
	public String getDb_bskcupName() {
		return db_bskcupName;
	}

	/**
	 * @param db_bskcupName
	 *            the db_bskcupName to set
	 */
	public void setDb_bskcupName(String db_bskcupName) {
		this.db_bskcupName = db_bskcupName;
	}

	/**
	 * @return the db_time
	 */
	public Date getDb_time() {
		return db_time;
	}

	/**
	 * @param db_time
	 *            the db_time to set
	 */
	public void setDb_time(Date db_time) {
		this.db_time = db_time;
	}

	/**
	 * @return the db_bskcpuUrl
	 */
	public String getDb_bskcpuUrl() {
		return db_bskcpuUrl;
	}

	/**
	 * @param db_bskcpuUrl the db_bskcpuUrl to set
	 */
	public void setDb_bskcpuUrl(String db_bskcpuUrl) {
		this.db_bskcpuUrl = db_bskcpuUrl;
	}

}
