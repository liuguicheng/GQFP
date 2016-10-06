
package com.plugins.pending.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.console.entity.Staff;
import com.plugins.pending.command.FinishedItemQueryInfo;
import com.plugins.pending.service.IPendingItemService;

 /**
 * @description
 */
public class FinishedItemQueryController extends PaginationQueryController {

    /**
     * IOC ¿ØÖÆ
     */
    private IPendingItemService pendingItemService;

    /**
     * @see org.springline.web.pagination.PaginationQueryController#selectQueryResult(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.spower.basesystem.common.command.BaseCommandInfo, Map)
     */
    protected Page selectQueryResult(HttpServletRequest request, HttpServletResponse response, PaginationInfo command, Map model) throws Exception  {
        FinishedItemQueryInfo info = (FinishedItemQueryInfo) command;
        info.setNotPage(new Boolean(true));
        Staff st = (Staff) AuthenticationFilter.getAuthenticator(request);
        info.setRecipientId(st.getId());
        return pendingItemService.selectFinishedItems(info);
    }

    /**
     * @param pendingItemService the pendingItemService to set
     */
    public void setPendingItemService(IPendingItemService pendingItemService) {
        this.pendingItemService = pendingItemService;
    }


}
