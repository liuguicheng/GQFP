package com.systemic.gq.bonus.settlement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.console.ConsoleHelper;
import com.systemic.gq.bonus.command.BonusRecordEdit;
import com.systemic.gq.bonus.command.BonusRecordInfo;
import com.systemic.gq.entity.BonusContent;
import com.systemic.gq.entity.BonusRecord;
import com.systemic.gq.entity.Member;
import com.systemic.gq.entity.Stock;
import com.systemic.gq.member.command.MemberInfo;

public abstract class SettlementHelper {

	// 每日奖金结算
	public static void doBonusSettlementForEveryDay(Member member) {

	}

	// 单个奖金结算-静态
	public static void doBonusSettlementForStatic(Member member) {
		if (member != null) {
			Stock stock = member.getStock();
			if (stock != null) {
				// 获取股权等级的奖励方案的字符串
				String bonusContentStr = stock.getBonusContentID() == null ? "" : stock.getBonusContentID();
				// 根据用户 股权等级的奖励方案的字符串，查询奖励方案
				List<BonusContent> bonusContentList = queryBonusContentListForUser(bonusContentStr);

				if (!bonusContentList.isEmpty()) {
					BonusContent helpBonusContent = null;
					BonusContent staticBonusContent = null;
					for (BonusContent bonusContent : bonusContentList) {
						switch (bonusContent.getBonusType()) {
						case "01":// 静态奖
							staticBonusContent = bonusContent;
							break;
						case "03":// 互助奖
							helpBonusContent = bonusContent;
							break;
						}
					}
					// 静态奖
					staticBonus(member, stock.getMoney(), helpBonusContent, staticBonusContent);
				}
			}
		}
	}

	// 静态奖 -定时发放
	public static void settlementStaticBonus() {
		MemberInfo info = new MemberInfo();
		info.setCreateTime(new Date());
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		if (memberlist != null) {
			for (Member member : memberlist) {
				double staticBonusMoney = staticBonusForThirtyDay(member);
				if (staticBonusMoney > 0) {

					upMemberGoldAward(member, staticBonusMoney);
				}

			}
		}
	}

	
	// 单个奖金结算-推荐
	public static void doBonusSettlementForTJ(Member member, Member currentMmenber) {
		if (member != null) {
			Stock stock = member.getStock();
			if (stock != null) {
				// 获取股权等级的奖励方案的字符串
				String bonusContentStr = stock.getBonusContentID() == null ? "" : stock.getBonusContentID();
				// 根据用户 股权等级的奖励方案的字符串，查询奖励方案
				List<BonusContent> bonusContentList = queryBonusContentListForUser(bonusContentStr);

				if (!bonusContentList.isEmpty()) {
					BonusContent helpBonusContent = null;
					BonusContent tjBonusContent = null;
					for (BonusContent bonusContent : bonusContentList) {
						switch (bonusContent.getBonusType()) {
						case "02":// 推荐奖
							tjBonusContent = bonusContent;
							break;
						case "03":// 互助奖
							helpBonusContent = bonusContent;
							break;
						}
					}
					// 推荐奖
					tjBonus(member, currentMmenber, stock.getMoney(), helpBonusContent, tjBonusContent);
				}
			}
		}
	}

	// 单个奖金结算-对碰
	public static void doBonusSettlementForDP(Member member, Member currentMmenber) {
		if (member != null) {
			Stock stock = member.getStock();
			if (stock != null) {
				// 获取股权等级的奖励方案的字符串
				String bonusContentStr = stock.getBonusContentID() == null ? "" : stock.getBonusContentID();
				// 根据用户 股权等级的奖励方案的字符串，查询奖励方案
				List<BonusContent> bonusContentList = queryBonusContentListForUser(bonusContentStr);

				if (!bonusContentList.isEmpty()) {
					BonusContent helpBonusContent = null;
					BonusContent dpBonusContent = null;
					for (BonusContent bonusContent : bonusContentList) {
						switch (bonusContent.getBonusType()) {
						case "04":// 对碰奖
							dpBonusContent = bonusContent;
							break;
						case "03":// 互助奖
							helpBonusContent = bonusContent;
							break;
						}
					}
					// 对碰
					dpBonus(member, currentMmenber, stock.getMoney(), helpBonusContent, dpBonusContent);
				}
			}
		}
	}

