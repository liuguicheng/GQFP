package com.console.operate.service;

import java.util.List;

import com.console.entity.Operate;
import com.console.operate.command.OperateEditInfo;
import com.console.operate.command.OperateQueryInfo;

public interface IOperateService {

	/**
	 * 新增页面权限信息.
	 * @param operate实例
	 *
	 */
	void saveOperate(OperateEditInfo info);

	/**
     * 删除页面权限信息.
     * @param id    需要删除的权限编号
     */
    void deleteOperate(String[] id);

    /**
     * 获取指定 id 对应的权限信息.
     * @param id    权限编号
     * @return  Operate 实例
     */
    Operate selectOperate(String id);

    /**
     * 获取所有的页面权限信息的列表.
     * @return  包含所有权限信息（Operate实例）的List实例
     */
    List selectAllOperate(OperateQueryInfo info);
}
