package com.plugins.dirtree.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;

import com.plugins.dirtree.entity.DirType;

public interface IDirTypeDao extends ICommonDao {

	/**
	 * 检查 目录类型编码是否有重复
	 * @param oldTypeCode 原类型编码
	 * @param newTypeCode 新类型编码
	 * @return true 已重复，false 未重复
	 */
	boolean checkEditDirTypeCode(String oldTypeCode, String newTypeCode);
	/**
	 * 获取有效的目录类型列表
	 * @return
	 */
	List selectValidDirTypes();
	/**
	 * 根据目录类型标识获取类型
	 * @param dirTypeCode
	 * @return
	 */
	DirType selectDirTypeByCode(String dirTypeCode);
	/**
	 * 获取有效的目录类型序号
	 * @return
	 */
	Integer selectUsableOrderNumber();

}
