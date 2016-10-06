/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.content.service;

import org.springline.orm.Page;

import com.plugins.cms.content.command.CmsContentEditInfo;
import com.plugins.cms.content.command.CmsContentQueryInfo;
import com.plugins.cms.entity.CmsContent;

/**
 * @description
 */
public interface ICmsContentService {

    /**
     * 查询栏目
     * @param info
     * @return
     */
    Page selectContentList(CmsContentQueryInfo info);

    /**
     * 加载栏目对象
     * @param contentId
     * @return
     */
    CmsContent loadContent(String contentId);

    /**
     * 保存
     * @param info
     */
    void saveContentEditInfo(CmsContentEditInfo info);

    /**
     * 获取可用顺序号
     * @param parentId
     * @return
     */
    Integer getSortOrder(String parentId);

    /**
     * 删除内容
     * @param ids
     */
    void deleteContents(String[] ids);
}
