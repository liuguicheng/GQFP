package com.plugins.dirtree.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;


public interface IDirDataDao extends ICommonDao {
	/**
	 * 根据目录树类型获、上级目录取目录列表
	 * @param typeid
	 * @param parentid
	 * @param isvalid 是否有效
	 * @return
	 */
	List selectDirDataBy(String typeid,String parentid,String isvalid);
	/**
	 * 根据目录树类型获、上级目录取目录列表
	 * @param typeid
	 * @param isvalid
	 * @return
	 */
	List selectDirData(String typeid,String isvalid);
	/**
	 * 根据上级目录获取目录树的可用序号
	 * @param parentId
	 * @return
	 */
	Integer selectUsableOrderNumber(String typeId,String parentId);
}
