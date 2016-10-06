/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.content.service.dao.hibernate;

import java.util.Iterator;
import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;
import org.hibernate.internal.util.collections.EmptyIterator;
import com.plugins.cms.content.command.CmsContentQueryInfo;
import com.plugins.cms.content.service.dao.ICmsContentDao;
import com.plugins.cms.entity.CmsContent;

/**
 * @description
 */
public class HibernateCmsContentDao extends HibernateCommonDao implements ICmsContentDao {

    private IQueryStringUtil cmsContentQueryUtil;
    /**
     * @see com.systemic.cms.Content.service.dao.ICmsContentDao#selectCmsContentList(com.systemic.cms.Content.command.CmsContentQueryInfo)
     */
    @Override
    public Page selectCmsContentList(CmsContentQueryInfo info) {
        IQueryObject qo = cmsContentQueryUtil.getQueryObject(info);
    	return super.find(qo.getQueryString(), qo.getParam(), info.getPageNumber());
    }


    /**
     * @see com.systemic.cms.Content.service.dao.ICmsContentDao#getSortOrder(java.lang.String)
     */
    @Override
    public Integer getSortOrder(String parentId) {
        StringBuffer hql = new StringBuffer("select max(sortOrder) from ").append(CmsContent.class.getName());
        Object[] params = new Object[20];
        int idx = 0;
        if (parentId == null || parentId.trim().length() == 0) {
            hql.append(" where parentId is null or parentId = ''");
        } else {
            hql.append(" where parentId =?").append(" and ContentId != ?");
            params[idx++] =  parentId;
            params[idx++] =  parentId;
        }
        Object[] conditions = new Object[idx];
        System.arraycopy(params, 0, conditions, 0, idx);
        Iterator it = super.iterate(hql.toString(), conditions);
        int number = 1;
        if (it.hasNext()) {
            try {
                number = ((Integer) it.next()).intValue();
                number++;
            } catch (Exception ex) {

            }
        }
        if (!(it instanceof EmptyIterator)) {
            super.closeIterator(it);
        }
        return new Integer(number);
    }


    /**
     * @param cmsContentQueryUtil the cmsContentQueryUtil to set
     */
    public void setCmsContentQueryUtil(IQueryStringUtil cmsContentQueryUtil) {
        this.cmsContentQueryUtil = cmsContentQueryUtil;
    }

}