	/**
	 * 一次性奖金结算 member 被推荐的会员 currentMmenber 推荐人
	 */
	public static void doBonusSettlementForDisposable(Member member, Member currentMmenber) {
		if (member != null) {
			Stock stock = member.getStock();
			if (stock != null) {
				// 获取股权等级的奖励方案的字符串
				String bonusContentStr = stock.getBonusContentID() == null ? "" : stock.getBonusContentID();
				// 根据用户 股权等级的奖励方案的字符串，查询奖励方案
				List<BonusContent> bonusContentList = queryBonusContentListForUser(bonusContentStr);

				if (!bonusContentList.isEmpty()) {
					BonusContent helpBonusContent = null;
					BonusContent staticBonusContent = null;
					BonusContent dpBonusContent = null;
					BonusContent tjBonusContent = null;
					for (BonusContent bonusContent : bonusContentList) {
						switch (bonusContent.getBonusType()) {
						case "01":// 静态奖
							staticBonusContent = bonusContent;
							break;
						case "02":// 推荐奖
							tjBonusContent = bonusContent;
							break;
						case "03":// 互助奖
							helpBonusContent = bonusContent;
							break;
						case "04":// 对碰奖
							dpBonusContent = bonusContent;
							break;
						}
					}
					// 静态奖
					staticBonus(member, stock.getMoney(), helpBonusContent, staticBonusContent);
					// 推荐奖
					tjBonus(member, currentMmenber, stock.getMoney(), helpBonusContent, tjBonusContent);
					// 对碰
					dpBonus(member, currentMmenber, stock.getMoney(), helpBonusContent, dpBonusContent);

				}

			}
		}

	}

	// ##################################################以下内部方法#####################################################//
	/**
	 * 对碰
	 * 
	 * @param member
	 *            注册会员
	 * @param currentMmenber
	 *            推荐人
	 * @param stockMoney
	 * @param helpBonusContent
	 * @param dpBonusContent
	 */
	private static void dpBonus(Member member, Member currentMmenber, double stockMoney, BonusContent helpBonusContent,
			BonusContent dpBonusContent) {
		double bonusMoney;
		/**
		 * 规则 1：1 以小区业绩的百分比计算 步骤：
		 * 1.对应区域推荐人是否一致,如注册人为左区，需找同推荐人初自己外的左区和右区人员，有则算；右区同理 2.以金额小方，为获取奖金参数基准
		 * 3.必须在同一层
		 */
		// 注册用户区域
		String region = member.getRegion();
		// 同层、同区、同上一个节点
		Member regionMember = selectMemberByRegion(member, currentMmenber);
		if (regionMember != null) {
			double money = stockMoney;
			if (regionMember.getStock().getMoney() < money) {
				money = regionMember.getStock().getMoney();
			}
			// 结算
			bonusMoney = saveBonusRecord(currentMmenber, money, dpBonusContent, 1);
			// 修改会员奖金总额
			upMemberGoldAward(currentMmenber, bonusMoney);
			// 互助
			settlementHelpBonusMoney(currentMmenber, bonusMoney, helpBonusContent);
			bonusMoney = 0;
		}
		// 父节点另一区域下对应子节点
		Member parentNodeMember = selectMemberByParentNode(region, currentMmenber);
		if (parentNodeMember != null) {
			Member zNodeMember = selectMemberByNode(region, parentNodeMember);
			if (zNodeMember != null) {
				double money = stockMoney;
				if (zNodeMember.getStock().getMoney() < money) {
					money = zNodeMember.getStock().getMoney();
				}
				// 结算
				bonusMoney = saveBonusRecord(currentMmenber, money, dpBonusContent, 1);
				// 修改会员奖金总额
				upMemberGoldAward(currentMmenber, bonusMoney);
				// 互助
				settlementHelpBonusMoney(currentMmenber, bonusMoney, helpBonusContent);
				bonusMoney = 0;
			}
		}
	}

