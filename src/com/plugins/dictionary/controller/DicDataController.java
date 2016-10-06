package com.plugins.dictionary.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.plugins.dictionary.entity.DicType;
import com.plugins.dictionary.service.IDictionaryService;

public class DicDataController extends SpringlineMultiActionController{

    /**字典Service注入*/
    private IDictionaryService dictionaryService;

    /**字典内容列表视图*/
    private String dicDataListView;

    /**字典内嵌内容列表视图*/
    private String dicDataInnerListView;



    /**
     * @param dictionaryService the dictionaryService to set
     */
    public void setDictionaryService(IDictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /**
     * @param dicDataListView the dicDataListView to set
     */
    public void setDicDataListView(String dicDataListView) {
        this.dicDataListView = dicDataListView;
    }

    /**
     * @param dicDataInnerListView the dicDataInnerListView to set
     */
    public void setDicDataInnerListView(String dicDataInnerListView) {
        this.dicDataInnerListView = dicDataInnerListView;
    }

    /**
     * 列出所有的字典内容信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ModelAndView dicDataList(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = new HashedMap();
        //加载所有的字典类型
        model.put("dicTypeList", this.dictionaryService.selectAllDicType());
        return new ModelAndView(this.dicDataListView,model);
    }

    /**
     *根据字典类型ID查找显示出所有的字典内容记录
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ModelAndView dicDataInnerList(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = new HashedMap();
        String typeCode = request.getParameter("typeCode");
        String message = request.getParameter("message");
        model.put("message", message);
        if (typeCode != null && typeCode.trim().length() != 0) {
        	DicType dicType = this.dictionaryService.getDicTypeByCode(typeCode);
            List dicDataList = this.dictionaryService.selectAllDicDataByCode(typeCode);
            model.put("dicDataList", dicDataList);
            model.put("dicType", dicType);
        }
        return new ModelAndView(this.dicDataInnerListView, model);
    }

    /**
     * 逻辑批量删除字典内容信息
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    public ModelAndView dicDataDelete(HttpServletRequest request,
    		HttpServletResponse response) throws Exception {
    	Map model = new HashedMap();
    	String[] checkValue = request.getParameterValues("dataId");
    	this.dictionaryService.deleteDicData(checkValue);
    	return dicDataInnerList(request, response);
    }

    /**
     * 启用字典的内容信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView dicDataUse(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	Map model = new HashedMap();
        String[] checkValue = request.getParameterValues("dataId");
        this.dictionaryService.updateDicData(checkValue);
        return dicDataInnerList(request, response);
    }


}
