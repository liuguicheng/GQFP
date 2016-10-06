package com.plugins.sms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springline.orm.Page;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.plugins.sms.command.SmsReplyQueryInfo;
import com.plugins.sms.service.ISmsService;

public class SmsReplyQueryController extends PaginationQueryController {
    /** ·þÎñ×¢Èë */
    private ISmsService smsService;

    public void setSmsService(ISmsService smsService) {
        this.smsService = smsService;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Page selectQueryResult(HttpServletRequest request, HttpServletResponse response, PaginationInfo command,
            Map model) throws Exception {
        SmsReplyQueryInfo info = (SmsReplyQueryInfo) command;
        return this.smsService.selectSmsReplyViewPage(info);
    }

}
