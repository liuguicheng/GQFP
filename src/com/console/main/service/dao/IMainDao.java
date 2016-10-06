/**
 * Description:
 * History:  2009-7-22 Create
**/

package com.console.main.service.dao;


import org.springline.orm.dao.ICommonDao;

import com.console.entity.Staff;

/**
 * @description
 */
public interface IMainDao extends ICommonDao {
    /**
     * 根据登录工号取员工信息，未找或员工状态无效到返回null.
     * @param loginName 登录工号
     * @return  Staff实例
     */
    Staff selectStaff(String loginName);
    
 
    
    /**
     * 根据登录工号取员工信息，包括无效状态的员工，未找到返回null.
     * @param loginName 登录工号
     * @return  Staff实例
     */
    Staff selectAllStaff(String loginName);
    
}
