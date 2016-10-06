package com.systemic.gq.pay.service.spring;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.orm.Page;

import com.console.ConsoleHelper;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.pay.command.PayInfoEdit;
import com.systemic.gq.pay.command.PayQueryInfo;
import com.systemic.gq.pay.service.ISpringPayService;
import com.systemic.gq.pay.service.dao.IPayDao;

public class SpringPayService implements ISpringPayService {
	private IPayDao payDao;

	public void setPayDao(IPayDao payDao) {
		this.payDao = payDao;
	}

	@Override
	public Page selectPay(PayQueryInfo info) {
		return this.payDao.selectPay(info);
	}

	/* (non-Javadoc) 申请b
	 * @see com.systemic.gq.pay.service.ISpringPayService#savePay(com.systemic.gq.pay.command.PayInfoEdit)
	 */
	@Override
	public void savePay(PayInfoEdit info,Member member) {
		PayInfo pi = null;
		if (StringUtils.isNotBlank(info.getNumberId())) {
			pi = this.selectPay(info.getNumberId());
		} else {
			pi = new PayInfo();
			BeanUtils.copyProperties(info, pi, new String[] { "numberId" });
			pi.setApplyTime(new Date());
			
		}
		pi.setApplyState(info.getApplyState());
		pi.setMemberId(member.getMemberId());
		pi.setMemnberStaffId(member.getStaffId());
		//金币计算
		Double money = member.getElectroniccurrency();
		
		Double afterMoney = null;
		if(money == null){
			afterMoney = info.getApplyRecordNum()+0.00;
			pi.setPayBeforeNum(0.00);//充值前的电子金币
		}else{
			pi.setPayBeforeNum(money);//充值前的电子金币
			afterMoney = money+info.getApplyRecordNum();
		}
		
		pi.setPayAfterNum(afterMoney);//充值后的金币
		pi.setApplyLocalHost(ConsoleHelper.getUserIp());
		this.payDao.save(pi);
		
	}

	@Override
	public PayInfo selectPay(String id) {
		return (PayInfo) this.payDao.load(PayInfo.class, id);
	}

	@Override
	public void updatePayInfo(PayInfo payinfo) {
		this.payDao.update(payinfo);
		
	}

}
