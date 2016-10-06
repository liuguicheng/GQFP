package com.plugins.sms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.console.entity.Staff;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.entity.SysSms;
import com.plugins.sms.service.ISmsService;

public class SmsQueryController extends PaginationQueryController {
    /** 查询类型：提交失败 */
    private static final String Query_TYPE_FAIL = "fail";

    /** 查询类型 */
    private String queryType;
    /** 服务注入 */
    private ISmsService smsService;

    public void setSmsService(ISmsService smsService) {
        this.smsService = smsService;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Page selectQueryResult(HttpServletRequest request, HttpServletResponse response, PaginationInfo command,
            Map model) throws Exception {
        SmsQueryInfo info = (SmsQueryInfo) command;
        Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
        if (self != null) {
            if (self.hasOperate(SysSms.OPERATE_CODE_MANAGE)) {
                // 有短信平台管理权限，可以查看所有人发送的短信
            } else {
                // 没有短信平台管理权限，只能查看本人发送的短信
                info.setSenderId(self.getId());
            }
        }
        if (StringUtils.isNotBlank(this.queryType) && this.queryType.equals(Query_TYPE_FAIL)) {
            // 查询提交失败
            info.setSmsState(SysSms.STATE_CREATE_FAIL);
        }
        return this.smsService.selectSmsViewPage(info);
    }

}
