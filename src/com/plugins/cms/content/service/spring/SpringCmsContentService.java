/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.content.service.spring;

import org.springframework.beans.BeanUtils;
import org.springline.orm.Page;

import com.plugins.cms.content.command.CmsContentEditInfo;
import com.plugins.cms.content.command.CmsContentQueryInfo;
import com.plugins.cms.content.service.ICmsContentService;
import com.plugins.cms.content.service.dao.ICmsContentDao;
import com.plugins.cms.entity.CmsContent;

/**
 * @description
 */
public class SpringCmsContentService implements ICmsContentService {

    private ICmsContentDao contentDao;

    /**
     * @see com.plugins.cms.content.service.ICmsContentService#selectContentList(com.plugins.cms.content.command.CmsContentQueryInfo)
     */
    @Override
    public Page selectContentList(CmsContentQueryInfo info) {
        return this.contentDao.selectCmsContentList(info);
    }

    /**
     * @see com.plugins.cms.content.service.ICmsContentService#loadContent(java.lang.String)
     */
    @Override
    public CmsContent loadContent(String contentId) {
        CmsContent cmsContent = (CmsContent) this.contentDao.load(CmsContent.class, contentId);
        if (cmsContent != null) {
            /*cmsContent.setContentText(
                LobHelper.getInstance().getClobText(CmsContent.class.getName(), contentId));*/
        }
        return cmsContent;
    }

    /**
     * @see com.plugins.cms.content.service.ICmsContentService#saveContentEditInfo(com.plugins.cms.content.command.CmsContentEditInfo)
     */
    @Override
    public void saveContentEditInfo(CmsContentEditInfo info) {
        CmsContent bean = null;
        if (info.getContentId() != null && info.getContentId().trim().length() > 0) {
            bean = this.loadContent(info.getContentId());
        }
        if (bean == null) {
            bean = new CmsContent();
        }
        BeanUtils.copyProperties(info, bean, new String[]{"contentId"});
        if (bean.getTopLevel() == null || bean.getTopLevel().trim().length() == 0) {
            bean.setTopLevel("0"); //Oracle的排序，对空值的处理与其他不太一样，所以.....
        }
        this.contentDao.save(bean);

        /////LobHelper.getInstance().saveClobText(bean.getClass().getName(), bean.getContentId(), info.getContentText());

    }

    /**
     * @see com.plugins.cms.content.service.ICmsContentService#deleteContents(java.lang.String[])
     */
    @Override
    public void deleteContents(String[] ids) {
        if (ids == null || ids.length <= 0) {
            return;
        }
        for (String id : ids) {
            CmsContent bean = (CmsContent) this.contentDao.load(CmsContent.class, id);
            if (bean != null) {
                this.contentDao.delete(bean);
            }
        }
    }

    /**
     * @see com.plugins.cms.content.service.ICmsContentService#getSortOrder(java.lang.String)
     */
    @Override
    public Integer getSortOrder(String parentId) {
        return this.contentDao.getSortOrder(parentId);
    }



    /**
     * @param contentDao the contentDao to set
     */
    public void setContentDao(ICmsContentDao contentDao) {
        this.contentDao = contentDao;
    }
}
