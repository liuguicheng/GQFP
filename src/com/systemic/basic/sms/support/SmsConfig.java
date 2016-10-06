package com.systemic.basic.sms.support;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class SmsConfig extends PropertyPlaceholderConfigurer {
	private static String smsIPUrl = null;
	private static String smsPortUrl = null;
    
	/**
	 * 
	 * 取配置文件IP 端口
	 * @param beanFactoryToProcess
	 * @param props
	 * @throws BeansException
	 */
	@SuppressWarnings("rawtypes")
	protected void processProperties(ConfigurableListableBeanFactory 
			beanFactoryToProcess,Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		Map<String, String> ctxPropertiesMap = new HashMap<String, String>();
	    for (Iterator localIterator = props.keySet().iterator(); localIterator.hasNext(); ) { 
	    	Object key = localIterator.next();
	    	String keyStr = key.toString();
	    	String value = props.getProperty(keyStr);
	    	ctxPropertiesMap.put(keyStr, value);
	    }
	    Object tmpIP = ctxPropertiesMap.get("smsIP.authority.url");
	    if (tmpIP != null)
	    	smsIPUrl = tmpIP.toString();
	    
	    Object tmpPort = ctxPropertiesMap.get("smsPort.authority.url");
	    if (tmpPort != null)
	    	smsPortUrl = tmpPort.toString();
	}

	public static String getSmsIPUrl() {
		return smsIPUrl;
	}

	public static void setSmsIPUrl(String smsIPUrl) {
		SmsConfig.smsIPUrl = smsIPUrl;
	}

	public static String getSmsPortUrl() {
		return smsPortUrl;
	}

	public static void setSmsPortUrl(String smsPortUrl) {
		SmsConfig.smsPortUrl = smsPortUrl;
	}
	

	
}
