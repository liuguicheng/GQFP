package com.systemic.gq.stock.service.dao.hibernate;


import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.entity.BonusContent;
import com.systemic.gq.entity.Member;
import com.systemic.gq.stock.command.BonusContentEdit;
import com.systemic.gq.stock.command.BonusContentInfo;
import com.systemic.gq.stock.service.dao.IBonusContentDao;

public class HinbernateBonusContentDao extends HibernateCommonDao implements IBonusContentDao {

	
	private IQueryStringUtil bonuscontentQueryStringUtil;
	

	public void setBonuscontentQueryStringUtil(IQueryStringUtil bonuscontentQueryStringUtil) {
		this.bonuscontentQueryStringUtil = bonuscontentQueryStringUtil;
	}



	public Page selectPageBonusConten(BonusContentInfo bcinfo) {
		IQueryObject io = this.bonuscontentQueryStringUtil.getQueryObject(bcinfo);
		return super.find(io.getQueryString(), io.getParam(),
				bcinfo.getPageNumber(), bcinfo.getPageSize());
	}



	@Override
	public List selectBonusContent(BonusContentInfo bcinfo) {
		if(bcinfo!=null){
			
		}
		List bonusContentlist=super.doQuery("from "+BonusContent.class.getName());
		if(bonusContentlist.isEmpty()){
			return null;
		}
		return bonusContentlist;
	}




}
