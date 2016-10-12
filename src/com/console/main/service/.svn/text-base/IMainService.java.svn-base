/**
 * Description:
 * History:  2009-7-22 Create
**/

package com.console.main.service;

import com.console.entity.Department;
import com.console.entity.Staff;
import com.console.entity.StaffSecurity;

/**
 * @description
 */
public interface IMainService {
    /**
     * 根据登录工号取员工信息，未找或员工状态无效到返回null.
     * @param loginName 登录工号
     * @return  Staff实例
     */
    Staff selectStaff(String loginName);

    /**
     * 校验登录工号及密码：通过校验返回对应的Staff实例，否则返回空值（NULL）.
     * @param loginName     登录工号
     * @param password      密码
     * @return  Staff实例
     */
    Staff doValidate(String loginName, String password);

    /** 获取组织结构对象
     * @param id
     * @return
     */
    Department selectDepartment(String id);


    /** 修改用户密码
     * @param staff 用户
     * @param newPassword 新密码
     */
    void updatePassword(Staff staff, String newPassword);
    
  
    
    /**
     * 在线/离线
     * 
     * @param isLogin Staff 实例
     */
    Staff updateIsLogin(Staff staff,String isLogin);
    
    /**
     * 根据员工编号取员工的登陆安全信息. <b>注：本方法不过滤无效用户</b>
     * 
     * @param id
     *            员工编号
     * @return StaffSecurity实例
     */
    StaffSecurity loadStaffSecurity(String staffId);
    
    /**
     * 根据登录工号取员工信息，包括无效状态的员工，未找到返回null.
     * @param loginName 登录工号
     * @return  Staff实例
     */
    Staff selectAllStaff(String loginName);
    
    /** 记录times值
     * @param loginName 用户名
     * @param times 登录次数
     */
    public StaffSecurity doTimes(String loginName, int times);
    
    /**  记录登录失败的次数
     * @param loginName 用户名
     * @param count 失败次数
     */
    public StaffSecurity doFailiedTimes(String loginName,int count) ;

    /** 给用户解锁
     * @param loginName 用户
     */
    public Staff doUnlock(Staff staff) ;
    
    public Staff selectStaffById(String id);
    
   
}
