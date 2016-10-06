package com.plugins.holiday.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HolidayUtil {

    /**
     * 获取一年中的指定的星期（从周一到周日有多少天）
     *
     * @param year
     * @param Weekday
     *            （0~6 0表示周日 6 表示周六）
     * @return
     */
    public List SunList(int year, int Weekday) {
        // 将周末日期保存到数组中

        List li = new ArrayList();

        int y = year; // 年份
        int w = Weekday; // 哪天算周末？6-0,0表示星期日 5表示星期5

        int m = 1, d;

        int e[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 每月有多少天
        boolean leap = y % 4 == 0 && y % 100 != 0 || y % 400 == 0; // 判断是否是闰年
        e[1] = leap ? 29 : 28;

        // 元旦星期几?
        d = (y + ((y - 1) >> 2) - (y - 1) / 100 + (y - 1) / 400) % 7;

        // 第一个周末几号
        if (d == w) {
            d = 1;
        } else {
            d = (w > d ? w - d : (w - d + 7)) + 1;
        }

        // 是否有第53个周末
        boolean add = (d == 1 || d <= 2 && leap);

        // 连续52个周末
        for (int i = 0; i < 52; ++i) {
            // System.out.println(i+1 + "   :   " + y + "- " + m + "- " + d);

            // 循环保存
            StringBuffer strbuf = new StringBuffer();
            strbuf.append(y); // 年
            if (String.valueOf(m).length() == 1) { // 月
                strbuf.append("0").append(m);
            } else {
                strbuf.append(m);
            }
            if (String.valueOf(d).length() == 1) {
                strbuf.append("0").append(d);
            } else {
                strbuf.append(d);
            }
            if(strbuf!=null){
              li.add(strbuf.toString());
            }

            d += 7;
            if (d > e[m - 1]) {
                d -= e[m - 1];
                ++m;
            }
        }

        if (add) { // 如果 有第53个周末
            // System.out.println(53 + "   :   " + y + "- " + 12 + "- " + d);
            StringBuffer strbuf = new StringBuffer();
            strbuf.append(y); // 年
            if (String.valueOf(m).length() == 1) { // 月
                strbuf.append("0").append(m);
            } else {
                strbuf.append(m);
            }
            if (String.valueOf(d).length() == 1) {
                strbuf.append("0").append(d);
            } else {
                strbuf.append(d);
            }

            if(strbuf!=null){
              li.add(strbuf);
            }
        }

        return li;

    }

    /**
     * 将字符串转换为日期
     *
     * @param str
     *            表示日期的字符串
     * @param strFormat
     *            转换格式
     * @return
     */
    public Date stringToDate(String str, String strFormat) {
        SimpleDateFormat sdFormat = new SimpleDateFormat(strFormat);
        Date date = new Date();
        try {
            date = sdFormat.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    /**
     * 将格式为2010-03-06 转换为0306 形式的字符 用于初始化节日
     *
     * @param str
     * @return
     */
    public String holidayStr(String str) {
        String[] strs = null;
        String temp = "";
        if (str != null && str.trim().length() > 0) {
            strs = str.split("-");
            if (strs.length == 3) {
                if(strs[1].length()==1){
                    strs[1]="0"+strs[1];
                }
                if(strs[2].length()==1){
                    strs[2]="0"+strs[2];
                }
                temp = strs[1] + strs[2];
            }
        }
        return temp;
    }
    /**
     * 将格式为2010-03-06 转换为0306 形式的字符 用于初始化节日
     * @param str
     * @return
     */
    public String holiday2Str(String str) {
        String[] strs = null;
        String temp = "";
        if (str != null && str.trim().length() > 0) {
            strs = str.split("-");
            if (strs.length == 3) {
                temp = strs[1] + strs[2];
            }
        }
        return temp;
    }

    /**
     * 将格式为2010-03-06 转换为2010-03-06 形式的字符
     *
     * @param str
     * @return
     */
    public String holidayDateStr(String str) {
        String[] strs = null;
        String temp = "";
        if (str != null && str.trim().length() > 0) {
            strs = str.split("-");
            if (strs.length == 3) {
                temp = strs[0] + "-" + strs[1] + "-" + strs[2];
            }
        }
        return temp;
    }

    /**
     * 查找最大日期
     *
     * @param li
     *            存放日期的时间集合
     * @return
     */
    public Date getMaxDate(List<Date> li) {
        Date max = null;
        if (li != null && li.size() > 0) {
            max = li.get(0);
            for (int i = 0; i < li.size(); i++) {
                if (max.getTime() < li.get(i).getTime()) {
                    max = li.get(i);
                }
            }
        }
        return max;
    }

    /**
     * 查找最小日期
     *
     * @param li
     *            存放日期的时间集合
     * @return
     */
    public Date getMinDate(List<Date> li) {
        Date min = null;
        if (li != null && li.size() > 0) {
            min = li.get(0);
            for (int i = 0; i < li.size(); i++) {
                if (min.getTime() > li.get(i).getTime()) {
                    min = li.get(i);
                }
            }
        }
        return min;
    }

    /**
     * 将格式为 20100306 的字符串转换为时间为2010-03-06日期形式
     *
     * @param str
     *            日期字符串
     * @return
     */
    public Date getDate(String str) {
        Date date = null;
        String temp = "";
        if (str != null && str.trim().length() > 0) {
            temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"
                    + str.substring(6);
            date = this.stringToDate(temp, "yyyy-MM-dd");
        }
        return date;
    }

    /**
     * 获取新历+非法定假期 节日的List集合( 每年都是固定的)
     *
     * @param year
     * @return
     */
    public Map getStaregnHolidays(int year) {
        Map map = new HashMap();
        map.put(year + "0214", "情人节");
        map.put(year + "0308", "妇女节");
        map.put(year + "0312", "植树节");
        map.put(year + "0315", "消费者权益日");
        map.put(year + "0317", "St. Patrick's");
        map.put(year + "0401", "愚人节");
        map.put(year + "0501", "劳动节");
        map.put(year + "0504", "青年节");
        map.put(year + "0512", "护士节");
        map.put(year + "0601", "儿童节");
        map.put(year + "0614", "Flag Day");
        map.put(year + "0701", "建党节 香港回归纪念");
        map.put(year + "0703", "炎黄在线诞辰");
        map.put(year + "0718", "托普诞辰");
        map.put(year + "0801", "建军节");
        map.put(year + "0808", "父亲节");
        map.put(year + "0909", "毛泽东逝世纪念");
        map.put(year + "0910", "教师节");
        map.put(year + "0928", "孔子诞辰");
        map.put(year + "1006", "老人节");
        map.put(year + "1024", "联合国日");
        map.put(year + "1111", "Veteran's / Remembrance Day");
        map.put(year + "1112", "孙中山诞辰纪念");
        map.put(year + "1220", "澳门回归纪念");
        map.put(year + "1225", "Christmas Day");
        map.put(year + "1226", "毛泽东诞辰纪念");
        return map;
    }

    /**
     * 新历 + 法定假期
     *
     * @param year
     * @return
     */
    public Map getstatutoryHolidays(int year) {
        Map map = new HashMap();
        //元旦
        map.put(year + "0101", "元旦");
        map.put(year + "0102", "元旦");
        map.put(year + "0103", "元旦");
        //清明节
        map.put(year + "0403", "清明节");
        map.put(year + "0404", "清明节");
        map.put(year + "0405", "清明节");
        //劳动节
        map.put(year + "0501", "劳动节");
        map.put(year + "0502", "劳动节");
        map.put(year + "0503", "劳动节");
        //国庆节
        map.put(year + "1001", "国庆节");
        map.put(year + "1002", "国庆节");
        map.put(year + "1003", "国庆节");
        map.put(year + "1004", "国庆节");
        map.put(year + "1005", "国庆节");
        map.put(year + "1006", "国庆节");
        map.put(year + "1007", "国庆节");
        return map;
    }

    /**
     * 获得农历+非法定 节日的list
     *
     * @param year
     * @return
     */
    public Map getStaregnHoliday(int year) {
        Map map = new HashMap();
        map.put(year + "0115", "元宵节");
        map.put(year + "0505", "端午节");
        map.put(year + "0707", "七夕情人节");
        map.put(year + "0715", "中元节");
        map.put(year + "0909", "重阳节");
        map.put(year + "1208", "腊八节");
        map.put(year + "1224", "小年");
        return map;

    }

    /**
     * 获得农历+法定 节日的list
     *
     * @param year
     * @return
     */
    public Map getstatutoryLunarHoliday(int year) {
        Map map = new HashMap();
        //春节
        //map.put(year + "0100", "除夕");
        map.put(year + "0101", "春节");
        map.put(year + "0102", "春节");
        map.put(year + "0103", "春节");
        map.put(year + "0104", "春节");
        map.put(year + "0105", "春节");
        map.put(year + "0106", "春节");
        //中秋节
        map.put(year + "0813", "中秋节");
        map.put(year + "0814", "中秋节");
        map.put(year + "0815", "中秋节");
        return map;
    }
    /**
     * 将 2010-1-2  转换为2010-01-02  便于数据库比较
     * @return
     */
    public String change(String str){
        if(str != null && str.trim().length() > 0){
            return "";
        }
        String temp="";
          String[] strs=str.split("-");
          StringBuffer sb=new StringBuffer();
          sb.append(strs[0]+"-");
          temp=strs[1];
          if(temp.length()<2){
             sb.append("0"+temp);
          }else{
              sb.append(temp);
          }
          sb.append("-");
          temp=strs[2];
          if(temp.length()<2){
              sb.append("0"+temp);
           }else{
               sb.append(temp);
           }
          return sb.toString();
    }
}
