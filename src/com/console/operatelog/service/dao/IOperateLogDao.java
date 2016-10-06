package com.console.operatelog.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.console.operatelog.command.OperateLogQueryInfo;

public interface IOperateLogDao extends ICommonDao{

	/**
     *  日志信息分页
     * @param info
     * @return page 分页对象
     */
	Page selectOperateLogList(OperateLogQueryInfo info);
}
