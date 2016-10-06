
package com.console.power.service;

import java.util.List;

import com.console.entity.Power;
import com.console.power.command.PowerGrantInfo;
import com.console.power.command.PowerQueryInfo;


public interface IPowerService {

    /**
     * 新增权限信息.
     * @param power Power 实例
     * @return  保存后的 Power 实例
     */
    Power savePower(Power power);



    /**
     * 删除权限信息.
     * @param id    需要删除的权限编号
     */
    void deletePower(String[] id);

    /**
     * 获取指定 id 对应的权限信息.
     * @param id    权限编号
     * @return  Power 实例
     */
    Power selectPower(String id);

    /**
     * 获取所有的权限信息的列表.
     * @return  包含所有权限信息（Power实例）的List实例
     */
    List selectAllPower(PowerQueryInfo info);


    /** 更改单位的授权信息
     * @param info 授权信息
     */
    void updatePowerGrantInfo(PowerGrantInfo info);
}
