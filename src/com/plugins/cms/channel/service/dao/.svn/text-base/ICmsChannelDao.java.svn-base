/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.channel.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.cms.channel.command.CmsChannelQueryInfo;

/**
 * @description
 */
public interface ICmsChannelDao extends ICommonDao {

    /**
     * 查询栏目
     * @param info
     * @return
     */
    Page selectCmsChannelList(CmsChannelQueryInfo info);

    /**
     * 获取可用顺序号
     * @param parentId
     * @return
     */
    Integer getSortOrder(String parentId);


}
