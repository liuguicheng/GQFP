package com.plugins.holiday.service.dao;


import java.util.Date;
import java.util.List;
import org.springline.orm.dao.ICommonDao;
import com.plugins.holiday.entity.Holiday;

public interface IHolidayDao extends  ICommonDao{

     /**
      *根据名称获得假日的列表信息
      * @return
      */
     List<Holiday> getHolidayByName(String name);
     /**
      * 获得不重复的节日的名称
      * @return
      */
     List getNameList(String name);
    /**
     * 获得某种假日的日期字符串  用于初始化js
     * @param isStatutory 0不是法定假日 1是法定假日
     * @param holidayType  0 农历 1 新历
     * @return
     */
     List getHolidayStr(String year,String isStatutory,String holidayType);
     /**
      * 获得数据库中处于两个日期内的数据
      * @param begin 开始日期
      * @param end 结束日期
      * @return
      */
     List  getBetweenDays(Date begin,Date end);
     /**
      * 根据传入的日期  查询数据库 判断这个日期是否放假
      * @param date
      * @return true 放假 false不放假
      */
     Boolean checkHolidayByDate(Date date);
     /**
      * 删除数据库中的所有数据
      */
     void deleteAll();
     /**
      * 获得某一年的所有记录
      * @param year
      * @return
      */
     List getHolidayByyearStr(String year);
     /**
      * 获得某一年的所有放假的日期
      * @param year
      * @return
      */
     List getHolidays(String year);
     /**
      * 根据年份和日期字符串 查询数据
      * @param year
      * @param str
      * @return
      */
     List getHolidayStr(String date);
     /**
      * 查看数据库中有哪些数据初始化过了
      * @return
      */
     List getYearFromHoliday();

}
