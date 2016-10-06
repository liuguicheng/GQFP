package com.plugins.dictionary.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.ConsoleHelper;
import com.plugins.dictionary.command.DicTypeEditInfo;
import com.plugins.dictionary.entity.DicData;
import com.plugins.dictionary.entity.DicType;
import com.plugins.dictionary.service.IDictionaryService;

public class DicTypeEditController extends SpringlineSimpleFormController{

	/**字典dictionaryService接口*/
	private IDictionaryService dictionaryService;

	/**
	 *
	 * @return 返回dictionaryService
	 */
	public IDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	/**
	 *
	 * @param dictionaryService 设置dictionaryService
	 */
	public void setDictionaryService(IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	/**
	 *
	 * @see org.springline.web.mvc.SpringlineSimpleFormController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
			throws Exception {
		super.initBinder(request, binder);
        binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true", "false", true));
	}

	/**
	 *
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(java.lang.Object)
	 */
	 @SuppressWarnings("unchecked")
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
		 Map model = new HashMap();
        DicTypeEditInfo dicTypeInfo = (DicTypeEditInfo) command;
    	String preTypeNo = request.getParameter("preTypeNo");
    	try {
	    	//当typeNo不为空时进行首字母大写转换
	    	StringBuffer typeCode = new StringBuffer(dicTypeInfo.getTypeCode());
	    	if (typeCode != null && typeCode.length() != 0) {
	    		typeCode.setCharAt(0, Character.toTitleCase(typeCode.charAt(0)));
	    		dicTypeInfo.setTypeCode(preTypeNo + typeCode.toString());

	    	}
	        model.put("message", "保存成功");
	        this.dictionaryService.saveDicType(dicTypeInfo);
    	} catch (Exception e) {
			model.put("message", "保存失败！" + e.getMessage());
		}
        return new ModelAndView(new GBRedirectView(super.getSuccessView()), model);
    }

	    /**
	     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	     */
	   @SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) throws Exception {
    	Map model = new HashedMap();
    	String typeId = request.getParameter("typeId");
        //ID不为空将对象绑定到页面上
        if (typeId != null && typeId.trim().length() > 0) {
        	//从typeCode第四位截取
        	DicType dicType = this.dictionaryService.getDicTypeById(typeId);
        	if (dicType.getTypeCode() != null && dicType.getTypeCode().trim().length() != 0) {
        		dicType.setTypeCode(dicType.getTypeCode().substring(3));
        	}
        	model.put("dicTypeInfo", dicType);
        } else {
        	DicTypeEditInfo dicTypeInfo = new DicTypeEditInfo();
	        //将当前最大的排序号set到dataSort里
        	dicTypeInfo.setTypeSort(this.dictionaryService.getMaxTypeSort());
	        dicTypeInfo.setIsLonger(ConsoleHelper.YES);//1:定长,0不定长，此项设置为定长
	        dicTypeInfo.setTypeWay(DicData.AUTO_GENERATE);//1手工录入；2系统生成,此项设置为系统生成
	        model.put("dicTypeInfo", dicTypeInfo);
        }
        return model;
    }
}
