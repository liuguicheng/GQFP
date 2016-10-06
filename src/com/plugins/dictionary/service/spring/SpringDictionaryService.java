package com.plugins.dictionary.service.spring;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springline.beans.cache.CacheHelper;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.plugins.dictionary.DictionaryHelper;
import com.plugins.dictionary.command.DicDataEditInfo;
import com.plugins.dictionary.command.DicTypeEditInfo;
import com.plugins.dictionary.command.DicTypeQueryInfo;
import com.plugins.dictionary.entity.DicData;
import com.plugins.dictionary.entity.DicType;
import com.plugins.dictionary.service.IDictionaryService;
import com.plugins.dictionary.service.dao.IDictionaryDao;

public class SpringDictionaryService implements IDictionaryService {


	/**注入dictionaryDao*/
	private IDictionaryDao dictionaryDao;

	/**
	 * @param dictionaryDao the dictionaryDao to set
	 */
	public void setDictionaryDao(IDictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}


	/**
	 * @see com.plugins.dionary.service.IDictionaryService#getDicTQueryPage(com.plugins.dictionary.command.DicTypeQueryInfo)
	 */
	public Page getDicTypeQueryPage(DicTypeQueryInfo info) {
		return this.dictionaryDao.getDicTypeQueryPage(info);
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#selectAllDicType()
	 */
	public List selectAllDicType() {
		return this.dictionaryDao.loadAll(DicType.class, "typeSort");
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getDicTypeById(java.lang.String)
	 */
	public DicType getDicTypeById(String id) {
		return (DicType)this.dictionaryDao.load(DicType.class, id);
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#saveDicType(com.plugins.dictionary.command.DicTypeEditInfo)
	 */
	public void saveDicType(DicTypeEditInfo info) {
		DicType dicType = new DicType();
		String currentCode = info.getTypeCode();
		if (info.getTypeId() != null && info.getTypeId().trim().length() != 0) {
			dicType = this.getDicTypeById(info.getTypeId());
			currentCode = dicType.getTypeCode();
		}
		//判断类型编码是否存在重复，如果重复抛异常
		if (this.dictionaryDao.checkExitTypeCode(dicType.getTypeCode(), info.getTypeCode())) {
			throw new RuntimeException("类型编码『" + info.getTypeCode() + "』已被使用，请重新编辑类型编码！");
		}
		try {
			BeanUtils.copyProperties(info, dicType, new String[] {"typeId"});//拷贝数据
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		this.dictionaryDao.save(dicType);
		if (!info.getTypeCode().equals(currentCode)) {
			//当字典编码发生改变时，级联更新字典内容表的数据，以便进行同步
			this.dictionaryDao.updateDicData(info.getTypeCode(), currentCode);
			//当字典编码改变时，注入新的字典，并注销旧的字典
			this.registerData(dicType.getTypeCode());
		}
		//当新增时注入字典
		if (info.getTypeId() == null || info.getTypeId().trim().length() == 0) {
			this.registerData(info.getTypeCode());
		}

	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getMaxTypeSort()
	 */
	public Integer getMaxTypeSort() {
		return this.dictionaryDao.getMaxTypeSort();
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getMaxDataCode(java.lang.String, java.lang.String)
	 */
	public String getMaxDataCode(String typeCode, String isLonger, Integer dataLong) {
		int maxDataCode = this.dictionaryDao.getMaxDataCode(typeCode);
		String dataCode = "" + maxDataCode;
		if (isLonger.equals(ConsoleHelper.YES)) {//定长
			dataCode = String.format("%0" + dataLong + "d", maxDataCode);
		}
		return dataCode;
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#selectAllDicDataByCode(java.lang.String)
	 */
	public List selectAllDicDataByCode(String typeCode) {
		return this.dictionaryDao.loadAll(DicData.class, "typeCode", typeCode, "dataSort");
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getDicTypeByCode(java.lang.String)
	 */
	public DicType getDicTypeByCode(String typeCode) {
		DicType dicType = null;
		List list = this.dictionaryDao.loadAll(DicType.class, "typeCode", typeCode, "typeSort");
		if (list != null && list.size() > 0) {
			dicType = new DicType();
			dicType = (DicType)list.get(0);
		}
		return dicType;
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getDicDataById(java.lang.String)
	 */
	public DicData getDicDataById(String dataId) {
		return (DicData)this.dictionaryDao.load(DicData.class, dataId);
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#getMaxDataSort(java.lang.String)
	 */
	public Integer getMaxDataSort(String typeCode) {
		return this.dictionaryDao.getMaxDataSort(typeCode);
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#saveDicData(com.plugins.dictionary.command.DicDataEditInfo)
	 */
	public void saveDicData(DicDataEditInfo info) {
		DicData dicData = new DicData();
		if (info.getDataId() != null && info.getDataId().trim().length() != 0) {
			BeanUtils.copyProperties(info, dicData);//拷贝数据
		} else {
			if (info.getTypeWay().equals(DicData.AUTO_GENERATE)) {//如果是系统生成方式
				dicData.setDataCode(this.getMaxDataCode(info.getTypeCode(), info.getIsLonger(), info.getDataLength()));
			} else {//手工录入方式
				//根据类型编码判断内容编码是否存在
				if (!this.dictionaryDao.checkExitDataCode(info.getDataCode(), info.getTypeCode())) {
					throw new RuntimeException("字典编码『" + info.getDataCode() + "』已被使用，请重新编辑字典编码！");
				}
			}
			BeanUtils.copyProperties(info, dicData, new String[] {"dataId"});//拷贝数据
		}
		this.dictionaryDao.save(dicData);
		//更新之后刷新字典
		this.refreshData(dicData.getTypeCode());
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#deleteDicData(java.lang.String[])
	 */
	public void deleteDicData(String[] ids) {
		if (ids == null || ids.length == 0) return;
		DicData dicData = null;
		for (int nI = 0; nI < ids.length; nI++) {
			dicData = new DicData();
			dicData = this.getDicDataById(ids[nI]);
			if (dicData != null) {
				dicData.setIsValid(ConsoleHelper.NO);
				this.dictionaryDao.update(dicData);
				//更新后刷新字典
				this.refreshData(dicData.getTypeCode());
			}
		}
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#updateDicData(java.lang.String[])
	 */
	public void updateDicData(String[] ids) {
		if (ids == null || ids.length == 0) return;
		DicData dicData = null;
		for (int nI = 0; nI < ids.length; nI++) {
			dicData = new DicData();
			dicData = this.getDicDataById(ids[nI]);
			if (dicData != null) {
				dicData.setIsValid(ConsoleHelper.YES);//正常使用
				this.dictionaryDao.update(dicData);
				//更新后刷新字典
				this.refreshData(dicData.getTypeCode());
			}
		}
	}

	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#deleteDicType(java.lang.String[])
	 */
	public void deleteDicType(String[] ids) {
		if (ids == null || ids.length == 0) return;
		DicType dicType = null;
		for (int nI = 0; nI < ids.length; nI++) {
			dicType = new DicType();
			dicType = this.getDicTypeById(ids[nI]);
			if (dicType != null) {
				//删除相关的字典内容数据
				this.dictionaryDao.deleteDicData(dicType.getTypeCode());
				this.dictionaryDao.delete(dicType);
				//删除后注销原有的字典
			}
		}
	}

	/**
	 * 刷新字典
	 * @param typeCode
	 */
	private void refreshData(String typeCode) {
		CacheHelper.getInstance().dispatchRefreshEvent(typeCode);
	}

	/**
	 * 注入字典
	 * @param typeCode
	 */
	private void registerData(String typeCode) {
		DictionaryHelper.getInstance().registerConnector(typeCode);
	}


	@Override
	public String getDicDataByType(String typeCode, String code) {
		 List<DicData> dics = this.selectAllDicDataByCode(typeCode);
	        for(DicData dic: dics){
	            if(dic.getDataCode().equals(code)){
	                return  dic.getDataName();
	            }
	        }
	        return "";
	}

}
