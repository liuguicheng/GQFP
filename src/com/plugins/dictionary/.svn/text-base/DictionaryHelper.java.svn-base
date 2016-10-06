package com.plugins.dictionary;

import com.plugins.dictionary.service.IDictionaryService;

public abstract class DictionaryHelper {

	private static DictionaryHelper instance = null;

	/**
	 * @return the instance
	 */
	public static DictionaryHelper getInstance() {
		if (instance == null) {
			throw new RuntimeException("未创建DictionaryHelper的实例对象！");
		}
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	protected void setInstance(DictionaryHelper instance) {
		DictionaryHelper.instance = instance;
	}

	/**
	 * 注册
	 * @param objName
	 */
	public abstract void registerConnector(String objName);
	/**
	 * @return IAppItemService
	 */
	public abstract IDictionaryService getDictionaryService();

}
