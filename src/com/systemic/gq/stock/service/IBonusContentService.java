package com.systemic.gq.stock.service;

import java.util.List;

import org.springline.orm.Page;

import com.systemic.gq.entity.BonusContent;
import com.systemic.gq.stock.command.BonusContentEdit;
import com.systemic.gq.stock.command.BonusContentInfo;

public interface IBonusContentService {

	Page selectPageBonusConten(BonusContentInfo bcinfo);
	
	void insertBonusConten(BonusContentEdit bonuscontnet);

	void deleteBonusContent(String[] ids);
	
	BonusContent selectBonusContentByid(String id);
	
	List selectBonusContentList(BonusContentInfo bcinfo);
	
}
