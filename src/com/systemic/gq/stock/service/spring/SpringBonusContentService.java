package com.systemic.gq.stock.service.spring;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.beans.cache.ICacheConnector;
import org.springline.orm.Page;

import com.systemic.gq.entity.BonusContent;
import com.systemic.gq.stock.command.BonusContentEdit;
import com.systemic.gq.stock.command.BonusContentInfo;
import com.systemic.gq.stock.service.IBonusContentService;
import com.systemic.gq.stock.service.dao.IBonusContentDao;

public class SpringBonusContentService implements IBonusContentService {

	private IBonusContentDao bonuscontentdao;
	
	public void setBonuscontentdao(IBonusContentDao bonuscontentdao) {
		this.bonuscontentdao = bonuscontentdao;
	}

	@Override
	public Page selectPageBonusConten(BonusContentInfo bcinfo) {
		// TODO Auto-generated method stub
		return bonuscontentdao.selectPageBonusConten(bcinfo);
	}

	@Override
	public void insertBonusConten(BonusContentEdit bonuscontnet) {
		BonusContent newbonuscon=new BonusContent();
		if(StringUtils.isNotBlank(bonuscontnet.getId())){
		   BeanUtils.copyProperties(bonuscontnet, newbonuscon);
		}else{
		   BeanUtils.copyProperties(bonuscontnet, newbonuscon,new String[]{"id"});
		}
		bonuscontentdao.save(newbonuscon);
	}

	@Override
	public void deleteBonusContent(String ids[]) {
		for (String id : ids) {
			BonusContent zsbcont=this.selectBonusContentByid(id);
			if(zsbcont!=null){
				bonuscontentdao.delete(zsbcont);
				zsbcont=null;
			}
			
		}
		
	}

	@Override
	public BonusContent selectBonusContentByid(String id) {
		return (BonusContent) bonuscontentdao.load(BonusContent.class, id);
	}

	@Override
	public List selectBonusContentList(BonusContentInfo bcinfo) {
		// TODO Auto-generated method stub
		return bonuscontentdao.selectBonusContent(bcinfo);
	}


}
