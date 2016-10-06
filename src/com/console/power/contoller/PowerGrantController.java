
package com.console.power.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springline.beans.dictionary.DictionaryLoader;
import org.springline.beans.tree.ITreeNode;
import org.springline.web.mvc.SpringlineSimpleFormController;
import org.springline.web.view.GBRedirectView;

import com.console.entity.Power;
import com.console.power.command.PowerGrantInfo;
import com.console.power.service.IPowerService;


public class PowerGrantController extends SpringlineSimpleFormController {

    /**     *     */
    private IPowerService powerService;

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        PowerGrantInfo info = (PowerGrantInfo) command;
        this.powerService.updatePowerGrantInfo(info);
        Map model = new HashMap();
        model.put("powerId", info.getPowerId());
        return new ModelAndView(new GBRedirectView(super.getSuccessView()), model);
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map model = new HashMap();
//        Map menus = (Map) SystemData.getInstance().getSystemData(Constants.MAIN_MENU_DATATYPE_KEY, null);
        ITreeNode menus = (ITreeNode) DictionaryLoader.getInstance().getAllDictionary().get(Power.TREE_DIC_IDENTIFICATION);
        model.put("menus", menus);
        model.put("powerList", this.powerService.selectAllPower(null));
        String powerId = request.getParameter("powerId");
        if (powerId != null && powerId.trim().length() > 0) {
            model.put("currentPower", this.powerService.selectPower(powerId));
        }
        return model;
    }

    /**
     * @param roleService The roleService to set.
     */
    public void setPowerService(IPowerService roleService) {
        this.powerService = roleService;
    }
}
