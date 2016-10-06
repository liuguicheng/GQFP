/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.channel.command;

import org.springline.web.pagination.PaginationInfo;

/**
 * @description
 */
public class CmsChannelQueryInfo extends PaginationInfo {

    /**
     *
     */
    private static final long serialVersionUID = 3875622466440828763L;

    /**
     * 根节点定义
     */
    public static final String ROOT = "root";
    /**
     * 上级栏目，根栏目为root
     */
    private String parentId;
    
    private String channelPath;
    /**
     * @return the parentId
     */
    public String getParentId() {
        return parentId;
    }
    /**
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    /**
     * @return the channelPath
     */
    public String getChannelPath() {
        return channelPath;
    }
    /**
     * @param channelPath the channelPath to set
     */
    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }
}
