package com.plugins.dictionary.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.plugins.dictionary.service.IDictionaryService;

public class DicTypeController extends SpringlineMultiActionController{

	/**字典dictionaryService接口*/
	private IDictionaryService dictionaryService;
	/**字典类型列表视图*/
	private String dicTypeQueryView;


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
	 * @return 返回dicTypeQueryView视图
	 */

	public String getDicTypeQueryView() {
		return dicTypeQueryView;
	}

	/**
	 * 设置dicTypeQueryView视图
	 * @param dicTypeQueryView
	 */
	public void setDicTypeQueryView(String dicTypeQueryView) {
		this.dicTypeQueryView = dicTypeQueryView;
	}



    /**
     * 批量删除所选择的字典类型数据
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
	public ModelAndView dicTypeDelete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] checkValue = request.getParameterValues("typeId");
		//删除checkValue里的所有值
		this.dictionaryService.deleteDicType(checkValue);
		//调用本地的dicTypeList方法
		return new ModelAndView(new GBRedirectView(this.dicTypeQueryView));
	}
}
