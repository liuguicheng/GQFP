

package com.plugins.pending.service;

import org.springline.orm.Page;

import com.plugins.pending.command.FinishedItemQueryInfo;
import com.plugins.pending.command.PendingItemQureyInfo;

 /**
 * @description
 */
public interface IPendingItemService  {

    /**
     * 获取待办事项里各分类的统计数
     * @param modulename 模块名称
     * @param id 待办接收人编号
     * @return 统计数
     */
    Integer selectPendingItemCount(String modulename, String id);
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
}
