/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.channel.service.dao.hibernate;

import java.util.Iterator;

import org.hibernate.internal.util.collections.EmptyIterator;
import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.plugins.cms.channel.command.CmsChannelQueryInfo;
import com.plugins.cms.channel.service.dao.ICmsChannelDao;
import com.plugins.cms.entity.CmsChannel;

/**
 * @description
 */
public class HibernateCmsChannelDao extends HibernateCommonDao implements ICmsChannelDao {

    private IQueryStringUtil cmsChannelQueryUtil;
    /**
     * @see com.plugins.cms.channel.service.dao.ICmsChannelDao#selectCmsChannelList(com.plugins.cms.channel.command.CmsChannelQueryInfo)
     */
    @Override
    public Page selectCmsChannelList(CmsChannelQueryInfo info) {
        String whereStr = "";
        Object[] params = new Object[10];
        int idx = 0;
        if (info.getParentId() != null) {
            if (CmsChannelQueryInfo.ROOT.equals(info.getParentId())) {
                whereStr = " (channel.parentId is null or channel.parentId = '') and";
            } else {
                whereStr = " channel.parentId = ? and";
                params[idx++] = info.getParentId();
            }
        }
        Object[] conditions = new Object[idx];
        System.arraycopy(params, 0, conditions, 0, idx);
        IQueryObject qo = cmsChannelQueryUtil.getQueryObject(info, whereStr, conditions);
        return super.find(qo.getQueryString(), qo.getParam(), info.getPageNumber());
    }


    /**
     * @see com.plugins.cms.channel.service.dao.ICmsChannelDao#getSortOrder(java.lang.String)
     */
    @Override
    public Integer getSortOrder(String parentId) {
        StringBuffer hql = new StringBuffer("select max(sortOrder) from ").append(CmsChannel.class.getName());
        Object[] params = new Object[20];
        int idx = 0;
        if (parentId == null || parentId.trim().length() == 0) {
            hql.append(" where parentId is null or parentId = ''");
        } else {
            hql.append(" where parentId =?").append(" and channelId != ?");
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
     * @param cmsChannelQueryUtil the cmsChannelQueryUtil to set
     */
    public void setCmsChannelQueryUtil(IQueryStringUtil cmsChannelQueryUtil) {
        this.cmsChannelQueryUtil = cmsChannelQueryUtil;
    }

}
