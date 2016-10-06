package com.plugins.pending.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.console.entity.Staff;
import com.plugins.pending.command.PendingItemQureyInfo;
import com.plugins.pending.common.PendingItemHelper;
import com.plugins.pending.service.IPendingItemService;

public class PendingItemQueryController extends PaginationQueryController {
    /** IPendingItemService */
    private IPendingItemService pendingItemService;

    /**
     * @see org.springline.web.pagination.PaginationQueryController#selectQueryResult(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse,
     *      com.spower.basesystem.common.command.BaseCommandInfo, Map)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Page selectQueryResult(HttpServletRequest request, HttpServletResponse response, PaginationInfo command,
            Map model) throws Exception {
        PendingItemQureyInfo info = (PendingItemQureyInfo) command;
        Staff st = (Staff) AuthenticationFilter.getAuthenticator(request);
        info.setRecipientId(st.getId());
        Page page = this.pendingItemService.selectPendingItem(info);
        model.put("pendingItemFlag", PendingItemHelper.FLAG_PENDINGITEM);
        model.put("message", request.getParameter("message"));
        return page;
    }

    /**
     * @param pendingItemService
     *            The pendingItemService to set.
     */
    public void setPendingItemService(IPendingItemService pendingItemService) {
        this.pendingItemService = pendingItemService;
    }

}
