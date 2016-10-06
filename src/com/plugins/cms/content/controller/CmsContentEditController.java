package com.plugins.cms.content.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springline.beans.utils.DateHelper;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.plugins.cms.content.command.CmsContentEditInfo;
import com.plugins.cms.content.service.ICmsContentService;
import com.plugins.cms.entity.CmsContent;


public class CmsContentEditController extends SpringlineSimpleFormController {
    /**  */
    private ICmsContentService contentService;

    /**
     * @see org.springline.web.mvc.SpringlineSimpleFormController#initBinder(javax.servlet.http.HttpServletRequest,
     *      org.springframework.web.bind.ServletRequestDataBinder)
     */
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        // 时间精确到时分秒
        binder.registerCustomEditor(Date.class, new CustomDateEditor(DateHelper.DATE_TIME_FORMAT, true));

    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        CmsContent content = null;
        String id = request.getParameter("contentId");
        if (id == null || id.trim().length() == 0) { // 新增
            content = new CmsContent();
            String channelId = request.getParameter("channelId");
            content.setChannelId(channelId);
            content.setReleaseDate(new Date());
            content.setStatus(CmsContent.STATUS_NORNAL);
            // content.setSortOrder(this.contentService.getSortOrder(content.getParentId()));
        } else { // 修改
            content = this.contentService.loadContent(id);
        }
        model.put("command", content);
        model.put("channelPath", request.getParameter("channelPath"));
        return model;
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
        Map model = new HashMap();
        CmsContentEditInfo info = (CmsContentEditInfo) command;
        model.put("channelId", info.getChannelId());
        try {
            this.contentService.saveContentEditInfo(info);
            model.put("message", "保存成功！");
        } catch (Exception ex) {
            model.put("message", ex.getMessage());
            model.put("command", info);
            return new ModelAndView(this.getFormView(), model);
        }
        System.out.println(request.getParameter("channelPath"));
        return new ModelAndView(new GBRedirectView(this.getSuccessView() + "?channelPath="
                + request.getParameter("channelPath")), model);
    }

    /**
     * @param contentService
     *            the contentService to set
     */
    public void setContentService(ICmsContentService contentService) {
        this.contentService = contentService;
    }

}
