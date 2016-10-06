package com.systemic.basic.sms.service.dao.hibernate;

import java.util.List;

import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.basic.sms.command.SmsQueryInfo;
import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.service.dao.ISmsDao;

public class HibernateSmsDao extends HibernateCommonDao implements ISmsDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SysSmsType> selectAllSms(SmsQueryInfo info) {
	    Object[] values = new Object[5];
        int idx = 0;
		StringBuffer hql = new StringBuffer(" ");
		hql.append("From SysSmsType as sms");
		if(info.getSmsType() != null && info.getSmsType().trim().length() > 0){
			hql.append(" where sms.smsType=?" );
			values[idx++] = info.getSmsType();
		}
	    Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
		return super.doQuery(hql.toString(),param);
	}

	
}
