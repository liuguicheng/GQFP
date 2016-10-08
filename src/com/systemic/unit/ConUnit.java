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
 * @author lgc
 *
 */
public class ConUnit {
	
	//换算两位小数
	public static double takePercentage(double a ,double b){
		//这里的数后面加“D”是表明它是Double类型，否则相除的话取整，无法正常使用
		   double percent = a/ b;
		   //输出一下，确认你的小数无误
		   System.out.println("小数：" + percent);
		   //获取格式化对象
		   NumberFormat nt = NumberFormat.getPercentInstance();
		   //设置百分数精确度2即保留两位小数
		   nt.setMinimumFractionDigits(2);
		   //最后格式化并输出
		   System.out.println("百分数：" + nt.format(percent));
		   return percent;
	} 
	
	//转换字符串编码
	public static String toutf(String st){
		String str="";
		try {
			str=URLDecoder.decode(st,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return str;
	}
	//判断当前日期是星期几
	public static int dayForWeek(Date newdate)  {
		 Calendar c = Calendar.getInstance();  
		 c.setTime(newdate);  
		 int dayForWeek = 0;  
		 if(c.get(Calendar.DAY_OF_WEEK) == 1){  
		  dayForWeek = 7;  
		 }else{  
		  dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
		 }  
		 return dayForWeek;  
	}
	//封装json 
	public static String tojson(ErrorDataMsg ed){
		List list=new ArrayList();
		list.add(ed);
		Gson g = (new GsonBuilder()).create();
		String gsonString = g.toJson(list);
		ed=null;
		list=null;
		g=null;
		return gsonString;
	}
	
	public static String formateDateToString(Date date ){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	
  
	
	public static void main(String[] args){
		
			
	}
}
