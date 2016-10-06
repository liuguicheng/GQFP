package com.plugins.dictionary.service;

import java.util.List;

import org.springline.orm.Page;

import com.plugins.dictionary.command.DicDataEditInfo;
import com.plugins.dictionary.command.DicTypeEditInfo;
import com.plugins.dictionary.command.DicTypeQueryInfo;
import com.plugins.dictionary.entity.DicData;
import com.plugins.dictionary.entity.DicType;


public interface IDictionaryService {

	/**
	 * 获取字典类型的分页数据
	 * @param info
	 * @return
	 */
	Page getDicTypeQueryPage(DicTypeQueryInfo info);

	/**
     * 查找所有的字典类型
     * @return
     */
    List selectAllDicType();

	/**
	 * 根据ID查找字典类型的数据
	 * @param id
	 * @return
	 */
	DicType getDicTypeById(String id);

	/**
	 * 获取字典类型的最大排序
	 * @return
	 */
	Integer getMaxTypeSort();

	/**
	 * 保存字典类型的数据
	 * @param info
	 */
	void saveDicType(DicTypeEditInfo info);

	/**
	 * 自动增长类型编码，并根据是否定长自动补0
	 * @param isLonger
	 * @param typeCode
	 * @return
	 */
	String getMaxDataCode(String typeCode, String isLonger, Integer dataLong);

	/**
	 * 根据类型编码加载所有的字典数据
	 * @param typeCode
	 * @return
	 */
	List selectAllDicDataByCode(String typeCode);

	/**
	 * 根据编码获取一条类型记录
	 * @param typeCode
	 * @return
	 */
	DicType getDicTypeByCode(String typeCode);

	/**
	 * 根据字典ID获取一条数据
	 * @param dataId
	 * @return
	 */
	DicData getDicDataById(String dataId);

	/**
	 * 获取字典的最大排序
	 * @param typeCode
	 * @return
	 */
	Integer getMaxDataSort(String typeCode);

	/**
	 * 保存字典数据
	 * @param info
	 */
	void saveDicData(DicDataEditInfo info);

	/**
	 * 逻辑批量删除字典内容
	 * @param ids
	 */
	void deleteDicData(String[] ids);

	/**
	 * 启用字典内容信息
	 * @param ids
	 */
	void updateDicData(String[] ids);

	/**
	 * 删除类型数据
	 * @param ids
	 */
	void deleteDicType(String[] ids);
	/**
     * 根据类型编码获取一条数据
     * @param typeCode,code
     * @return DicData
     */
	String getDicDataByType(String typeCode,String code);
}
