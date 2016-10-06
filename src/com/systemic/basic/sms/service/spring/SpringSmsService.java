package com.systemic.basic.sms.service.spring;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.cache.ICacheConnector;
import org.springline.beans.dictionary.support.DictionaryUtils;

import com.plugins.sn.service.SNHelper;
import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.command.SmsEditInfo;
import com.systemic.basic.sms.command.SmsQueryInfo;
import com.systemic.basic.sms.service.ISmsService;
import com.systemic.basic.sms.service.dao.ISmsDao;


public class SpringSmsService implements ISmsService,ICacheConnector {
	/** dao注入 */
	private ISmsDao smsDao;

	public void setSmsDao(ISmsDao smsDao) {
		this.smsDao = smsDao;
	}

	/**
	@Override
	public void afterPropertiesSet() throws Exception {
		// 注册字典
        CacheHelper.getInstance().registerConnector(SysSmsType.DIC_SMS_CONTENT, this);

	}
	 */

	/**
	 * 查询短信信息
	 * 
	 * @see com.transact.Sms.service#ISmsService(String id)
	 */
	public List<SysSmsType> selectAllSms(SmsQueryInfo info){
		return this.smsDao.selectAllSms(info);
	}

	/**
	 * 根据短信ID查询短信信息
	 * 
	 * @see com.transact.Sms.service#ISmsService(String id)
	 */
	public SysSmsType loadSmsById(String smsId) {
		Assert.notNull(smsId);
		return (SysSmsType) this.smsDao.load(SysSmsType.class,	smsId);
	}

	/**
	 * 保存短信信息
	 * 
	 * @see com.transact.Sms.service#ISmsService(com.transact.SmsEditInfo.command.SmsEditInfo)
	 */
	public SysSmsType saveSms(SmsEditInfo info) {
		Assert.notNull(info);
		SysSmsType entity = null;
		if (StringUtils.isNotBlank(info.getSmsId())) {
			entity = this.loadSmsById(info.getSmsId());
			BeanUtils.copyProperties(info, entity);
		} else {
			entity = new SysSmsType();
			BeanUtils.copyProperties(info, entity,new String[] { "smsId" });
			entity.setSmsId(SNHelper.getSNService().getSerialNumber(SysSmsType.class.getName(), "smsId", false));
		}
		this.smsDao.save(entity);

		// 更新字典
		CacheHelper.getInstance().dispatchRefreshEvent(SysSmsType.DIC_SMS_CONTENT);

		return entity;
	}

	/**
	 * 删除短信信息
	 * 
	 * @see com.transact.Sms.service#ISmsService(String[] id)
	 */
	public void deleteSms(String[] ids) {
		Assert.notNull(ids);
		for (String id : ids) {
			SysSmsType entity = (SysSmsType) this.smsDao.load(SysSmsType.class, id);
			this.smsDao.delete(entity);
		}
	}

	@Override
	public Object loadCacheObject(String objName) {
		if (SysSmsType.DIC_SMS_CONTENT.equals(objName)) {
			List<SysSmsType> list = this.selectAllSms();
			return DictionaryUtils.listToDictionaryItemMap(list, "smsType", "smsContent");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<SysSmsType> selectAllSms() {
		return this.smsDao.loadAll(SysSmsType.class, "", "", "");
	}

	@Override
	public List<SysSmsType> selectSmsTypeList(String smsType) {
		List smsList = this.smsDao.loadAll(SysSmsType.class, "smsType", smsType, "");
		return smsList;
	}

}
