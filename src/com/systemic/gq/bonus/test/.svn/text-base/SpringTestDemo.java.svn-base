package com.systemic.gq.bonus.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.systemic.gq.bonus.service.IBonusRecordServcie;
import com.systemic.gq.entity.BonusRecord;

import junit.framework.TestCase;

public class SpringTestDemo extends TestCase {

	IBonusRecordServcie bonusRecordService;

	protected void setUp() throws Exception {
		ApplicationContext ac = new FileSystemXmlApplicationContext("dist/WEB-INF/config/applicationContext.xml");
		bonusRecordService = (IBonusRecordServcie) ac.getBean("bonusRecordService");
		System.out.println(bonusRecordService == null);
	}

	public void testListTest() {

		List<BonusRecord> clientList = bonusRecordService.selectBonusRecordByMember(null);
		Assert.assertEquals(clientList.size(), 10);
		for (BonusRecord c : clientList) {
			System.out.println(c.getMoney());
		}
	}
}
