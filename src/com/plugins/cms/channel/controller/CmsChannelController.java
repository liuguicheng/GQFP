/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.channel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.plugins.cms.channel.command.CmsChannelQueryInfo;
import com.plugins.cms.channel.service.ICmsChannelService;

/**
 * @description
 */
public class CmsChannelController extends SpringlineMultiActionController {

    private ICmsChannelService channelService;

    /**
     * 栏目管理
     *
     * @param request HttpServletRequest实例
     * @param response HttpServletResponse实例
     * @return ModelAndView实例
     */
    public ModelAndView channelManage(HttpServletRequest request,
            HttpServletResponse response) {
        return new ModelAndView("cms/channel/channelManage");
    }
    /**
     * 栏目管理
     *
     * @param request HttpServletRequest实例
     * @param response HttpServletResponse实例
     * @return ModelAndView实例
     */
    public ModelAndView channelManageList(HttpServletRequest request,
            HttpServletResponse response, CmsChannelQueryInfo info) {
        Map model = new HashMap();
//        String parentId = request.getParameter("parentId");
        model.put("page", this.channelService.selectChannelList(info));
        model.put("command", info);
        model.put("message", request.getParameter("message"));
        return new ModelAndView("cms/channel/channelManageList", model);
    }
    /**
     * 栏目管理
     *
     * @param request HttpServletRequest实例
     * @param response HttpServletResponse实例
     * @return ModelAndView实例
     */
    public ModelAndView channelSelect(HttpServletRequest request,
            HttpServletResponse response) {
        return new ModelAndView("cms/channel/channelSelect");
    }
    /**
     * 内容删除
     *
     * @param request HttpServletRequest实例
     * @param response HttpServletResponse实例
     * @return ModelAndView实例
     */
    public ModelAndView channelDelete(HttpServletRequest request,
            HttpServletResponse response) {
        String[] ids = request.getParameterValues("channelId");
        Map model = new HashMap();
        try {
            this.channelService.deleteChannels(ids);
            model.put("message", "数据已成删除");
        } catch (Exception ex) {
            model.put("message", ex.getMessage());
        }
        model.put("parentId", request.getParameter("parentId"));
        return new ModelAndView(new RedirectView("../cms/channelManageList.do"), model);
    }

    /**
     * @param channelService the channelService to set
     */
    public void setChannelService(ICmsChannelService channelService) {
        this.channelService = channelService;
    }
}
