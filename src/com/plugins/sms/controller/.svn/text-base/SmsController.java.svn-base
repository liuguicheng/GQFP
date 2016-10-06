package com.plugins.sms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.console.entity.Staff;
import com.plugins.sms.SmsHelper;
import com.plugins.sms.command.SmsInfo;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.entity.SysSms;
import com.plugins.sms.service.ISmsService;

public class SmsController extends SpringlineMultiActionController {

    /** 服务注入 */
    private ISmsService smsService;

    public void setSmsService(ISmsService smsService) {
        this.smsService = smsService;
    }

    /**
     * 短信编辑界面
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView smsEdit(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        model.put("now", new Date());
        model.put("message", request.getParameter("message"));
        return new ModelAndView(getViewMap().get("smsEdit").toString(), model);
    }

    /**
     * 发送短信
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView smsSend(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String content = request.getParameter("smsContent");
        String[] rNums = request.getParameterValues("rNum");
        String[] rIds = request.getParameterValues("rId");
        String[] rNames = request.getParameterValues("rName");
        // 拼接
        String reNums = "";
        for (String rNum : rNums) {
            reNums += rNum + ",";
        }
        reNums = reNums.substring(0, reNums.length() - 1);
        String reIds = "";
        for (String rId : rIds) {
            reIds += rId + ",";
        }
        reIds = reIds.substring(0, reIds.length() - 1);
        String reNames = "";
        for (String rName : rNames) {
            reNames += rName + ",";
        }
        reNames = reNames.substring(0, reNames.length() - 1);

        SmsInfo info = new SmsInfo();
        Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
        if (self != null) {
            info.setSenderId(self.getId());
            info.setSenderName(self.getName());
            info.setSmsInfo(content);
            info.setReceiverIds(reIds);
            info.setReceiveNums(reNums);
            info.setReceiverNames(reNames);
            if (SmsHelper.getInstance().sendSms(info)) {
                model.put("message", "提交成功！");
            } else {
                model.put("message", "操作未成功！部分短信可能发送成功，请查询短信发送记录以检查发送失败的短信记录。");
            }
        } else {
            throw new RuntimeException("出错啦！无法获取当前用户信息！");
        }

        return new ModelAndView(new GBRedirectView(getViewMap().get("smsSend").toString()), model);
    }

    /**
     * 短信重发
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView smsResend(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String[] ids = request.getParameterValues("id");
        try {
//            if (this.smsService.resendSms(ids)) {
//                model.put("message", "操作成功！");
//            } else {
//                model.put("message", "操作失败！");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("message", "操作失败！");
        }
        model.put("token", request.getParameter("token"));
        return new ModelAndView(new GBRedirectView(getViewMap().get("smsResend").toString()), model);
    }

    /**
     * 全部重发短信
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ModelAndView smsAllResend(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        // 查询所有失败短信
        SmsQueryInfo info = new SmsQueryInfo();
        info.setNotPage(true);
        Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
        if (self != null) {
            if (self.hasOperate(SysSms.OPERATE_CODE_MANAGE)) {
                // 有短信平台管理权限，可以查看所有人发送的短信
            } else {
                // 没有短信平台管理权限，只能查看本人发送的短信
                info.setSenderId(self.getId());
            }
        }
        // 查询提交失败
        info.setSmsState(SysSms.STATE_CREATE_FAIL);
        Page page = this.smsService.selectSmsPage(info);
        if (page != null && page.getData() != null && page.getData().size() > 0) {
            List<SysSms> list = page.getData();
            String[] rIds = new String[list.size()];
            for (int i = 0; i < rIds.length; i++) {
                rIds[i] = list.get(i).getRecordId();
            }
//            if (this.smsService.resendSms(rIds)) {
//                model.put("message", "操作成功！");
//            } else {
//                model.put("message", "操作失败！");
//            }
        } else {
            model.put("message", "无法查询到提交失败的短信数据，无法执行全部重发操作！");
        }
        return new ModelAndView(new GBRedirectView(getViewMap().get("smsResend").toString()), model);
    }

    /**
     * 短信发送管理视图
     * 
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("rawtypes")
    public ModelAndView smsSendManage(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        return new ModelAndView(getViewMap().get("smsManage").toString(), model);
    }

}
