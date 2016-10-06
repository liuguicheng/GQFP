package com.systemic.gq.stock.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.entity.Rule;
import com.systemic.gq.entity.Stock;
import com.systemic.gq.stock.command.RuleEdit;
import com.systemic.gq.stock.service.dao.IRuleDao;

public class HinbernateRuleDao extends HibernateCommonDao implements IRuleDao {

	@Override
	public Rule selectRuleBY() {
		List ruleList = super.doQuery("from " + Rule.class.getName());
		if (ruleList.isEmpty()) {
			return null;
		}
		return (Rule) ruleList.get(0);
	}


}
