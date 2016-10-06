package com.systemic.gq.stock.service;

import com.systemic.gq.entity.Rule;
import com.systemic.gq.stock.command.RuleEdit;

public interface IRuleService {

	void insertStock(RuleEdit ruleedit);

	Rule selectRule(String id);

	Rule selectRuleBY();
}
