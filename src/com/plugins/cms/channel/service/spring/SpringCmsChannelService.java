/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.channel.service.spring;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;
import org.springline.beans.dictionary.support.DictionaryUtils;
import org.springline.beans.tree.ITreeNode;
import org.springline.beans.tree.support.paternity.PaternityTreeBuilder;
import org.springline.orm.Page;

import com.plugins.cms.CmsHelper;
import com.plugins.cms.channel.command.CmsChannelEditInfo;
import com.plugins.cms.channel.command.CmsChannelQueryInfo;
import com.plugins.cms.channel.service.ICmsChannelService;
import com.plugins.cms.channel.service.dao.ICmsChannelDao;
import com.plugins.cms.content.command.CmsContentQueryInfo;
import com.plugins.cms.content.service.ICmsContentService;
import com.plugins.cms.entity.CmsChannel;
import com.plugins.dictionary.entity.DicType;
import com.plugins.sn.service.SNHelper;

/**
 * @description
 */
public class SpringCmsChannelService implements ICmsChannelService{
    private ICmsChannelDao channelDao;
    private ICmsContentService contentService;
/*    *//**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     *//*
    public void afterPropertiesSet() {
        CacheHelper.getInstance().registerConnector(CmsChannel.SIMPLE_DIC_IDENTIFICATION, this);
        CacheHelper.getInstance().registerConnector(CmsChannel.TREE_DIC_IDENTIFICATION, this);
    }*/

/*    *//**
     * @see org.springline.beans.cache.ICacheConnector#loadCacheObject(java.lang.String)
     *//*
    @Override
    public Object loadCacheObject(String objName) {
        if (CmsChannel.SIMPLE_DIC_IDENTIFICATION.equals(objName)) {
            List depts = this.channelDao.loadAll(CmsChannel.class, null);// .loadAll(Department.class,
            return DictionaryUtils.listToDictionaryItemMap(depts, "channelId", "channelName");
        } else if (CmsChannel.TREE_DIC_IDENTIFICATION.equals(objName)) {
            List depts = this.channelDao.loadAll(CmsChannel.class, null);// .loadAll(Department.class,
            return PaternityTreeBuilder.createTree(depts);
        }
        return null;
    }*/
    
	/**
	 * @see com.plugins.dictionary.service.IDictionaryService#selectAllDicType()
	 */
	public List loadAllCmsChannel() {
		return this.channelDao.loadAll(CmsChannel.class, null);
	}

    private void refreshDic() {
        CacheHelper.getInstance().dispatchRefreshEvent(CmsChannel.SIMPLE_DIC_IDENTIFICATION);
        CacheHelper.getInstance().dispatchRefreshEvent(CmsChannel.TREE_DIC_IDENTIFICATION);
    }
    /**
     * @see com.plugins.cms.channel.service.ICmsChannelService#selectChannelList(com.plugins.cms.channel.command.CmsChannelQueryInfo)
     */
    @Override
    public Page selectChannelList(CmsChannelQueryInfo info) {
        return this.channelDao.selectCmsChannelList(info);
    }

    /**
     * @see com.plugins.cms.channel.service.ICmsChannelService#loadChannel(java.lang.String)
     */
    @Override
    public CmsChannel loadChannel(String channelId) {
        return (CmsChannel) this.channelDao.load(CmsChannel.class, channelId);
    }

    /**
     * @see com.plugins.cms.channel.service.ICmsChannelService#saveChannelEditInfo(com.plugins.cms.channel.command.CmsChannelEditInfo)
     */
    @Override
    public void saveChannelEditInfo(CmsChannelEditInfo info) {
        CmsChannel bean = null;
        if (info.getChannelId() != null && info.getChannelId().trim().length() > 0) {
            bean = this.loadChannel(info.getChannelId());
        }
        if (bean == null) {
            bean = new CmsChannel();
            bean.setChannelId(SNHelper.getSNService().getSerialNumber(CmsChannel.class.getName(), ""));
            bean.setChannelStatus(CmsHelper.STATUS_NORMAL);
        }
        BeanUtils.copyProperties(info, bean, new String[]{"channelId"});
        if (bean.getParentId() != null && bean.getParentId().equals(info.getChannelId())) {
            bean.setParentId(null); //根路径
        }
        this.channelDao.save(bean);
        refreshDic();
    }

    /**
     * @see com.plugins.cms.channel.service.ICmsChannelService#getSortOrder(java.lang.String)
     */
    @Override
    public Integer getSortOrder(String parentId) {
        return this.channelDao.getSortOrder(parentId);
    }



    /**
     * @see com.plugins.cms.channel.service.ICmsChannelService#deleteChannels(java.lang.String[])
     */
    @Override
    public void deleteChannels(String[] ids) {
        if (ids == null || ids.length <= 0) {
            return;
        }
        ITreeNode channelTree = (ITreeNode) CacheHelper.getInstance().getCacheObject(CmsChannel.TREE_DIC_IDENTIFICATION);
        for (String id : ids) {
            CmsChannel bean = (CmsChannel) this.channelDao.load(CmsChannel.class, id);
            if (bean != null) {
                ITreeNode tmp = channelTree.find(bean.getChannelId());
                if (tmp != null && !tmp.isLeaf()) {
                    throw new DataIntegrityViolationException("请先删除栏目『"
                            + bean.getChannelName() + "』的下属栏目，再删除该栏目！");
                }
                CmsContentQueryInfo qi = new CmsContentQueryInfo();
                qi.setChannelId(bean.getChannelId());
                Page page = this.contentService.selectContentList(qi);
                if (page != null && page.getData() != null && page.getData().size() > 0) {
                    throw new DataIntegrityViolationException("请先删除栏目『"
                            + bean.getChannelName() + "』的内容，再删除该栏目！");
                }

                this.channelDao.delete(bean);
                refreshDic();
            }
        }
    }

    /**
     * @param channelDao the channelDao to set
     */
    public void setChannelDao(ICmsChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    /**
     * @param contentService the contentService to set
     */
    public void setContentService(ICmsContentService contentService) {
        this.contentService = contentService;
    }
}
