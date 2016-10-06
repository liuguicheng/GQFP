package com.console.operate.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;

import com.console.operate.command.OperateQueryInfo;

public interface IOperateDao extends ICommonDao{

	/**
     * 取所有的权限信息.
     * @return  所有Operate实例组成的List
     */
    List selectAllOperate(OperateQueryInfo info);
}
