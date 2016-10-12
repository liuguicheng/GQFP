package com.console.main.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.dictionary.support.DictionaryUtils;
import org.springline.beans.tree.ITreeNode;
import org.springline.web.WebUITool;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.OperateLog;
import com.console.entity.Power;
import com.console.entity.Staff;
import com.console.entity.StaffSecurity;
import com.console.main.service.IMainService;
import com.console.support.MenuManager;
/**
 * @description 入口Controller.
 */
public class MainController extends SpringlineMultiActionController {
    /** IManageService */
    private IMainService mainService;
    /** 系统字符编码 */
    private static String PLATFORM_ENCODING = System
        .getProperty("file.encoding");

    // private ICorpBehaveService corpBehaveService =
    // CorpHelper.getInstance().getCorpBehaveService();

    /**
     * 默认的工作页面
     */
    private static String DEFAULT_DESTOP_VIEW = "../main/homepage.do";

    /**
     * 索引
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response, HashMap model) throws Exception {
//        model.put("apkU", value)
        return new ModelAndView("main/index", model);
    }

    /**
     * 登录操作，不成功返回索引，成功转到 mainView
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView login(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HashMap model = new HashMap();
        StaffSecurity staffTimes = null;
        StaffSecurity inputTimes = null;
        String loginName = request.getParameter("userName");
        String password = request.getParameter("password");
       //获取页面是用什么浏览器访问的值
        String browser = request.getParameter("browser");
        
        int i = 0;
        boolean afterfailTime = false;
        Staff staffLogin = this.mainService.selectAllStaff(loginName);//此处要调用新方法，返回指定登录名的用户，不限制是否有效！
        model.put("userName", loginName);
        if (staffLogin == null) {
            model.put("message", "该用户不存在！");
            model.put("loginName", loginName);
            return index(request, response, model);
        } else{
            StaffSecurity ss = this.mainService.loadStaffSecurity(staffLogin.getId());
            if(ss.getIsLock()!= null
                    && ss.getIsLock().equals(ConsoleHelper.NO)){
                    // 获取登录失败时间
                    long failedRecordTime = ss.getFailedRecordTime().getTime();
                    // 获取当前系统时间
                    long currentTime = new Date().getTime();
                    // 用当前系统时间减去登录失败时间
                    long timeRecord = (currentTime - failedRecordTime)
                            / (1000 * 60 * 60 * 24);
                    // 如果小于1天则继续锁定
                    if (timeRecord < 1) {
                        model.put("message", "您当天登录失败的次数已超过三次，该用户已经被锁定,请在24小时之后再进行登录！");
                        model.put("loginName", loginName);
                        return index(request, response, model);
                    }else{
                        afterfailTime = true;
                    }
            }
        }

        Staff staff = this.mainService.doValidate(loginName, password);
        if (staff == null) {
            int count;
            Staff failTimes = this.mainService.selectAllStaff(loginName);
            if(failTimes != null){
                StaffSecurity ss = this.mainService.loadStaffSecurity(failTimes.getId());
                // 计算次数,判断是IE才执行
                if (browser.equals("IE")) {
                    i = ss.getTimes();
                    i = i + 1;
                    staffTimes = this.mainService.doTimes(loginName, i);
                }
                
                if (ss.getFailedTimes() == null) {
                    count = 0;
                    count++;
                    // 记录登录失败次数，和登录失败时间
                    if (browser.equals("IE")) {
                        inputTimes = this.mainService.doFailiedTimes(loginName,
                                count);
                    }
                    if (browser.equals("")) {
                        inputTimes = this.mainService.doFailiedTimes(loginName,
                                count);
                    }
                } else if (ss.getFailedTimes() < 3) {
                    int countNum = ss.getFailedTimes();
                    countNum++;
                    // 记录登录失败次数，和登录失败时间
                    if (browser.equals("IE") && staffTimes.getTimes() > 1) {
                        inputTimes = this.mainService.doFailiedTimes(loginName,
                                countNum);
                    }
                    if (browser.equals("")) {
                        inputTimes = this.mainService.doFailiedTimes(loginName,
                                countNum);
                    }
                }
                // 计算用户还可以输入多少次密码
                int haveInputTimes = 0 ;
                if (inputTimes != null ) {              
                     haveInputTimes = inputTimes.getFailedTimes();
                }else{
                    haveInputTimes = ss.getFailedTimes();
                }
                haveInputTimes = 3 - haveInputTimes;
                if (haveInputTimes != 0) {
                    model.put("message", "用户名或者密码错误！【今日再输错" + haveInputTimes + "次，账号将锁定】  ");
                } else {
                    if(afterfailTime){
                        model.put("message", "用户名或者密码错误！【该用户已经被锁定，请输入正确的密码进行解锁】  ");
                    }else{
                        model.put("message", "用户名或者密码错误！【今日输错已达到三次，账号被锁定】  ");
                    }
                }
            }

            model.put("loginName", loginName);
            return index(request, response, model);
        } 
        
        // 对登录成功后的用户进行解锁
        staff = this.mainService.doUnlock(staff);
        
        AuthenticationFilter.doAuthentication(request, staff);
        
        //标记在线
        this.mainService.updateIsLogin(staff, ConsoleHelper.YES);
     // 登录成功后显示登录日志
        String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上登录系统";
        ConsoleHelper.getInstance().getLogService().saveOperateLog(OperateLog.LOG_TYPE_SYSTEM, staff, logContent);
        
        return new ModelAndView(new RedirectView("../main/home.do"));
    }

    /**
     * 显示主视图
     *
     * @param request HttpServletRequest实例
     * @param response HttpServletResponset实例
     * @return ModelAndView实例
     */
    public ModelAndView home(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HashMap model = new HashMap();
        Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
        if (staff == null) {
            return new ModelAndView(new RedirectView("../main/index.do"));
        }
        
        Department ancestorDep = this.mainService.selectDepartment(staff
            .getDepartment().getAncestorDep());
        ITreeNode data = (ITreeNode) CacheHelper.getInstance().getCacheObject(Power.TREE_DIC_IDENTIFICATION); // SystemData.getInstance().getSystemData(Constants.MAIN_MENU_DATATYPE_KEY,
        // null);
        ITreeNode menu = MenuManager.buildMenuTree(data, staff, ancestorDep);
        if (menu == null || menu.getChildren().size() == 0) {
            model.put("message", staff.getName()
                    + " 没有使用系统的权限，请联系系统管理员进行相应的授权操作！");
            return index(request, response, model);
        }

        model.put("mainMenu", menu.getChildren());

        String view = request.getParameter("rightView");
        if (view == null) {
            model.put("rightView", DEFAULT_DESTOP_VIEW);
        } else {
            view = this.encoding(view);
            model.put("rightView", view);
        }
        model.put("leftMenuView", "../main/menuTree.do");
        // 企业行为信息提示，审核、发布信息
        model.put("staff", staff);
        
        return new ModelAndView("main/mainView", model);
    }

