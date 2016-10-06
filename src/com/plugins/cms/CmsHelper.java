package com.plugins.cms;

import java.util.Observable;

import org.springline.orm.Page;

import com.plugins.cms.content.command.CmsContentQueryInfo;
import com.plugins.cms.entity.CmsChannel;
import com.plugins.cms.entity.CmsContent;

public abstract class CmsHelper extends Observable {

    /**
     * 单实例的类工厂 Spring IOC控制
     */
    private static CmsHelper instance = null;
    /**
     * 废置状态
     */
    public static final String STATUS_DELETED = "0";
    /**
     * 正常状态
     */
    public static final String STATUS_NORMAL = "1";
    /**
     * @return the instance
     */
    public static CmsHelper getInstance() {
        if (instance == null) {
            throw new RuntimeException("未创建CMSHelper的实例对象！");
        }
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public void setInstance(CmsHelper instance) {
        CmsHelper.instance = instance;
    }
    
    /**
     * 根据Id加载栏目
     * @param channelId
     * @return
     */
    public abstract CmsChannel loadCmsChannel(String channelId);
    /**
     * 根据路径编码获取栏目
     * @param channelPath
     * @return
     */
    public abstract CmsChannel selectCmsChannel(String channelPath);
    
    /**
     * 获取内容清单
     * @param info
     * @return
     */
    public abstract Page selectCmsContentList(CmsContentQueryInfo info);

    /**
     * @param contentId
     * @return
     */
    public abstract CmsContent loadCmsContent(String contentId);

}
