package com.plugins.dirtree.service;

import java.util.List;

import com.plugins.dirtree.command.DirDataEditInfo;
import com.plugins.dirtree.command.DirTypeEditInfo;
import com.plugins.dirtree.entity.DirData;
import com.plugins.dirtree.entity.DirType;

public interface IDirService {
	/**
	 * 保存 目录类型
	 * @param info 目录类型信息
	 */
	boolean saveDirType(DirTypeEditInfo info);
	/**
	 * 删除 目录类型，设置类型为无效
	 * @param id 目录类型编号
	 */
	void deleteDirType(String[] ids);
	/**
	 * 设置目录类型为有效
	 * @param id
	 */
	void doDirTypeUse(String id);
	/**
	 * 获取目录类型列表
	 * @return 目录类型列表
	 */
	List selectAllDirTypes();
	/**
	 * 获取有效目录类型列表
	 * @return
	 */
	List selectValidDirTypes();
	/**
	 * 获取有效的目录类型序号
	 * @param parentId
	 * @return
	 */
	Integer selectUsableDirTypeOrderNumber();
	/**
	 * 排序目录类型列
	 * @param ids
	 */
	void doDirTypeSort(String[] ids);
	/**
	 * 根据目录类型编号获取类型
	 * @param dirTypeId
	 * @return
	 */
	DirType selectDirType(String dirTypeId);
	/**
	 * 根据目录类型标识获取类型
	 * @param dirTypeCode
	 * @return
	 */
	DirType selectDirTypeByCode(String dirTypeCode);
	/**
	 * 根据目录树类型获取目录树，该接口从缓存中获取有效的目录树
	 * @param typecode
	 * @param parentid
	 * @return
	 */
	List selectDirDataTreeByType(String typecode, String parentid);
	/**
	 * 根据目录树类型获取目录树
	 * @param typecode
	 * @param parentid
	 * @return
	 */
	List selectDirDataList(String typeid, String parentid);
	/**
	 * 根据上级目录获取目录树的可用序号
	 * @param parentId
	 * @return
	 */
	Integer selectUsableDirDataOrderNumber(String typeId,String parentId);
	/**
	 * 查询目录信息
	 * @param id 目录编号
	 * @return
	 */
	DirData selectDirData(String id);
	/**
	 * 保存目录
	 * @param info
	 * @return
	 */
	boolean saveDirData(DirDataEditInfo info);
	/**
	 * 更新字典
	 * @param dirtypeid
	 * @param dirTypecode
	 */
	void doRefreshDirDataDic(String dirtypeid,String dirTypecode);
	/**
	 * 逻辑删除目录内容
	 * @param dirDataid 目录ID
	 */
	void doDirDataDelete(String dirDataid);
	/**
	 * 启用目录内容
	 * @param dirDataid 目录ID
	 */
	void doDirDataUse(String dirDataid);
	/**
	 * 排序目录，按ID数组的顺序排序
	 * @param ids ID数据
	 */
	void doDirDataSort(String[] ids);
}