    /**
     * 显示菜单
     *
     * @param request HttpServletRequest实例
     * @param response HttpServletResponset实例
     * @return ModelAndView实例
     */
    public Map menuTree(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
        Department ancestorDep = this.mainService.selectDepartment(staff
            .getDepartment().getAncestorDep());
        ITreeNode root = (ITreeNode) CacheHelper.getInstance().getCacheObject(Power.TREE_DIC_IDENTIFICATION);
        String moduleName = request.getParameter("moduleName");
        if (moduleName != null && moduleName.trim().length() > 0) {
            root = root.find(moduleName);
        }
        ITreeNode menu = MenuManager.buildMenuTree(root, staff, ancestorDep);
        model.put("menuTree", menu);
        
        return model;
    }

    /**
     * 显示主页
     *
     * @param request HttpServletRequest实例
     * @param response HttpServletResponset实例
     * @return ModelAndView实例
     */
    public Map homepage(HttpServletRequest request, HttpServletResponse response) {
        return new HashMap();
    }

    /**
     * 退出系统.
     *
     * @param request HttpServletRequest实例
     * @param response HttpServletResponset实例
     * @return ModelAndView实例
     */
    public ModelAndView logout(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
        request.getSession().invalidate();
        AuthenticationFilter.cancelAuthentication(request);
        //标记离线
       // this.mainService.updateIsLogin(staff, ConsoleHelper.NO);
        // 退出系统后显示登录日志
        String logContent = "在IP为" +  ConsoleHelper.getUserIp() + "的机器上退出系统";
        ConsoleHelper.getInstance().getLogService().saveOperateLog(OperateLog.LOG_TYPE_SYSTEM, staff, logContent);
        return index(request, response, null);
    }

    /**
     * 修改密码
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    protected Map passwordEdit(HttpServletRequest request,
            HttpServletResponse response, Map model) throws Exception {
        return model;
    }

    /**
     * 保存修改的密码
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected Map savePassword(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = new HashMap();
        String loginName = request.getParameter("loginName");
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");

        Staff staffLogin = this.mainService.selectStaff(loginName);
        if (staffLogin == null) {
            model.put("message", "该用户不存在！");
            return passwordEdit(request, response, model);
        } else  {
            StaffSecurity ss = this.mainService.loadStaffSecurity(staffLogin.getId());
            if(ss.getIsLock()!= null
                    && ss.getIsLock().equals(ConsoleHelper.NO)) {
                model.put("message", "该用户已经被锁定！");
                return passwordEdit(request, response, model);
            }
        }
        // 用户校验
        Staff staff = this.mainService.doValidate(loginName, oldPassword);
        if (staff == null) {
            model.put("message", "错误的用户名或密码！");
            return passwordEdit(request, response, model);
        }
        this.mainService.updatePassword(staff, password);
        model.put("returnValue", "1");
        return passwordEdit(request, response, model);
    }

    /**
     * @param manageService The manageService to set.
     */
    public void setMainService(IMainService mainService) {
        this.mainService = mainService;
    }

    /**
     * @param source String
     * @return String
     */
    private String encoding(String source) {
        try {
            return new String(source.getBytes("GBK"), PLATFORM_ENCODING);
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.toString());
            return source;
        }
    }
    /**
     * 二维码名片
     * 
     * @param request
     *            HttpServletRequest实例
     * @param response
     *            HttpServletResponset实例
     * @return ModelAndView实例
     */
    public ModelAndView getQRCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = request.getParameter("msg");
        WebUITool.createQRCode(request, response, msg);
        return null;
    }
    // /**
    // *
    // */
    // private static String platformEncoding;
    // static {
    // platformEncoding = System.getProperty("file.encoding");
    // }
    // /**
    // * 将源字符串从 GBK 编码转换到系统平台编码
    // * @param source 要转换的字符串
    // * @return 转换后的字符串，如果转换失败则返回源字符串
    // */
    // public static String gbkToPlatformEncoding(String source) {
    // try {
    // return new String(source.getBytes("GBK"), platformEncoding);
    // } catch (UnsupportedEncodingException e) {
    // System.err.println(e.toString());
    // return source;
    // }
    // }

}
