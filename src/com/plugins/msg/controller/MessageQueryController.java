package com.plugins.msg.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.console.entity.Staff;
import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.service.IMsgService;

public class MessageQueryController extends PaginationQueryController {
	
	
    private IMsgService msgService;

    public void setMsgService(IMsgService msgService) {
        this.msgService = msgService;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Page selectQueryResult(HttpServletRequest request, HttpServletResponse response, PaginationInfo command,
            Map model) throws Exception {
        MessageQueryInfo info = (MessageQueryInfo) command;
        info.setSendMan(info.getSendMan());
        if (info.getStaffId() == null || info.getStaffId().trim().length() < 1) {
            Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
            if (self != null) {
                info.setStaffId(self.getId());
            }
        }
         
        Page page = this.msgService.selectMessage(info);
        return page;
    }
}
