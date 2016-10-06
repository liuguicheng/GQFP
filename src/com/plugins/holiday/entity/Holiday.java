package com.plugins.holiday.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Holiday entity. @author MyEclipse Persistence Tools
 */

public class Holiday implements java.io.Serializable {

    // Fields

    /**节日ID*/
    private String holidayId;
    /**节日类型*/
    private String holidayType;
    /**节日名称*/
    private String holidayName;
    /**是否放假*/
    private String isLeave;
    /**放假原因*/
    private String holidayReason;
    /**节日 日期表示*/
    private Date holidayDate;
    /**节日 字符串表示*/
    private String holidayStr;
    /**是否法定假日*/
    private String isStatutory;
    /**备注*/
    private String remark;
    private String holidayYear;

    /**
     * 表示放假
     */
    public static final String HOLIDAY_YES="1";
    /**
     * 表示不放假
     */
    public static final String HOLIDAY_NO="0";


    /**
     * 表示农历节日类型
     */
    public static final String LunarCalendar_TYPE="0";
    /**
     * 表示阳历(新历)节日类型
     */
    public static final String GregorianCalendar_TYPE="1";
    public static final Map holidayYearMap=new HashMap();

    // Constructors

    /** default constructor */
    public Holiday() {
    }

    /** minimal constructor */
    public Holiday(String holidayId) {
        this.holidayId = holidayId;
    }

    /** full constructor */
    public Holiday(String holidayId, String holidayType, String holidayName,
            String isLeave, String holidayReason, Date holidayDate,
            String holidayStr, String isStatutory, String remark) {
        this.holidayId = holidayId;
        this.holidayType = holidayType;
        this.holidayName = holidayName;
        this.isLeave = isLeave;
        this.holidayReason = holidayReason;
        this.holidayDate = holidayDate;
        this.holidayStr = holidayStr;
        this.isStatutory = isStatutory;
        this.remark = remark;
    }

    // Property accessors



    public String getHolidayId() {
        return this.holidayId;
    }

    public String getHolidayYear() {
        return holidayYear;
    }

    public void setHolidayYear(String holidayYear) {
        this.holidayYear = holidayYear;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId;
    }

    public String getHolidayType() {
        return this.holidayType;
    }

    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    public String getHolidayName() {
        return this.holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getIsLeave() {
        return this.isLeave;
    }

    public void setIsLeave(String isLeave) {
        this.isLeave = isLeave;
    }

    public String getHolidayReason() {
        return this.holidayReason;
    }

    public void setHolidayReason(String holidayReason) {
        this.holidayReason = holidayReason;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayStr() {
        return this.holidayStr;
    }

    public void setHolidayStr(String holidayStr) {
        this.holidayStr = holidayStr;
    }

    public String getIsStatutory() {
        return isStatutory;
    }

    public void setIsStatutory(String isStatutory) {
        this.isStatutory = isStatutory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



}