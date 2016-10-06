
package com.plugins.pending.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.plugins.pending.command.FinishedItemQueryInfo;
import com.plugins.pending.command.PendingItemQureyInfo;
import com.plugins.pending.entity.PendingItem;
import com.plugins.pending.entity.PendingItemRecipients;
import com.plugins.pending.service.dao.IPendingItemDao;


public class HibernatePendingItemDao extends HibernateCommonDao implements IPendingItemDao {
    /**  */
    private IQueryStringUtil pengdingItemQueryUtil, finishedItemQueryUtil;
    /** （non Javadoc）
     * 获取待办事项里各分类的统计数
     * @see com.plugins.pending.service.dao.IPendingItemDao#selectPendingItemCount(java.lang.String)
     */
    public List selectPendingItemCount(String modulename, String staffId) {
        Object[] values = {modulename, staffId};
        String hql = "select  count(pi.forDoc.forDoId) from " + PendingItemRecipients.class.getName()
            + " as pi where pi.forDoc.moduleName = ? and pi.recipientId = ?";
        return super.doQuery(hql, values);
    }
    /**
     * @see com.plugins.pending.service.dao.IPendingItemDao#selectPendingItem(com.plugins.pending.command.PendingItemQureyInfo)
     */
    public Page selectPendingItem(PendingItemQureyInfo info) {
        StringBuffer sql = new StringBuffer(255);
        Object[] conditions = new Object[10];
        Object[] parameters = new Object[0];
        int idx = 0;
        if (info.getModuleNames() != null && info.getModuleName().trim().length() > 0) {
            //sql.append(" pdr.forDoc.moduleName in(").append(info.getModuleNames()).append(") and");
            sql.append(" pdr.forDoc.moduleName in(?)and");
            conditions[idx++] = info.getModuleNames();
        }
//        if (info.getNonModuleNames() != null && info.getNonModuleNames().trim().length() > 0) {
//            //sql.append(" pdr.forDoc.moduleName not in(").append(info.getNonModuleNames()).append(") and");
//            sql.append(" pdr.forDoc.moduleName <>? and");
//            conditions[idx++] = info.getNonModuleNames();
//        }
        if (idx > -1) {
            parameters = new Object[idx];
            System.arraycopy(conditions, 0, parameters, 0, idx);
        }
        IQueryObject qo = this.pengdingItemQueryUtil.getQueryObject(info, sql.toString(), parameters);
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List data = super.doQuery(qo.getQueryString(), qo.getParam());
            return super.putDataToPage(data);
        }
        return super.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
    }


    /**
     * @see com.plugins.pending.service.dao.IPendingItemDao#selectFinishedItems(com.plugins.pending.command.FinishedItemQueryInfo)
     */
    public Page selectFinishedItems(FinishedItemQueryInfo info) {
        IQueryObject qo = this.finishedItemQueryUtil.getQueryObject(info);
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List data = super.doQuery(qo.getQueryString(), qo.getParam());
            return super.putDataToPage(data);
        }
        return super.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
    }

    /**
     * 根据模块名和标识获取待办事项对象
     * @param moduleName 待办事项所属的模块名
     * @param flagId 在同一个模块下标识该待办事项的Id
     * @return 待办事项对象
     *
     */
    public PendingItem getPendingItem(String moduleName, String flagId) {
        String hsql = " from " + PendingItem.class.getName() + " as pi where pi.moduleName = ? and pi.flagId = ? ";

        List result = super.doQuery(hsql, new Object[] {moduleName, flagId});

        return (result != null && result.size() > 0) ? (PendingItem) result.get(0) : null;
    }

    /**
     * 获取某个待办事项的所有接收人对象
     * @param forDoId 待办事项的id
     * @return 接收人对象列表
     *
     */
    public List getPendingItemRecipients(String forDoId) {
        String hsql = " from " + PendingItemRecipients.class.getName() + " as pr where pr.forDoId = ? ";

        return super.doQuery(hsql, new Object[] {forDoId});
    }

    /**
     * @param pengdingItemQueryUtil The pengdingItemQueryUtil to set.
     */
    public void setPengdingItemQueryUtil(IQueryStringUtil pengdingItemQueryUtil) {
        this.pengdingItemQueryUtil = pengdingItemQueryUtil;
    }
    /**
     * @param finishedItemQueryUtil the finishedItemQueryUtil to set
     */
    public void setFinishedItemQueryUtil(IQueryStringUtil finishedItemQueryUtil) {
        this.finishedItemQueryUtil = finishedItemQueryUtil;
    }
}
