/**
 * @文件名称：MemberHelperImpl.java
 * @文件描述：
 * @版权所有：(C)2008-2010
 * @公司：CERC
 * @完成日期:  2012-6-14
 * @作者    ： litao
 */
package com.plugins.cms.core;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;
import org.springline.beans.dictionary.support.DictionaryUtils;
import org.springline.beans.tree.support.paternity.PaternityTreeBuilder;
import org.springline.orm.Page;

import com.plugins.cms.CmsHelper;
import com.plugins.cms.channel.command.CmsChannelQueryInfo;
import com.plugins.cms.channel.service.ICmsChannelService;
import com.plugins.cms.content.command.CmsContentQueryInfo;
import com.plugins.cms.content.service.ICmsContentService;
import com.plugins.cms.entity.CmsChannel;
import com.plugins.cms.entity.CmsContent;

public class CmsHelperSupport extends CmsHelper implements InitializingBean,ICacheConnector{

    private ICmsChannelService channelService;
    private ICmsContentService contentService;
    
     /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() {
        CacheHelper.getInstance().registerConnector(CmsChannel.SIMPLE_DIC_IDENTIFICATION, this);
        CacheHelper.getInstance().registerConnector(CmsChannel.TREE_DIC_IDENTIFICATION, this);
    }

    /**
     * @see org.springline.beans.cache.ICacheConnector#loadCacheObject(java.lang.String)
     */
    @Override
    public Object loadCacheObject(String objName) {
        if (CmsChannel.SIMPLE_DIC_IDENTIFICATION.equals(objName)) {
            List depts = this.channelService.loadAllCmsChannel();
            return DictionaryUtils.listToDictionaryItemMap(depts, "channelId", "channelName");
        } else if (CmsChannel.TREE_DIC_IDENTIFICATION.equals(objName)) {
            List depts = this.channelService.loadAllCmsChannel();
            return PaternityTreeBuilder.createTree(depts);
        }
        return null;
    }
    
    /**
    *
    */
    public CmsHelperSupport() {
        super.setInstance(this);
    }
    /**
     * @see com.plugins.cms.CmsHelper#loadCmsChannel(java.lang.String)
     */
    @Override
    public CmsChannel loadCmsChannel(String channelId) {
        return this.channelService.loadChannel(channelId);
    }
    
    /**
     * @see com.plugins.cms.CmsHelper#selectCmsChannel(java.lang.String)
     */
    @Override
    public CmsChannel selectCmsChannel(String channelPath) {
        CmsChannelQueryInfo info = new CmsChannelQueryInfo();
        info.setChannelPath(channelPath);
        Page page = this.channelService.selectChannelList(info);
        if (page != null && page.getData() != null && page.getData().size() > 0) {
            return (CmsChannel) page.getData().get(0);
        }
        return null;
    }
    
    /**
     * @see com.plugins.cms.CmsHelper#selectCmsContentList(com.plugins.cms.content.command.CmsContentQueryInfo)
     */
    @Override
    public Page selectCmsContentList(CmsContentQueryInfo info) {
        info.setStatus(CmsContent.STATUS_NORNAL);
        return this.contentService.selectContentList(info);
    }

    /**
     * @see com.plugins.cms.CmsHelper#loadCmsContent(java.lang.String)
     */
    @Override
    public CmsContent loadCmsContent(String contentId) {
        return this.contentService.loadContent(contentId);
    }

    /**
     * @param contentService the contentService to set
     */
    public void setContentService(ICmsContentService contentService) {
        this.contentService = contentService;
    }

    /**
     * @param channelService the channelService to set
     */
    public void setChannelService(ICmsChannelService channelService) {
        this.channelService = channelService;
    }



}
