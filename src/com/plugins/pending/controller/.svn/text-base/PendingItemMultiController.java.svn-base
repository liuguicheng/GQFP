package com.plugins.pending.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.console.entity.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.plugins.pending.command.PendingItemQureyInfo;
import com.plugins.pending.common.PendingItemHelper;
import com.plugins.pending.entity.PendingItem;
import com.plugins.pending.entity.PendingItemRecipients;
import com.plugins.pending.service.IPendingItemService;

public class PendingItemMultiController extends SpringlineMultiActionController {
    /** IPendingItemService */
    private IPendingItemService pendingItemService;
    /** 显示在主页的视图 **/
    private String pendingItemHomepageView;

    /**
     * 主页查询待办事宜
     * 
     * @param request
     * @see HttpServletRequest
     * @param response
     * @see HttpServletResponse
     * @return ModelAndView
     */
    public ModelAndView pendingItemHomepage(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();

        Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
        PendingItemQureyInfo info = new PendingItemQureyInfo();
        info.setRecipientId(staff.getId());
        Page page = this.pendingItemService.selectPendingItem(info);

        model.put("pendingItemList", page.getData());
        return new ModelAndView(this.pendingItemHomepageView, model);
    }

    /**
     * 异步查询待办事宜
     * 
     * @param request
     * @param response
     */
    @SuppressWarnings("unchecked")
    public void pendingItemAsyncList(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charSet=gbk");
        PrintWriter out = null;
        String staffId = request.getParameter("staffId");
        try {
            List<PendingItem> todoList = new ArrayList<PendingItem>();
            Page todoData = PendingItemHelper.getInstance().selectPendingItemList(staffId, "");
            if (todoData != null && todoData.getData() != null) {
                List<PendingItemRecipients> list = todoData.getData();
                PendingItem todoItem = null;
                for (PendingItemRecipients item : list) {
                    todoItem = new PendingItem();
                    todoItem.setUrl(item.getForDoc().getUrl());
                    todoItem.setTitle(item.getForDoc().getTitle());
                    todoItem.setSendTime(item.getForDoc().getSendTime());
                    todoItem.setSender(new Staff());
                    todoItem.getSender().setName(item.getForDoc().getSender().getName());
                    todoList.add(todoItem);
                }
            }

            Gson json = new GsonBuilder().create();
            out = response.getWriter();
            out.print(json.toJson(todoList, new TypeToken<List<PendingItem>>() {
            }.getType()));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * @param pendingItemHomepageView
     *            The pendingItemHomepageView to set.
     */
    public void setPendingItemHomepageView(String pendingItemHomepageView) {
        this.pendingItemHomepageView = pendingItemHomepageView;
    }

    /**
     * @param pendingItemService
     *            The pendingItemService to set.
     */
    public void setPendingItemService(IPendingItemService pendingItemService) {
        this.pendingItemService = pendingItemService;
    }

}
