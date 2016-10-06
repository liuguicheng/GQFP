package com.systemic.gq.pay.service;

import org.springline.orm.Page;

import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.pay.command.PayInfoEdit;
import com.systemic.gq.pay.command.PayQueryInfo;

public interface ISpringPayService {
	/**分页查询申请*/
	Page selectPay(PayQueryInfo info);
	/**保存申请*/
	void savePay(PayInfoEdit info,Member member);
	/**根据id获取一条申请数据*/
	PayInfo selectPay(String id);
	void updatePayInfo(PayInfo payinfo);
}
