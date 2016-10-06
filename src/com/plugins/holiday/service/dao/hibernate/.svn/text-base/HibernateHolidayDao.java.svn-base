package com.plugins.holiday.service.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.internal.util.StringHelper;
import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.plugins.holiday.command.HolidayQueryInfo;
import com.plugins.holiday.entity.Holiday;
import com.plugins.holiday.service.dao.IHolidayDao;

public class HibernateHolidayDao extends HibernateCommonDao implements IHolidayDao {

    /**要注入的holidayQueryStringUtil*/
    private IQueryStringUtil holidayQueryStringUtil;

    public IQueryStringUtil getHolidayQueryStringUtil() {
        return holidayQueryStringUtil;
    }
    public void setHolidayQueryStringUtil(IQueryStringUtil holidayQueryStringUtil) {
        this.holidayQueryStringUtil = holidayQueryStringUtil;
    }
    public Page getHolidayPage(HolidayQueryInfo info){

        StringBuffer where=new StringBuffer("");
        Object[] params=new Object[4];
        int index=0;
        if(StringHelper.isNotEmpty(info.getMaxHolidayDate())&&StringHelper.isNotEmpty(info.getMinHolidayDate())){
            where.append(" ((H.holidayType= '"+Holiday.GregorianCalendar_TYPE+"' and char(H.holidayDate) >=? and char(H.holidayDate) <=?) ");
            where.append(" or (H.holidayType= '"+Holiday.LunarCalendar_TYPE+"' and char(H.holidayDate) >=? and char(H.holidayDate) <=?)) and");
            params[index++]=info.getMinHolidayDate();
            params[index++]=info.getMaxHolidayDate();
            params[index++]=info.getMinNlHolidayDate();
            params[index++]=info.getMaxNlHolidayDate();
        }else if(StringHelper.isNotEmpty(info.getMinHolidayDate())&&StringHelper.isEmpty(info.getMaxHolidayDate())){
            where.append(" ((H.holidayType= '"+Holiday.GregorianCalendar_TYPE+"' and char(H.holidayDate) >=?)  ");
            where.append(" or (H.holidayType= '"+Holiday.LunarCalendar_TYPE+"' and char(H.holidayDate) >=?) )and");
            params[index++]=info.getMinHolidayDate();
            params[index++]=info.getMinNlHolidayDate();
        }else if(StringHelper.isEmpty(info.getMinHolidayDate())&&StringHelper.isNotEmpty(info.getMaxHolidayDate())){
            where.append(" ((H.holidayType= '"+Holiday.GregorianCalendar_TYPE+"' and char(H.holidayDate) <=?)  ");
            where.append(" or (H.holidayType= '"+Holiday.LunarCalendar_TYPE+"' and char(H.holidayDate) <=?)) and");
            params[index++]=info.getMaxHolidayDate();
            params[index++]=info.getMaxNlHolidayDate();
        }
        Object[] obj=new Object[index];
        System.arraycopy(params, 0, obj,0,index);


        IQueryObject qo = this.holidayQueryStringUtil.getQueryObject(info,where.toString(),obj);
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List list = super.doQuery(qo.getQueryString(), qo.getParam());
            this.putDataToPage(list);
        }
        return super.find(qo.getQueryString(), qo.getParam(),info.getPageNumber().intValue());
    }
    /**
     * @see com.plugins.holiday.service.dao.IHolidayDao#getHolidayByName(java.lang.String)
     */
    public List<Holiday> getHolidayByName(String name) {
        String sql="from Holiday as h where h.holidayName=?";
        return super.doQuery(sql, new Object[]{name});
    }
    /**
     * @see com.plugins.holiday.service.dao.IHolidayDao#getNameList()
     */
    public List getNameList(String name) {
        String sql="select distinct  holidayName  from Holiday";
        if(name!=null&&name.trim().length()>0){
            sql+="where holidayName=?";
        }
        return super.doQuery(sql);
    }
   /**
    * 获得法定假日的日期字符串 用于初始化日历
    * @see com.plugins.holiday.service.dao.IHolidayDao#getHolidayStr(java.lang.String, java.lang.String)
    */
    public List getHolidayStr(String year,String isStatutory,String holidayType){
        Object[] params=new Object[3];
        String sql="from Holiday where holidayYear=? and isStatutory = ? and holidayType=?";
        params[0]=year;
        params[1]=isStatutory;
        params[2]=holidayType;
        return super.doQuery(sql,params);
    }
    /**
     * 获得数据库中处于两个日期内的数据
     * @param begin 开始日期
     * @param end 结束日期
     * @return
     */
    public List  getBetweenDays(Date begin,Date end){
        Object[] params=new Object[2];
        params[0]=begin;
        params[1]=end;
        String sql="from Holiday where holidayDate>=? and holidayDate<=? and isLeave='1'";
        return super.doQuery(sql,params);
    }
    /**
     * 根据传入的日期  查询数据库 判断这个日期是否放假
     * @param date
     * @return true 放假 false不放假
     */
    public Boolean checkHolidayByDate(Date date){
        String sql="from Holiday where char(holidayDate)=? and isLeave='1'";
        List li=super.doQuery(sql, new Object[]{date});
        if(li!=null&&li.size()>0){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 删除数据库中的所有数据
     */
    public void deleteAll(){
        String sql="from Holiday";
        List li=super.doQuery(sql);
        for(int i=0;i<li.size();i++){
          super.delete(li.get(i));
        }
    }
    /**
     * @see com.plugins.holiday.service.dao.IHolidayDao#getHolidayByyearStr(java.lang.String)
     */
    public List getHolidayByyearStr(String year){
        String sql="";
        if(StringHelper.isNotEmpty(year)){
             sql="from Holiday where holidayYear=?";
             return super.doQuery(sql,new Object[]{year});
        }else{
             return null;
        }
    }
    /**
     * 获得某一年的所有放假的日期
     * @param year
     * @return
     */
    public List getHolidays(String year){
        if(StringHelper.isEmpty(year)){
            return null;
        }
        StringBuffer sb=new StringBuffer("");
        sb.append("from "+Holiday.class.getName()+" where holidayYear=?");
        sb.append(" and isLeave="+Holiday.HOLIDAY_YES);
        return super.doQuery(sb.toString(),new Object[]{year});
    }
    /**
     * 根据年份和日期字符串 查询数据
     * @param year
     * @param str
     * @return
     */
    public List getHolidayStr(String date){
        if(StringHelper.isEmpty(date)){
            return null;
        }
         StringBuffer sb=new StringBuffer("");
         sb.append("from "+Holiday.class.getName()+" where char(holidayDate)=?");
         return super.doQuery(sb.toString(), new Object[]{date});
    }
    /**
     * 查看数据库中有哪些数据初始化过了
     * @return
     */
    public List getYearFromHoliday(){
        String sql=" select DISTINCT holidayYear from "+Holiday.class.getName();
        return super.doQuery(sql);
    }

}
