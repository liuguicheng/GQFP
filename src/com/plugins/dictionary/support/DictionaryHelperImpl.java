package com.plugins.dictionary.support;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;
import org.springline.beans.dictionary.support.DictionaryUtils;

import com.plugins.dictionary.DictionaryHelper;
import com.plugins.dictionary.entity.DicType;
import com.plugins.dictionary.service.IDictionaryService;

public class DictionaryHelperImpl extends DictionaryHelper implements InitializingBean, ICacheConnector {

	/**注入dictionaryService*/
	private IDictionaryService dictionaryService;
	@Override
	public IDictionaryService getDictionaryService() {
		if (dictionaryService == null) {
			throw new RuntimeException("DictionaryHelperImpl无法提供IDictionaryService接口");
		}
		return dictionaryService;
	}

	public void afterPropertiesSet() throws Exception {
		super.setInstance(this);
        List dicTypeList = dictionaryService.selectAllDicType();
        DicType dt = null;
        for (Iterator it = dicTypeList.iterator(); it.hasNext();) {
            dt = new DicType();
            dt = (DicType)it.next();
            if (dt != null) {
                registerConnector(dt.getTypeCode());
            }
        }		
	}

	/**
     * @see com.plugins.dictionary.DictionaryHelper#registerConnector(java.lang.String)
     */
    @Override
    public void registerConnector(String objName) {
        CacheHelper.getInstance().registerConnector(objName, this);
    }

    /**
     * @see org.springline.beans.cache.ICacheConnector#loadCacheObject(java.lang.String)
     */
    @Override
    public Object loadCacheObject(String objName) {
        List data = this.dictionaryService.selectAllDicDataByCode(objName);
        return DictionaryUtils.listToDictionaryItemMap(data, "dataCode", "dataName");
    }

    /**
	 * @param dictionaryService the dictionaryService to set
	 */
	public void setDictionaryService(IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

}
