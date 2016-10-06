
package com.console.power.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.entity.Power;
import com.console.power.command.PowerInfo;
import com.console.power.service.IPowerService;


public class PowerEditController extends SpringlineSimpleFormController {
    /**  */
    private IPowerService powerService;


    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
            BindException errors) throws Exception {
        PowerInfo powerInfo = (PowerInfo) command;
        Power power = new Power();
        BeanUtils.copyProperties(powerInfo, power);

        power = this.getPowerService().savePower(power);
        Map model = new HashMap();
        model.put("token", request.getParameter("token"));
        model.put("tab", request.getParameter("tab"));
        return new ModelAndView(new GBRedirectView(super.getSuccessView()),model);
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
     */
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
        String id = request.getParameter("powerId");
        if (id != null && id.trim().length() > 0) {
            Power power = this.getPowerService().selectPower(id);
            PowerInfo powerInfo = new PowerInfo();
            BeanUtils.copyProperties(power, powerInfo);
            model.put("command", powerInfo);
        }
        model.put("token", request.getParameter("token"));
        model.put("tab", request.getParameter("tab"));
        return model;
    }

    /**
     * @return Returns the powerService.
     */
    public IPowerService getPowerService() {
        return this.powerService;
    }

    /**
     * @param powerService The powerService to set.
     */
    public void setPowerService(IPowerService powerService) {
        this.powerService = powerService;
    }

}
