/**
 * Description:
 * History:  2010-8-19 Create
**/

package com.plugins.holiday;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 1、提供单实例访问入口及获取工作日的接口
 * 2、实现观察者模式中的被观察者角色，已保证节假日的定义变化能及时通知相关服务
 * 3、由子类继承提供具体实例，及发送变更通知
 */
public abstract class HolidayHelper {

    /**
     * 单实例
     */
    private static HolidayHelper instance = null;

    /**
     * 单实例访问入库
     * @return
     */
    public static HolidayHelper getInstance() {
        return instance;
    }

    /**
     * 观察者清单
     */
    private Set<IHolidayObserver> observers = new HashSet();

    /**
     * 由观察者自己注入
     * @param o
     */
    public synchronized void registerObserver(IHolidayObserver o) {
        observers.add(o);
    }

    /**
     * 发送变更通知，由子类调用
     * @param beginTime
     * @param endTime
     */
    protected synchronized void doUpdate(Date beginTime, Date endTime) {
        for (IHolidayObserver o : observers) {
            o.doHolidayUpdate(beginTime, endTime);
        }
    }

    /**
     * 根据起始时间、工作日数，计算截止时间
     * @param beginTime 起始时间
     * @param workDay 工作日数量
     * @return 截止时间
     */
    public abstract Date getDeadline(Date beginTime, int workDay);

    public static void setInstance(HolidayHelper instance) {
        HolidayHelper.instance = instance;
    }


}
