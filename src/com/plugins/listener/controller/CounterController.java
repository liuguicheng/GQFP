/**
 * Description:
 * History:  2013-08-07 Create
 **/

package com.plugins.listener.controller;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.plugins.listener.OnlineCounter;


/**
 * 
 * @description
 */
public class CounterController extends SpringlineMultiActionController {

	@SuppressWarnings("unchecked")
	public ModelAndView counter(HttpServletRequest request,
			HttpServletResponse response, Object info) {
		
		Map model = new HashMap();
		String countNum = String.valueOf(OnlineCounter.pageviewTotalCount);
		model.put("countNum", countNum);
		return new ModelAndView("listener/counter", model);
	}
}
