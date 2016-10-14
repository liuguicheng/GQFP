package com.console.main.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Staff;
import com.console.support.MenuManager;

/**
 * @description url访问过滤器
 * 用户登录系统后，对普通用户在浏览器直接输入url地址访问本身没具有的权限页面进行控制。
 * （注：目前访问权限控制只能控制用户访问的菜单，在菜单下的访问路径目前没有更好的办法控制）。
 */
public class PowerFilter  extends HttpServlet implements Filter {
    /**
     * 
     */
    private static final long serialVersionUID = -1295765347804389970L;

    /**  
     * 重定向的URL  
     */  
    private String redirectURl = null; 
    
    private static Logger LOG = Logger.getLogger(PowerFilter.class); 
    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        // 指定要重定向的页面   
        redirectURl = "/jump.html"; 
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request; 
        HttpServletResponse sresponse = (HttpServletResponse) response;   
        HttpSession session = req.getSession();
        String advancedPassword=(String) session.getAttribute("advancedPassword");
        String threePassword=(String) session.getAttribute("threePassword");
        Staff staff =  (Staff) AuthenticationFilter.getAuthenticator(req);   
        if(staff == null){
            sresponse.sendRedirect(redirectURl); 
        } else {
             try {    
                    //所属部门
                    Department dep = ConsoleHelper.getInstance().getManageService().selectDepartment(staff.getDepartment().getId());
                    //获取URL地址
                    String url = ".."+req.getServletPath();
                    //根据Url查询权限信息
                    if(url != null && url.length() > 0){
                       Power power = ConsoleHelper.getInstance().getManageService().selectPowerByUrl(url);
                       if(power !=null){
                    	   //验证是否需要高级密码
                    	   boolean isadv= MenuManager.isAdvancedPassword(advancedPassword, threePassword, power);
                    	   if(isadv){
                    			 redirectURl="/main/goVerifyAdvancedPasswrod.do?url="+url+"&level="+power.getPasswordLevel();
                  			   	 // 过滤器经过过滤后，过滤链继续传递请求和响应   
                  			     String path = req.getContextPath()+redirectURl;
                  			    ((HttpServletResponse) response).sendRedirect(path);
                    	   }
                           boolean isPower = MenuManager.hasPower(power,staff,dep);
                           if(!isPower){
                               // 过滤器经过过滤后，过滤链继续传递请求和响应   
                               String path = req.getContextPath()+redirectURl;
                                ((HttpServletResponse) response).sendRedirect(path);
                           } else {
                            // 过滤器经过过滤后，过滤链继续传递请求和响应   
                               chain.doFilter(request, response);
                           }
                       } else {
                           chain.doFilter(request, response);
                       }   
                    }  
             } catch (Throwable e) {   
                 LOG.error("权限过滤时候出现错误", e);   
                 String path = req.getContextPath()+redirectURl;
                 ((HttpServletResponse) response).sendRedirect(path);
//                 throw new RuntimeException("权限过滤时候出现错误", e);   
             }   
        }
    
    }

	

    /**
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        //
    }

}

