package com.systemic.unit;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 工具类
 * 
 * @author lgc
 *
 */
public class ConUnit {

	// 换算两位小数
	public static double takePercentage(double a, double b) {
		// 这里的数后面加“D”是表明它是Double类型，否则相除的话取整，无法正常使用
		double percent = a / b;
		// 输出一下，确认你的小数无误
		System.out.println("小数：" + percent);
		// 获取格式化对象
		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);
		// 最后格式化并输出
		System.out.println("百分数：" + nt.format(percent));
		return percent;
	}

	// 转换字符串编码
	public static String toutf(String st) {
		String str = "";
		try {
			str = URLDecoder.decode(st, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return str;
	}

	// 判断当前日期是星期几
	public static int dayForWeek(Date newdate) {
		Calendar c = Calendar.getInstance();
		c.setTime(newdate);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	// 封装json
	public static String tojson(ErrorDataMsg ed) {
		List list = new ArrayList();
		list.add(ed);
		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);
		ed = null;
		list = null;
		g = null;
		return gsonString;
	}

	public static String formateDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameDate;
	}

	/**
	 * 
	 * @param qy
	 *            区域
	 * @param cen
	 *            显示多少层
	 */
	public static void CalculationTable(int qy, int cen) {
		// 传回x的y次幂值 求出最大个数 ：区域的层次幂
		double totalTd = Math.pow(qy, (cen - 1));
		// System.out.println("当前区域：" + qy + ",一共" + cen + "层。最大格数：" + totalTd);
		// 去除前端页面table循环次数（层数*区域减去最后一层）
		//int num = (cen * 2 - 1);
		// System.out.println("table 循环tr行数：" + num);
		// 第一层、第二层的td 跨度基本固定，不用计算
		// System.out.println("第1层td跨度：" + totalTd + ",格数：1");
		// System.out.println("第2层td跨度：" + (totalTd / qy) + ",格数：" + qy);
		// 每层 td 跨度
		// for (int i = 3; i <= cen; i++) {
		// if (i == cen) {
		// System.out.println("第" + i + "层td跨度：1,格数：" + Math.pow(qy, (i - 1)));
		// } else {
		// System.out.println("第" + i + "层td跨度：" + Math.floor((totalTd / i)) +
		// ",格数：" + Math.pow(qy, (i - 1)));
		// }
		// }
	}

	public static void main(String[] args) {
		CalculationTable(3, 3);
	}
}
