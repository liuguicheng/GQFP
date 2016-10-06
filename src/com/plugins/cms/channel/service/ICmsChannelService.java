/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.channel.service;

import java.util.List;

import org.springline.orm.Page;

import com.plugins.cms.channel.command.CmsChannelEditInfo;
import com.plugins.cms.channel.command.CmsChannelQueryInfo;
import com.plugins.cms.entity.CmsChannel;

/**
 * @description
 */
public interface ICmsChannelService {

    /**
     * 查询栏目
     * @param info
     * @return
     */
    Page selectChannelList(CmsChannelQueryInfo info);

    /**
     * 加载栏目对象
     * @param channelId
     * @return
     */
    CmsChannel loadChannel(String channelId);

    /**
     * 保存
     * @param info
     */
    void saveChannelEditInfo(CmsChannelEditInfo info);

    /**
     * 获取可用顺序号
     * @param parentId
     * @return
     */
    Integer getSortOrder(String parentId);

    /**
     * 删除栏目
     * @param ids
     */
    void deleteChannels(String[] ids);
    
	/**
	 * 根据类型编码加载所有的字典数据
	 * @param typeCode
	 * @return
	 */
	List loadAllCmsChannel();
}
