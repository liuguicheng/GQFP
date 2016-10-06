package com.systemic.gq.stock.service.spring;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.systemic.gq.entity.Rule;
import com.systemic.gq.stock.command.RuleEdit;
import com.systemic.gq.stock.service.IRuleService;
import com.systemic.gq.stock.service.dao.IRuleDao;

public class SpringRuleService implements IRuleService {

	private IRuleDao ruledao;

	public void setRuledao(IRuleDao ruledao) {
		this.ruledao = ruledao;
	}

	@Override
	public void insertStock(RuleEdit ruleedit) {
		Rule rule=new Rule();
		if(StringUtils.isNotBlank(ruleedit.getId())){
			BeanUtils.copyProperties(ruleedit,rule);
		}else{
			BeanUtils.copyProperties(ruleedit,rule,new String[]{"id"});
		}
		ruledao.save(rule);
		
	}

	@Override
	public Rule selectRule(String id) {
		return (Rule) ruledao.load(Rule.class, id);
	}

	@Override
	public Rule selectRuleBY() {
		// TODO Auto-generated method stub
		return ruledao.selectRuleBY();
	}
	
}
