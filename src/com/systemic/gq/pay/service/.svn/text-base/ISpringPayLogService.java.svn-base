package com.systemic.gq.pay.service;

import org.springline.orm.Page;

import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.PayInfo;
import com.systemic.gq.entity.PayLog;
import com.systemic.gq.pay.command.PayLogQueryInfo;


public interface ISpringPayLogService {
	/**保存充值记录
	 * @param info
	 */
	void savePayLog(String logContent,double money,String operationType,String memberId);
	
	/**分页查询充值记录
	 * @param info
	 * @return
	 */
	Page selectPayLog(PayLogQueryInfo info);
	
	/**根据id获取一条记录
	 * @param id
	 * @return
	 */
	PayLog loadPayLog(String id);
}
