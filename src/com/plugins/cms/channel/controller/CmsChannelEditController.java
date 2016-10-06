package com.plugins.cms.channel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.plugins.cms.channel.command.CmsChannelEditInfo;
import com.plugins.cms.channel.service.ICmsChannelService;
import com.plugins.cms.entity.CmsChannel;


public class CmsChannelEditController extends SpringlineSimpleFormController {
    /**  */
    private ICmsChannelService channelService;

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        CmsChannel channel = null;
        String id = request.getParameter("channelId");
        if (id == null || id.trim().length() == 0) { // ÐÂÔö
            channel = new CmsChannel();
            String parentId = request.getParameter("parentId");
            channel.setParentId(parentId);
//            if (parentId != null && parentId.trim().length() > 0) {
//                CmsChannel parent = this.channelService.loadChannel(parentId);
//                if (parent != null) {
//                    channel.setParentId(parent.getChannelId());
//                }
//            }
            channel.setSortOrder(this.channelService.getSortOrder(channel.getParentId()));
        } else { // ÐÞ¸Ä
            channel = this.channelService.loadChannel(id);
        }
        model.put("command", channel);
        return model;
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        Map model = new HashMap();
        CmsChannelEditInfo info = (CmsChannelEditInfo) command;
        model.put("parentId", info.getParentId());
        try {
            this.channelService.saveChannelEditInfo(info);
        } catch (Exception ex) {
            model.put("message", ex.getMessage());
            model.put("command", info);
            return new ModelAndView(this.getFormView(), model);
        }

        return new ModelAndView(new GBRedirectView(this.getSuccessView()),
            model);
    }

    /**
     * @param channelService the channelService to set
     */
    public void setChannelService(ICmsChannelService channelService) {
        this.channelService = channelService;
    }



}
