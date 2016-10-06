

package com.plugins.memory.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


public class ViewMemoryController extends AbstractController {

    /** （non Javadoc）
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if ("1".equals(request.getParameter("dogc"))) {
            System.gc();
        }

        PrintWriter printer = response.getWriter();

        printer.println("<html>");
        printer.println("<head>");
        printer.println("<meta http-equiv='Content-Type' content='text/html; charset=GBK' />");
        printer.println("</head>");
        printer.println("<body>");
        printer.println("<h3>");
        printer.println("<pre>");
        printer.println("FreeMemory  [" + Runtime.getRuntime().freeMemory() + "] Bytes");
        printer.println("TotalMemory [" + Runtime.getRuntime().totalMemory() + "] Bytes");
        printer.println("MaxMemory   [" + Runtime.getRuntime().maxMemory() + "] Bytes");
        printer.println("</pre>");
        printer.println("</h3>");
        printer.println("<form method=post>");
        printer.println("<input type=hidden name=dogc value=1>");
        printer.println("<input type=submit value='GC'>");
        printer.println("</form>");
        printer.println("</body>");
        printer.println("</html>");

        return null;
    }

}
