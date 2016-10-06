package com.systemic.unit;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConUnit {
	
	//������λС��
	public static double takePercentage(double a ,double b){
		//�����������ӡ�D���Ǳ�������Double���ͣ���������Ļ�ȡ�����޷�����ʹ��
		   double percent = a/ b;
		   //���һ�£�ȷ�����С������
		   System.out.println("С����" + percent);
		   //��ȡ��ʽ������
		   NumberFormat nt = NumberFormat.getPercentInstance();
		   //���ðٷ�����ȷ��2��������λС��
		   nt.setMinimumFractionDigits(2);
		   //����ʽ�������
		   System.out.println("�ٷ�����" + nt.format(percent));
		   return percent;
	} 
	
	//ת���ַ�������
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
	//�жϵ�ǰ���������ڼ�
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
	
	public static void main(String[] args){
		
			System.out.println("1,2,3,4,5,".indexOf("1"));
	}
}