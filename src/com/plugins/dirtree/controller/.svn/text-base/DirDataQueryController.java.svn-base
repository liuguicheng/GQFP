package com.plugins.dirtree.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.plugins.dirtree.entity.DirType;
import com.plugins.dirtree.service.IDirService;

public class DirDataQueryController extends SpringlineMultiActionController {

    /**
     *
     */
    private IDirService dirService;

    /**
     * @param dirService
     */
    public void setDirService(IDirService dirService) {
        this.dirService = dirService;
    }

    /**
     * 目录管理
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView dirManage(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = new HashMap();
        try {
            List typelist = dirService.selectValidDirTypes();
            model.put("dirtypelist", typelist);
            model.put("selectdirtype",
                    ((com.plugins.dirtree.entity.DirType) typelist.get(0))
                            .getDirTypeCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("dirTree/dirManage", model);
    }

    /**
     * 目录内容管理，dirManage内嵌部分，可外部独立调用
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView dirDataManage(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = new HashMap();
        try {
            String dirtypecode = request.getParameter("dirTypeCode");
            if (dirtypecode != null) {
                List dirTree = dirService.selectDirDataTreeByType(dirtypecode, null);
                model.put("dirtree", dirTree);
                model.put("selectdirtype", dirtypecode);

                DirType type = dirService.selectDirTypeByCode(dirtypecode);
                model.put("selectdirtypename", type.getDirTypeName());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new ModelAndView("dirTree/dirDataManage", model);
    }

    /**
     * 目录选择？
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView dirDataDialog(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = new HashMap();
        try {
            String dirtypecode = request.getParameter("dirTypeCode");
            if (dirtypecode != null) {
                List dirTree = dirService.selectDirDataTreeByType(dirtypecode,null);
                model.put("dirtree", dirTree);
                model.put("selectdirtype", dirtypecode);

                DirType type= dirService.selectDirTypeByCode(dirtypecode);
                model.put("selectdirtypename",type.getDirTypeName());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new ModelAndView("dirTree/selectDirData", model);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView dirDataSelect(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = new HashMap();
        try {
            String dirtypecode = request.getParameter("dirTypeCode");
            String dirparentid = request.getParameter("dirParentId");

            if (dirtypecode != null && dirtypecode.length() > 0) {
                List dirdatalist = null;
                String typeid = dirService.selectDirTypeByCode(dirtypecode)
                        .getDirTypeId();

                model.put("listtitle", "根目录");
                if (dirparentid != null && dirparentid.length() > 0) {
                    String listtitle = dirService.selectDirData(dirparentid)
                            .getDirName();
                    model.put("listtitle", "【"+listtitle+"】子目录列表");
                    model.put("dirParentId", dirparentid);
                }
                dirdatalist = dirService.selectDirDataList(typeid,
                        dirparentid == null ? "" : dirparentid);

                model.put("dirDataList", dirdatalist);
                model.put("dirTypeCode", dirtypecode);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new ModelAndView("dirTree/dirDataList", model);
    }
}
