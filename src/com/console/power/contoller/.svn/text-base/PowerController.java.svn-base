
package com.console.power.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineCommandHelper;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.console.power.command.PowerInfo;
import com.console.power.command.PowerQueryInfo;
import com.console.power.service.IPowerService;

/**
 * @description
 */
public class PowerController extends SpringlineMultiActionController {
    /**  */
    private IPowerService powerService;


    /**
     * 列权限视图.
     * @param request   HttpServletRequest实例
     * @param response  HttpServletResponse实例
     * @return  ModelAndView
     */
    public ModelAndView powerList(HttpServletRequest request, HttpServletResponse response, PowerQueryInfo command) {
        Map model =  new HashMap();  //主页消息框跳转时
        String tab = request.getParameter("tab");
        // 设置显示的tab页
        if(StringUtils.isBlank(tab)){
            tab = PowerInfo.DIC_MODULE_NAME_WEB;
        }
        model.put("tab", tab);
        model.put("message", request.getParameter("message"));
        return new ModelAndView("manage/powerManage", model);
    }

    
    /**
     * 列权限列表.
     * @param request   HttpServletRequest实例
     * @param response  HttpServletResponse实例
     * @return  ModelAndView
     */
    public ModelAndView powerInnerList(HttpServletRequest request, HttpServletResponse response, PowerQueryInfo command) {
        PowerQueryInfo info = (PowerQueryInfo) SpringlineCommandHelper.getSpringlineCommand(request);
        String tab = request.getParameter("tab");
        if (info == null) {
            info = command;
        }
        Map model = new HashMap();
        model.put("message", request.getParameter("message"));
        model.put("command", info);
        info.setModuleName(tab);                                                    //电脑端或者手机端标记
        model.put("powerList", powerService.selectAllPower(info));
        model.put("token", request.getParameter("token"));
        model.put("tab", tab);
        SpringlineCommandHelper.cacheSpringlineCommand(request, info);
        return new ModelAndView("manage/powerList", model);
    }
    
    /**
     * 删除指定的权限信息.
     * @param request   HttpServletRequest实例
     * @param response  HttpServletResponse实例
     * @return  ModelAndView
     */
    public ModelAndView powerDelete(HttpServletRequest request, HttpServletResponse response) {
        String[] powerId = request.getParameterValues("powerId");
        Map model = new HashMap();
        try {
            this.powerService.deletePower(powerId);
            model.put("message", "已成功删除所选菜单!");
        } catch (Exception ex) {
            model.put("message", "菜单已授权给相应角色，不能删除！");
        }
        model.put("token", request.getParameter("token"));
        model.put("tab", request.getParameter("tab"));
        return new ModelAndView(new GBRedirectView("../manage/powerInnerList.do"), model);
    }


    /**
     * @param powerService The powerService to set.
     */
    public void setPowerService(IPowerService powerService) {
        this.powerService = powerService;
    }

}
