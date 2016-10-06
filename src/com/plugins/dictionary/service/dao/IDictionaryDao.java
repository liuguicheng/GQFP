package com.plugins.dictionary.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.dictionary.command.DicTypeQueryInfo;


/**
 *
 * @author wqy
 *@描述：DictionaryDao字典Dao接口
 */
public interface IDictionaryDao extends ICommonDao{

	/**
     *获取字典类型的分页数据
     * @param info
     * @return
     */
    Page getDicTypeQueryPage(DicTypeQueryInfo info);

    /**
     * 获取字典类型的当前最大排序
     * @return
     */
    Integer getMaxTypeSort();

    /**
     * 判断字典标识是否已经存在
     * @param oldTypeCode
     * @param newTypeCode
     * @return
     */
    public boolean checkExitTypeCode(String oldTypeCode, String newTypeCode);

    /**
     * 获取字典类型的当前最大排序
     * @return
     */
    Integer getMaxDataSort(String typeCode);

    /**
     * 根据字典类型ID判断是否存在字典标识
     * @param oldDataCode
     * @param dataCode
     * @param typeId
     * @return
     */
    public boolean checkExitDataCode(String dataCode,String typeCode);

    /**
     * 根据类型编码更新字典表
     * @param newTypeCode
     * @param oldTypeCode
     */
    void updateDicData(String newTypeCode, String oldTypeCode);

    /**
	 * 自动增长类型编码
	 * @param isLonger
	 * @param typeCode
	 * @param dataLong
	 * @return
	 */
	int getMaxDataCode(String typeCode);

	/**
	 * 根据类型编码物理删除字典数据
	 * @param typeCode
	 */
	void deleteDicData(String typeCode);
}
