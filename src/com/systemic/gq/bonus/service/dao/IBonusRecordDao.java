package com.systemic.gq.bonus.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.systemic.gq.bonus.command.BonusRecordInfo;
import com.systemic.gq.entity.BonusRecord;

public interface IBonusRecordDao extends ICommonDao {

	Page selectPageStock(BonusRecordInfo bonusrecordinfo);

	List<BonusRecord> selectBonusRecordByMember(BonusRecordInfo bonusrecordinfo);
}
