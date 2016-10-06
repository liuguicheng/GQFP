/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.content.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.cms.content.command.CmsContentQueryInfo;

/**
 * @description
 */
public interface ICmsContentDao extends ICommonDao {

    /**
     * 查询栏目
     * @param info
     * @return
     */
    Page selectCmsContentList(CmsContentQueryInfo info);

    /**
     * 获取可用顺序号
     * @param parentId
     * @return
     */
    Integer getSortOrder(String parentId);
}