	/**
	 * 推荐奖
	 * 
	 * @param member
	 *            注册用户
	 * @param currentMmenber
	 *            注册用户推荐人
	 * @param stockMoney
	 *            注册用户股权金额
	 * @param helpBonusContent
	 * @param tjBonusContent
	 */
	private static void tjBonus(Member member, Member currentMmenber, double stockMoney, BonusContent helpBonusContent,
			BonusContent tjBonusContent) {
		double bonusMoney;
		// 推荐
		bonusMoney = saveBonusRecord(currentMmenber, stockMoney, tjBonusContent, 1);
		// 修改会员奖金总额
		upMemberGoldAward(currentMmenber, bonusMoney);
		// 互助
		settlementHelpBonusMoney(member, bonusMoney, helpBonusContent);
		bonusMoney = 0;
	}

	/**
	 * 静态奖
	 * 
	 * @param member
	 *            获得用户
	 * @param stockMoney
	 *            用户股权金额
	 * @param helpBonusContent
	 * @param staticBonusContent
	 */
	private static void staticBonus(Member member, double stockMoney, BonusContent helpBonusContent,
			BonusContent staticBonusContent) {
		double bonusMoney;
		// 静态
		if (selectDay(new Date(), member.getCreateTime())) {
			// 记录奖金 并记录 状态为未发放
			bonusMoney = saveBonusRecord(member, stockMoney, staticBonusContent, 0);

			// 修改会员奖金总额 由每个月30号系统定时任务统一发放

			// 互助
			settlementHelpBonusMoney(member, bonusMoney, helpBonusContent);
			bonusMoney = 0;
		}
	}

	/**
	 * 根据会员股权等级，获取该等级的奖励方案
	 * 
	 * @param bonusContentStr
	 *            会员的股权等级的奖励方案-（集合）
	 * @return
	 */
	private static List<BonusContent> queryBonusContentListForUser(String bonusContentStr) {
		List<BonusContent> bonusContentList = null;
		if (StringUtils.isNotBlank(bonusContentStr)) {
			String[] sz = bonusContentStr.split(",");
			BonusContent bonuscontent = null;
			bonusContentList = new ArrayList<BonusContent>();
			for (String szstr : sz) {
				bonuscontent = ConsoleHelper.getInstance().getBonuscontentService().selectBonusContentByid(szstr);
				bonusContentList.add(bonuscontent);
			}
			bonuscontent = null;
		}
		return bonusContentList;
	}

	private static void upMemberGoldAward(Member member, double bonusMoney) {
		if (member != null) {
			member.setGoldAward(member.getGoldAward() + bonusMoney);
			ConsoleHelper.getInstance().getSpringMemberService().updateMermber(member);
		}
	}

	/**
	 * 结算 互助奖
	 * 
	 * @param member
	 *            注册用户
	 * @param bonusMoney
	 *            收入金额
	 * @param bonusContentid
	 *            注册用户等级的 奖励规则id
	 */
	private static void settlementHelpBonusMoney(Member member, double bonusMoney, BonusContent bonusContent) {

		int begin = bonusContent.getBegin();
		int end = bonusContent.getEnd();
		Member newmember = selectMemberByNote(member.getNote());
		System.out.println("上一节点用户：" + newmember.getUserName());
		for (int i = 1; i < begin - 1; i++) {
			newmember = selectMemberByNote(newmember.getNote());
			System.out.println("上一节点用户：" + newmember.getUserName());
		}
		// 计算 互助奖
		double helpBonusMoney = bonusMoney * bonusContent.getProportion();
		// 添加互助奖记录
		BonusRecordEdit bre = new BonusRecordEdit(helpBonusMoney, newmember.getMemberId(), newmember.getUserName(),
				new Date(), bonusContent.getBonusType(), bonusContent.getProportion(), "", 0);
		ConsoleHelper.getInstance().getBonusRecordService().insertBonusRecor(bre);
		// 修改会员奖金币
		upMemberGoldAward(newmember, helpBonusMoney);
		helpBonusMoney = 0;

		for (int j = 1; j < end - begin; j++) {
			newmember = selectMemberByNote(newmember.getNote());
			System.out.println("上一节点用户：" + newmember.getUserName());
			// 计算 互助奖
			helpBonusMoney = bonusMoney * bonusContent.getProportion();
			// 添加互助奖记录
			bre = new BonusRecordEdit(helpBonusMoney, newmember.getMemberId(), newmember.getUserName(), new Date(),
					bonusContent.getBonusType(), bonusContent.getProportion(), "", 0);
			ConsoleHelper.getInstance().getBonusRecordService().insertBonusRecor(bre);
			// 修改会员奖金币
			upMemberGoldAward(newmember, helpBonusMoney);
		}
	}

