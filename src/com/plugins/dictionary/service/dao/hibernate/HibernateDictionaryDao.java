package com.plugins.dictionary.service.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.internal.util.collections.EmptyIterator;
import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.plugins.dictionary.command.DicTypeQueryInfo;
import com.plugins.dictionary.entity.DicData;
import com.plugins.dictionary.entity.DicType;
import com.plugins.dictionary.service.dao.IDictionaryDao;

/**
 *
 * @author wanqiuyu
 *@描述：实现IdictionaryDao字典接口，继承HibernateCommonDao
 */
public class HibernateDictionaryDao extends HibernateCommonDao implements IDictionaryDao{

    /**要注入的dicDataQueryStringUtil*/
    private IQueryStringUtil dicDataQueryStringUtil;

    /**
     *
     * @param dicDataQueryStringUtil 设置 dicDataQueryStringUtil
     */
    public void setDicDataQueryStringUtil(IQueryStringUtil dicDataQueryStringUtil) {
        this.dicDataQueryStringUtil = dicDataQueryStringUtil;
    }

    /**
	 * @see com.plugins.dictionary.service.dao.IDictionaryDao#getDicTypeQueryPage(com.plugins.dictionary.command.DicTypeQueryInfo)
	 */
	public Page getDicTypeQueryPage(DicTypeQueryInfo info) {
		IQueryObject qo = this.dicDataQueryStringUtil.getQueryObject(info);
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List list = super.doQuery(qo.getQueryString(), qo.getParam());
            return super.putDataToPage(list);
        }
        return super.find(qo.getQueryString(),qo.getParam(), info.getPageNumber().intValue(), info.getPageSize());
	}

    /**
    *
    * @see com.plugins.dictionary.service.dao.IDictionaryDao#getMaxTypeSort()
    */
   @SuppressWarnings("unchecked")
   public Integer getMaxTypeSort() {
       String hql = "select max(typeSort) from " + DicType.class.getName();
       Integer nI = 1;
       Iterator it = super.iterate(hql);
       if (it.hasNext()) {
           Object object = it.next();
           if (object != null) {
               nI = Integer.valueOf(object.toString()) + 1;
           }
       }
       if (!(it instanceof EmptyIterator)) {
           super.closeIterator(it);
       }
       return nI;
   }

    /**
     *
     * @see com.plugins.dictionary.service.dao.IDictionaryDao#checkExitDictypeName(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public boolean checkExitTypeCode(String oldTypeCode, String newTypeCode) {
        boolean flag = false;
        //当进行修改时，先用<>过滤掉旧的typeCode数据，再根据传入的新的typeCode值进行匹配
        String hql = "from " + DicType.class.getName() 
                + " as dt where dt.typeCode<>? and dt.typeCode=?";
        List list = doQuery(hql,new Object[]{oldTypeCode,newTypeCode});
        if (list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     *
     * @see com.plugins.dictionary.service.dao.IDictionaryDao#getDicDataMaxSort()
     */
    @SuppressWarnings("unchecked")
    public Integer getMaxDataSort(String typeCode) {
        String hql = "select max(dataSort) from " + DicData.class.getName()
                + " as dicD where dicD.typeCode=?";
        Integer  dataSort = 1;
        Iterator it = iterate(hql,new Object[] {typeCode});
        if (it.hasNext()) {
            Object object = it.next();
            if (object != null) {
                dataSort = Integer.valueOf(object.toString()) + 1;
            }
        }
        if (!(it instanceof EmptyIterator)) {
            super.closeIterator(it);
        }
        return dataSort;
    }

	/**
     * @see com.plugins.dictionary.service.dao.IDictionaryDao#checkExitDataCode(java.lang.String, java.lang.String)
     */
    public boolean checkExitDataCode(String dataCode, String typeCode) {
        boolean b = true;
        String hql = " from " + DicData.class.getName() 
                + " as dicD where dicD.dataCode=?" 
                + " and dicD.typeCode=?";
        List list = this.doQuery(hql,new Object[] {dataCode,typeCode});
        if (list.size() != 0) {
            b = false;
        }
        return b;
    }

	/**
	 * @see com.plugins.dictionary.service.dao.IDictionaryDao#updateDicData(java.lang.String, java.lang.String)
	 */
    public void updateDicData(String newTypeCode, String oldTypeCode) {
        String hql = " update " + DicData.class.getName() + " set typeCode=? where typeCode=?";
        this.bulkUpdate(hql, new Object[] {newTypeCode,oldTypeCode});

    }

	/**
	 * @see com.plugins.dictionary.service.dao.IDictionaryDao#getMaxDataCode(java.lang.String, java.lang.String)
	 */
    public int getMaxDataCode(String typeCode) {
        String hql = "select count(dataCode) from " + DicData.class.getName() + " where typeCode=?";
        int dataCode = 1;
        Iterator it = this.iterate(hql,new Object[] {typeCode});
        if (it.hasNext()) {
            Object object = it.next();
            if (object != null) {
                dataCode = Integer.parseInt(object.toString()) + 1;

            }
        }
        if (!(it instanceof EmptyIterator)) {
            super.closeIterator(it);
        }
        return dataCode;
    }

	/**
	 * @see com.plugins.dictionary.service.dao.IDictionaryDao#deleteDicData(java.lang.String)
	 */
    public void deleteDicData(String typeCode) {
        String hql = "delete from " + DicData.class.getName() + " where typeCode=?";
        this.bulkUpdate(hql,new Object[] {typeCode});

    }



}
