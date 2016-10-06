package com.plugins.holiday.service;

import java.util.Date;
import java.util.List;


import com.plugins.holiday.command.HolidayEditInfo;
import com.plugins.holiday.entity.Holiday;

public interface IHolidayService {

    /**
     * 根据id获得假日的详细信息
     * @param holidayId
     * @return
     */
    Holiday selectHolidayById(String holidayId);
    /**
     * 保存假日信息
     * @param editInfo
     */
    Holiday saveHoliday(HolidayEditInfo  editInfo);
    /**
     * 删除假日信息
     * @param holidayIds
     */
    void deleteHoliday(String[] holidayIds);
    /**
     * 初始化假日
     * @param year
     */
    void doInitHolidy(int year);
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
    List selectHolidayStr(String year,String isStatutory,String holidayType);
    /**
     * 获得数据库中处于两个日期内的数据
     * @param begin 开始日期
     * @param end 结束日期
     * @return
     */
    List  doGetBetweenDays(Date begin,Date end);
    /**
     * 获得某一年的所有放假的日期
     * @param year
     * @return
     */
    public List selectHolidays(String year);
    public List selectHolidayStr(String date);
    Holiday updateHoliday(String date);
}
