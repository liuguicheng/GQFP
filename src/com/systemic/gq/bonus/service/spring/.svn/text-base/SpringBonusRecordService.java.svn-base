package com.systemic.gq.bonus.service.spring;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.orm.Page;

import com.systemic.gq.bonus.command.BonusRecordEdit;
import com.systemic.gq.bonus.command.BonusRecordInfo;
import com.systemic.gq.bonus.service.IBonusRecordServcie;
import com.systemic.gq.bonus.service.dao.IBonusRecordDao;
import com.systemic.gq.entity.BonusRecord;

public class SpringBonusRecordService implements IBonusRecordServcie {

	IBonusRecordDao bonusRecordDao;

	public void setBonusRecordDao(IBonusRecordDao bonusRecordDao) {
		this.bonusRecordDao = bonusRecordDao;
	}

	@Override
	public Page selectPageBonusRecord(BonusRecordInfo bonusrecordinfo) {
		return bonusRecordDao.selectPageStock(bonusrecordinfo);
	}

	@Override
	public void insertBonusRecor(BonusRecordEdit bonusrecordedit) {

		BonusRecordInfo bonusrecordinfo = new BonusRecordInfo();

		if (StringUtils.isNotBlank(bonusrecordedit.getId())) {
			BeanUtils.copyProperties(bonusrecordedit, bonusrecordinfo);
		} else {
			BeanUtils.copyProperties(bonusrecordedit, bonusrecordinfo, new String[] { "id" });
		}

		bonusRecordDao.save(bonusrecordinfo);

	}

	@Override
	public List<BonusRecord> selectBonusRecordByMember(BonusRecordInfo bonusrecordinfo) {
		return bonusRecordDao.selectBonusRecordByMember(bonusrecordinfo);

	}

	@Override
	public void updateBonusRecord(BonusRecord bonusrecord) {
		bonusRecordDao.update(bonusrecord);
	}

}
