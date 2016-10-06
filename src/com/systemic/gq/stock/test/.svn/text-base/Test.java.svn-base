package com.systemic.gq.stock.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import com.systemic.gq.entity.BonusContent;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String sr="1,2,3,4,5";
		//doTOUserBonusContentlist();
		//doStringConvertList(sr);
//		Integer[] array1 = {1,2,3,4,1,2,4,6,7,8,10,22,33};  
//        
//        Integer[] array2 = {1,2,3,4,1,2,4,6,7,8,10,22,33,55,66,77,88,99};  
//          
//        Set<Integer> sameElementSet = getid(array1,array2);  
//          
//        for(Integer i : sameElementSet) {  
//          
//        System.out.println(i);  
		//System.out.println(isLastDayOfMonth(new Date()));
//        }
		selectDay();
	}
	
	private static List  doStringConvertList(String bonusContentString){
		ArrayList<String> arrayList=null;
		if(bonusContentString!=null&&!"".equals(bonusContentString)){
		 	String rest[]=	bonusContentString.split(",");
		 	if(rest.length>0){
//		 		for (String string : rest) {
//			 		bonusContentStr.add(string);
//				}
		 		arrayList = new ArrayList<String>(Arrays.asList(rest));
		 		System.out.println(arrayList);
		 	}
	 	}
		return arrayList;
	}
	
	
	private static void doTOUserBonusContentlist(){
		List<Integer> list1 = new ArrayList<Integer>();  
		  List<Integer> list2 = new ArrayList<Integer>();  
		  list1.add(1);  
		  list1.add(2);  
		  list1.add(3);  
		  list1.add(4);  
		  list2.add(3);  
		  list2.add(4);  
		  list2.add(5);  
		  list2.add(6);  
		  List<Integer> list = new ArrayList<Integer>();  
		  list.addAll(list1);  
		  list.addAll(list2);  
		  System.out.println(list);  
		  
		  Collections.sort(list, new Comparator<Integer>() {  
		   public int compare(Integer o1, Integer o2) {  
		    return o1 - o2;  
		   }  
		  
		  });  
		  System.out.println(list);  
		  for (int i = 1; i < list.size(); i++) {  
		   if (list.get(i) == list.get(i - 1)) {  
		    list.remove(i);  
		   }  
		  }  
		  System.out.println(list);  
	}
	
	public static Set<Integer>  getid(Integer[] a, Integer[] b){
			Set<Integer> same = new HashSet<Integer>();  //用来存放两个数组中相同的元素  
	      Set<Integer> temp = new HashSet<Integer>();  //用来存放数组a中的元素  
	      for (int i = 0; i < a.length; i++) {  
	          temp.add(a[i]);   //把数组a中的元素放到Set中，可以去除重复的元素  
	      }  
	        
	      for (int j = 0; j < b.length; j++) {  
	        //把数组b中的元素添加到temp中  
	        //如果temp中已存在相同的元素，则temp.add（b[j]）返回false  
	        if(!temp.add(b[j]))  
	            same.add(b[j]);  
	    }  
	    return same;   
	}
	//判断月末
	public static boolean isLastDayOfMonth(Date date) { 
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date); 
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1)); 
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) { 
            return true; 
        } 
        return false; 
    } 
	public static void selectDay(){
		 Calendar ca = Calendar.getInstance();
		 ca.setTime(new Date()); 
		 System.out.println(ca.get(Calendar.DAY_OF_MONTH));
		 
		 }

}
