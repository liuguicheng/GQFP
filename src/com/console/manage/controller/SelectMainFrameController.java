
package com.console.manage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springline.web.filter.AuthenticationFilter;


public class SelectMainFrameController extends AbstractController {

    /**  */
    private String selectMainFrameView;
    /**  */
    private String selectLeftView;
    /**  */
    private String selectRightView;
    /**
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected final ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map model = new HashMap();
        model.put("currentUser", AuthenticationFilter.getAuthenticator(request));
        model.put("selectLeftView", this.selectLeftView);
        model.put("selectRightView", this.selectRightView);
        return new ModelAndView(this.selectMainFrameView, model);
    }

    /**
     * @param selectMainFrameView The selectMainFrameView to set.
     */
    public void setSelectMainFrameView(String selectMainFrameView) {
        this.selectMainFrameView = selectMainFrameView;
    }

    /**
     * @param selectLeftView The selectLeftView to set.
     */
    public void setSelectLeftView(String selectLeftView) {
        this.selectLeftView = selectLeftView;
    }

    /**
     * @param selectRightView The selectRightView to set.
     */
    public void setSelectRightView(String selectRightView) {
        this.selectRightView = selectRightView;
    }

}
