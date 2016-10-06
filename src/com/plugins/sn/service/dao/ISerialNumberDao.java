
package com.plugins.sn.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;



public interface ISerialNumberDao extends ICommonDao {

    /** 根据类型标识和前缀标识获取序号
     * @param classifyIdentifier 类型标识
     * @param prefixIdentifier 前缀标识
     * @return 序号列表
     */
    List selectSysSerialNumber(String classifyIdentifier, String prefixIdentifier);
}
