package com.plugins.dirtree.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.plugins.dirtree.service.IDirService;

public class DirDataController extends SpringlineMultiActionController {

    /**
     * Service½Ó¿Ú
     */
    private IDirService dirService;

    /**
     * @param dirService
     */
    public void setDirService(IDirService dirService) {
        this.dirService = dirService;
    }

    /**
     * Âß¼­É¾³ý£¬½ûÓÃ
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView dirDataDelete(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
         try {
             String id = request.getParameter("dirDataid");
             String dirtypecode = request.getParameter("dirTypeCode");

             if(id != null && id.trim().length() > 0) {
                 dirService.doDirDataDelete(id);
             }
             dirService.doRefreshDirDataDic(dirService.selectDirData(id).getDirTypeId(), dirtypecode);
         } catch(Exception e) {
             System.err.println(e.getMessage());
         }
         return new ModelAndView(new GBRedirectView("../dirTree/dirDataSelect.do?" + request.getQueryString()));
    }

    /**
     * ÆôÓÃ£¬Âß¼­É¾³ýµÄÄæÏò²Ù×÷
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView dirDataUse(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
         try {
             String id = request.getParameter("dirDataid");
             String dirtypecode = request.getParameter("dirTypeCode");

             if(id != null && id.trim().length() > 0) {
                 dirService.doDirDataUse(id);
             }
             dirService.doRefreshDirDataDic(dirService.selectDirData(id).getDirTypeId(), dirtypecode);
         } catch(Exception e) {
             System.err.println(e.getMessage());
         }
         return new ModelAndView(new GBRedirectView("../dirTree/dirDataSelect.do?" + request.getQueryString()));
    }

    /**
     * ÅÅÐò
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView dirDataSort(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try{
            String [] ids = request.getParameterValues("dirId");
            dirService.doDirDataSort(ids);

            if(ids != null && ids.length >0) {
                String dirtypecode = request.getParameter("dirTypeCode");
                dirService.doRefreshDirDataDic(dirService.selectDirData(ids[0]).getDirTypeId(), dirtypecode);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ModelAndView(new GBRedirectView("../dirTree/dirDataSelect.do?" + request.getQueryString()));
    }
}
