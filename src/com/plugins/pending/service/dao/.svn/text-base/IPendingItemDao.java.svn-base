

package com.plugins.pending.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.pending.command.FinishedItemQueryInfo;
import com.plugins.pending.command.PendingItemQureyInfo;
import com.plugins.pending.entity.PendingItem;


public interface IPendingItemDao extends ICommonDao {

    /**
     * 获取待办事项里各分类的统计数
     * @param modulename modulename
     * @param staffId staffId
     * @return 待办事项里各分类的统计数
     */
    List selectPendingItemCount(String modulename, String staffId);

    /**
     * 查询待办事宜
     * @param info PendingItemQureyInfo
     * @return Page
     */
    Page selectPendingItem(PendingItemQureyInfo info);
    /**查询已完结的事宜
     * @param info
     * @return
     */
    Page selectFinishedItems(FinishedItemQueryInfo info);

    /**
     * 根据模块名和标识获取待办事项对象
     * @param moduleName 待办事项所属的模块名
     * @param flagId 在同一个模块下标识该待办事项的Id
     * @return 待办事项对象
     *
     */
    public PendingItem getPendingItem(String moduleName, String flagId);
    /**
     * 获取某个待办事项的所有接收人对象
     * @param forDoId 待办事项的id
     * @return 接收人对象列表
     *
     */
    public List getPendingItemRecipients(String forDoId);
}
