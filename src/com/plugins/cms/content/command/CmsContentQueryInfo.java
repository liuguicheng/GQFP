/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.content.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

/**
 * @description
 */
public class CmsContentQueryInfo extends PaginationInfo {

    /**
     *
     */
    private static final long serialVersionUID = 3875622466440828763L;


    /**
     * À¸Ä¿Id
     */
    private String channelId;
    private String channelPath;
    private String title;

    private Date releaseDateUp;
    private Date releaseDateDown;

    private String status;
    /**
     * @return the channelId
     */
    public String getChannelId() {
        return channelId;
    }


    /**
     * @param channelId the channelId to set
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }


    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }


    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * @return the releaseDateUp
     */
    public Date getReleaseDateUp() {
        return releaseDateUp;
    }


    /**
     * @param releaseDateUp the releaseDateUp to set
     */
    public void setReleaseDateUp(Date releaseDateUp) {
        this.releaseDateUp = releaseDateUp;
    }


    /**
     * @return the releaseDateDown
     */
    public Date getReleaseDateDown() {
        return releaseDateDown;
    }


    /**
     * @param releaseDateDown the releaseDateDown to set
     */
    public void setReleaseDateDown(Date releaseDateDown) {
        this.releaseDateDown = releaseDateDown;
    }


    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }


    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
