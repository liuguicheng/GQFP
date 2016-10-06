package com.systemic.basic.sms.support;

public class Tools
{

	/**
	 * 电话号码长度，11位
	 */
	private static final Integer NUM_LENGTH = 11;
	/**
	 * 号码来源标示前缀长度，3位
	 */
	private static final Integer NUM_PREFIX_LENGTH = 3;

	public Tools()
	{
	}

	/**
	 * 大概检查手机号码是否是11位数字，并是否以13开头
	 * @param sMobile String   传入的手机号码
	 * @return boolean         true符合规范
	 */
	public static boolean checkMobile(String sMobile) {
		if (sMobile == null) {
			return false;
		}
		if (sMobile.length() != 11) {
			return false;
		}
		return true;
	}


	/**
	 * 取得手机所属的运营商
	 * @param mobileNum 电话号码
	 * @return String (0移动,1联通,2模拟,3电信)
	 */
	public static String  getMobileType(String mobileNum)
	{
		if (mobileNum == null || mobileNum.length() == 0||mobileNum.length() != 11)
		{
			return null;
		}

		if (isMoblie(mobileNum))
			return "0";
		else if (isUnicom(mobileNum))
			return "1";
		else if (isTelcom(mobileNum))
			return "3";
		else if (mobileNum.substring(0,3).equals("999"))
			return "2";
		else
			return null;
	}

	/**
	 * 判断是否为移动的手机号码
	 * @param num 电话号码
	 * @return boolean
	 */
	public static boolean isMoblie(String num) {
		if (num == null || num.length() != NUM_LENGTH) {
			return false;
		}
		String mobilePrefix = "134,135,136,137,138,139,147,150,151,152,154,157,158,159,182,183,187,188";//BaseConfig.getInstance().getParamterValue("号码前缀", "移动", "");
		if (mobilePrefix == null || mobilePrefix.trim().length() == 0) {
			return false;
		}
		mobilePrefix = "," + mobilePrefix + ",";
		return mobilePrefix.indexOf(num.substring(0, NUM_PREFIX_LENGTH)) >= 0;

	}

	/**
	 * 判断是否为联通的手机号码
	 * @param num 电话号码
	 * @return boolean
	 */
	public static boolean isUnicom(String num)  {
		if (num == null || num.length() != NUM_LENGTH) {
			return false;
		}
		String mobilePrefix = "130,131,132,145,155,156,185,186";//BaseConfig.getInstance().getParamterValue("号码前缀", "联通", "");
		if (mobilePrefix == null || mobilePrefix.trim().length() == 0) {
			return false;
		}
		mobilePrefix = "," + mobilePrefix + ",";
		return mobilePrefix.indexOf(num.substring(0, NUM_PREFIX_LENGTH)) >= 0;
	}

	/**
	 * 判断电话号码是否为电信的号码
	 * @param num 电话号码
	 * @return boolean
	 */
	public static boolean isTelcom(String num) {
		if (num == null || num.length() != NUM_LENGTH) {
			return false;
		}
		String mobilePrefix = "133,153,180,189";// BaseConfig.getInstance().getParamterValue("号码前缀", "电信", "");
		if (mobilePrefix == null || mobilePrefix.trim().length() == 0) {
			return false;
		}
		mobilePrefix = "," + mobilePrefix + ",";
		return mobilePrefix.indexOf(num.substring(0, NUM_PREFIX_LENGTH)) >= 0;
	}


}
