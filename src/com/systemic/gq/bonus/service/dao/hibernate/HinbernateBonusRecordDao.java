package com.systemic.gq.bonus.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.bonus.command.BonusRecordInfo;
import com.systemic.gq.bonus.service.dao.IBonusRecordDao;
import com.systemic.gq.entity.BonusRecord;
import com.systemic.gq.entity.Member;

public class HinbernateBonusRecordDao extends HibernateCommonDao implements IBonusRecordDao {

	private IQueryStringUtil bonusRecordQueryStringUtil;
	
	
	public void setBonusRecordQueryStringUtil(IQueryStringUtil bonusRecordQueryStringUtil) {
		this.bonusRecordQueryStringUtil = bonusRecordQueryStringUtil;
	}


	@Override
	public Page selectPageStock(BonusRecordInfo bonusrecordinfo) {
		IQueryObject io = this.bonusRecordQueryStringUtil.getQueryObject(bonusrecordinfo);
		if (bonusrecordinfo.getNotPage() != null && bonusrecordinfo.getNotPage().booleanValue()) {
            List data = super.doQuery(io.getQueryString(), io.getParam());
            return super.putDataToPage(data);
        }
		return super.find(io.getQueryString(), io.getParam(),
				bonusrecordinfo.getPageNumber(), bonusrecordinfo.getPageSize());
	}


	public List<BonusRecord> selectBonusRecordByMember(BonusRecordInfo bonusrecordinfo) {
		Object[] values = new Object[5];
        int idx = 0;
        StringBuffer queryStr = new StringBuffer();
        queryStr.append("from ").append(BonusRecord.class.getName()).append(
            " as m where 1=1 ");
        if(bonusrecordinfo!=null){
			if(bonusrecordinfo.getUserid()!=null&&!"".equals(bonusrecordinfo.getUserid())){
				 queryStr.append(" and m.userid = ? ");
	             values[idx++] = bonusrecordinfo.getUserid() ;
			}
			if(bonusrecordinfo.getIsSend()!=null&&!"".equals(bonusrecordinfo.getIsSend())){
				 queryStr.append(" and m.isSend = ? ");
	             values[idx++] = bonusrecordinfo.getIsSend() ;
			}
			
		}
        Object[] param = new Object[idx];
        System.arraycopy(values, 0, param, 0, idx);
        List list= super.doQuery(queryStr.toString(),param);
	if(!list.isEmpty()){
		return  list;
	}
	return null;
		
	}

}
