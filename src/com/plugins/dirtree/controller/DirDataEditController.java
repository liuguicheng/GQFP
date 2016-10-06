package com.plugins.dirtree.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.plugins.dirtree.command.DirDataEditInfo;
import com.plugins.dirtree.entity.DirData;
import com.plugins.dirtree.entity.DirType;
import com.plugins.dirtree.service.IDirService;

public class DirDataEditController extends SpringlineSimpleFormController {

    /**
     * 服务接口
     */
    private IDirService dirService;

    /**
     * @param dirService
     */
    public void setDirService(IDirService dirService) {
        this.dirService = dirService;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        Map model = new HashMap();
        try {
            DirDataEditInfo info = (DirDataEditInfo) command;
            String id = request.getParameter("dirDataid");
            String typecode = request.getParameter("dirTypeCode");

            if (id != null && id.trim().length() > 0) {
                info.setDirId(id);
            }
            boolean sucess = dirService.saveDirData(info);
            if (!sucess)
                throw new Exception("保存失败！");

            // 更新字典
            if (typecode != null && typecode.trim().length() > 0) {
                dirService.doRefreshDirDataDic(info.getDirTypeId(), typecode);
            }
            return new ModelAndView(new GBRedirectView(super.getSuccessView()
                    + "?" + request.getQueryString()), model);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.put("opMessage", e.getMessage());
            model.put("command", command);
            return new ModelAndView(super.getFormView(), model);
        }
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        // TODO Auto-generated method stub
        Map model = new HashedMap();
        try {
            DirData dirdata = null;
            String typecode = request.getParameter("dirTypeCode");
            String parentid = request.getParameter("dirParentId");
            String id = request.getParameter("dirDataid");

            String typeid = "";
            if (typecode != null && typecode.trim().length() > 0) {
                DirType type = dirService.selectDirTypeByCode(typecode);
                model.put("type", type);
                typeid = type.getDirTypeId();
            }

            if (id != null && id.trim().length() > 0) {
                dirdata = this.dirService.selectDirData(id);
                if (dirdata.getParentId() != null
                        && dirdata.getParentId().trim().length() > 0)
                    model.put("parentname", dirService.selectDirData(
                            dirdata.getParentId()).getDirName());
            }else{
                dirdata = new DirData();
                dirdata.setSortOrder(dirService
                        .selectUsableDirDataOrderNumber(typeid,parentid));
                if(parentid != null && parentid.trim().length() > 0){
                    dirdata.setParentId(parentid);
                    model.put("parentname", dirService.selectDirData(parentid)
                            .getDirName());
                }
            }
            model.put("command", dirdata);
            model.put("requestque", "dirTypeCode=" + typecode + "&dirParentId="
                    + parentid);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return model;
    }
}