	/**
	 * 根据节点查询 会员
	 */
	private static Member selectMemberByNote(String note) {
		MemberInfo info = new MemberInfo();
		info.setNote(note);
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		if (memberlist != null) {
			return memberlist.get(0);
		}
		return null;
	}

	/**
	 * 查询父节点的另外一边对应区域节点
	 * 
	 * @param member
	 *            注册会员
	 * @param currentMmenber
	 *            推荐人
	 * @return
	 */
	private static Member selectMemberByRegion(Member member, Member currentMmenber) {
		String region = "";
		if (member.getRegion().equals("0")) {
			region = "1";
		} else if (member.getRegion().equals("1")) {
			region = "0";
		}
		MemberInfo info = new MemberInfo();
		info.setReferenceId(currentMmenber.getMemberId());
		info.setRegion(region);
		info.setNote(member.getNote());
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		if (memberlist != null) {
			return memberlist.get(0);
		}
		return null;
	}

	/**
	 * 查询父节点的父节点
	 * 
	 * @param member
	 *            注册会员
	 * @param currentMmenber
	 *            推荐人
	 * @return
	 */
	private static Member selectMemberByParentNode(String region, Member currentMmenber) {
		if (region.equals("0")) {
			region = "1";
		} else if (region.equals("1")) {
			region = "0";
		}
		MemberInfo info = new MemberInfo();
		info.setMemberId(currentMmenber.getReferenceId());
		info.setRegion(region);
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		if (memberlist != null) {
			return memberlist.get(0);
		}
		return null;
	}

	/**
	 * 查询父节点下子节点的
	 * 
	 * @param member
	 *            注册会员
	 * @param currentMmenber
	 *            推荐人
	 * @return
	 */
	private static Member selectMemberByNode(String region, Member member) {
		MemberInfo info = new MemberInfo();
		info.setNote(member.getMemberId());
		info.setRegion(region);
		List<Member> memberlist = ConsoleHelper.getInstance().getSpringMemberService().selectMemberBy(info);
		if (memberlist != null) {
			return memberlist.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @param member
	 *            会员
	 * @param stockMoney
	 *            股权金额
	 * @param bonusContent
	 *            股权奖励方案
	 * @param sendType
	 *            奖金发放标识 0 未发放 1已发放
	 * @return
	 */
	private static double saveBonusRecord(Member member, double stockMoney, BonusContent bonusContent, int sendType) {

		String bonustype = bonusContent.getBonusType();
		double bonusMoney = stockMoney * bonusContent.getProportion();
		BonusRecordEdit bonusRecordEdit = new BonusRecordEdit(bonusMoney, member.getMemberId(), member.getUserName(),
				new Date(), bonustype, bonusContent.getProportion(), "", sendType);
		ConsoleHelper.getInstance().getBonusRecordService().insertBonusRecor(bonusRecordEdit);
		return bonusMoney;
	}

	// 静态奖 结算 ，修改记录状态 结算总体金额，添加会员奖金币
	private static double staticBonusForThirtyDay(Member member) {
		double moeny = 0;
		// 奖金到达方法时间，则修改未发放状态，算取总数，更新用户奖金币
		BonusRecordInfo bri = new BonusRecordInfo();
		bri.setIsSend(0);
		bri.setUserid(member.getMemberId());
		List<BonusRecord> brlist = ConsoleHelper.getInstance().getBonusRecordService().selectBonusRecordByMember(bri);
		if (brlist != null) {
			for (BonusRecord bonusRecord : brlist) {
				moeny += bonusRecord.getMoney();
				bonusRecord.setIsSend(1);
				bonusRecord.setSend_time(new Date());
				ConsoleHelper.getInstance().getBonusRecordService().updateBonusRecord(bonusRecord);
			}
			return moeny;
		}
		return moeny;
	}

	// 比较时间天数位 是否相同
	private static boolean selectDay(Date newdate, Date userDate) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(newdate);
		int day = ca.get(Calendar.DAY_OF_MONTH);
		ca = Calendar.getInstance();
		ca.setTime(userDate);
		int userday = ca.get(Calendar.DAY_OF_MONTH);

		if (day == userday) {
			return true;
		}
		return false;
	}
}
