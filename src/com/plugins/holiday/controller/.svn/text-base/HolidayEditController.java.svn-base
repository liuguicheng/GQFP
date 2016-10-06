package com.plugins.holiday.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;
import com.plugins.holiday.command.HolidayEditInfo;
import com.plugins.holiday.service.IHolidayService;

public class HolidayEditController extends SpringlineSimpleFormController {

    private IHolidayService holidayService;


    @SuppressWarnings("unchecked")
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
        Map model = new HashMap();
        HolidayEditInfo editInfo = (HolidayEditInfo) command;
        try {
            this.holidayService.saveHoliday(editInfo);
            model.put("message", "操作成功！");
        } catch (Exception e) {
            model.put("message","操作失败"+ e.getMessage());
        }
        return new ModelAndView(new GBRedirectView(super.getSuccessView()),
                model);
    }

    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request) throws Exception {
        String holidayId = request.getParameter("holidayId");
        Map model = new HashMap();
        if (holidayId != null && holidayId.trim().length() > 0) {
            model.put("holiday", this.holidayService.selectHolidayById(holidayId));
        }
        return model;
    }

    public IHolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(IHolidayService holidayService) {
        this.holidayService = holidayService;
    }

}
