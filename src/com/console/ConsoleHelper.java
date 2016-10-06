package com.console;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Observable;

import org.springline.beans.cache.CacheHelper;
import org.springline.beans.dictionary.IDictionaryMapValueItem;
import org.springline.web.view.support.HtmlHelper;

import com.console.entity.Department;
import com.console.entity.Staff;
import com.console.main.service.IMainService;
import com.console.manage.service.IManageService;
import com.console.operate.service.IOperateService;
import com.console.operatelog.service.IOperateLogService;
import com.console.power.service.IPowerService;
import com.console.role.service.IRoleService;
import com.systemic.gq.bonus.service.IBonusRecordServcie;
import com.systemic.gq.member.service.ISpringMemberService;
import com.systemic.gq.pay.service.ISpringPayLogService;
import com.systemic.gq.stock.service.IBonusContentService;
import com.systemic.gq.stock.service.IRuleService;

public abstract class ConsoleHelper extends Observable {

	/**
	 * 单实例的类工厂 Spring IOC控制
	 */
	private static ConsoleHelper instance = null;
	/** 是 */
	public static final String YES = "1";
	/** 否 */
	public static final String NO = "0";

	public static final String APP_DEPARTMENT = "216";// 行政审批大厅

	/**
	 * 根据部门Id从内存字典获取部门对象
	 *
	 * @param depId 部门Id
	 * @return Department
	 */
	public static Department getDepartment(String depId) {
		Map dicData = (Map) CacheHelper.getInstance().getCacheObject("dicDepartment");
		IDictionaryMapValueItem item = (IDictionaryMapValueItem) HtmlHelper
			.getMapData(dicData, depId);
		return (Department) item.getData();
	}

	/**
	 * 根据人员Id从内存字典获取人员对象
	 *
	 * @param staffId 人员Id
	 * @return Staf
	 */
	public static Staff getStaff(String staffId) {
		Map dicData = (Map) CacheHelper.getInstance().getCacheObject("dicStaff");
		IDictionaryMapValueItem item = (IDictionaryMapValueItem) HtmlHelper
			.getMapData(dicData, staffId);
		if (item != null) {
			return (Staff) item.getData();
		}
		return null;
	}

	/**
	 * 将人员Id列表转换成名称列表
	 *
	 * @param ids id列表
	 * @return 名称列表
	 */
	public static String getStaffNames(String ids) {
		if (ids != null && ids.trim().length() > 0) {
			String[] id = ids.split(",");
			StringBuffer names = new StringBuffer();
			Map dicData = (Map) CacheHelper.getInstance().getCacheObject("dicStaff");
			for (int i = 0; i < id.length; i++) {
				IDictionaryMapValueItem item = (IDictionaryMapValueItem) HtmlHelper
					.getMapData(dicData, id[i]);
				names.append(item.getName()).append("，");
			}
			if (names.length() > 0) {
				names.deleteCharAt(names.lastIndexOf("，"));
			}
			return names.toString();
		}
		return null;
	}

	/**
	 * 将人员Id列表转换成名称列表
	 *
	 * @param ids id列表
	 * @return 名称列表
	 */
	public static String getDepartmentNames(String ids) {
		if (ids != null && ids.trim().length() > 0) {
			String[] id = ids.split(",");
			StringBuffer names = new StringBuffer();
			Map dicData = (Map) CacheHelper.getInstance().getCacheObject("dicDepartment");
			for (int i = 0; i < id.length; i++) {
				IDictionaryMapValueItem item = (IDictionaryMapValueItem) HtmlHelper
					.getMapData(dicData, id[i]);
				names.append(item.getName()).append("，");
			}
			if (names.length() > 0) {
				names.deleteCharAt(names.lastIndexOf("，"));
			}
			return names.toString();
		}
		return null;
		
	}

	
	 /**
     * 获取当前用户IP
     *
     * @return java.lang.String
     */
    public static String getUserIp() {
        //获取用户登录IP
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } 
        return ip;
    }
    
	/**
	 * @return the instance
	 */
	public static ConsoleHelper getInstance() {
		if (instance == null) {
			throw new RuntimeException("未创建ConsoleHelper的实例对象！");
		}
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	protected void setInstance(ConsoleHelper instance) {
		ConsoleHelper.instance = instance;
	}

	/**
	 * @return IManageService
	 */
	public abstract IManageService getManageService();

	/**
	 * @return the IRoleService
	 */
	public abstract IRoleService getRoleService();

	/**
	 * @return IPowerService
	 */
	public abstract IPowerService getPowerService();

	/**
	 * @return IOperateService
	 */
	public abstract IOperateService getOperateService();

	/**
	 * @return IOperateLogService
	 */
	public abstract IOperateLogService getLogService();
	/**
	 * @return IMainService
	 */
	public abstract IMainService getMainService();
	/**
	 * @return ISpringPayLogService
	 */
	public abstract ISpringPayLogService getSpringPayLogService();
	/**
	 * @return ISpringMemberService
	 */
	public abstract ISpringMemberService getSpringMemberService();
	
	public abstract IBonusContentService getBonuscontentService();
	
	public abstract IBonusRecordServcie getBonusRecordService();
	
	public abstract IRuleService getRuleService();
}
