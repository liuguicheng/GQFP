

package com.plugins.pending.common;

import java.util.List;
import java.util.Properties;

import org.springline.orm.Page;

import com.console.entity.Staff;


/**
 * @author Administrator
 *
 */
public interface IPendingItem {

//    /**
//     * 增加一个待办事项
//     * @param moduleName 待办事项所属的模块名
//     * @param flagId 在同一个模块下标识该待办事项的Id
//     * @param url 与该待办事项关联的操作界面的url
//     * @param title 待办事项的标题
//     * @param sender 发送人
//     * @param recipients 接收人
//     */
//    void openItem(String moduleName, String flagId, String  url,
//            String title, Staff sender, List recipients);
    /**
     * 增加一个待办事项
     * @param moduleName 待办事项所属的模块名，多级模块采用反斜杠分隔，如公文管理/发文管理
     * 查询时，moduleName使用模糊查询机制。如果需要读取对象的最终模块名称，可使用PendingItem.getSimpleName()
     * @param flagId 在同一个模块下标识该待办事项的Id
     * @param url 与该待办事项关联的操作界面的url
     * @param title 待办事项的标题
     * @param sender 发送人
     * @param recipients 接收人
     * @param props 扩展属性，以Properties格式写入，读取时使用PendingItem.getProperty(String propertyName)
     */
    void doOpenItem(String moduleName, String flagId, String  url,
            String title, Staff sender, List recipients, Properties props);
    /**
     * 完成一个待办事项
     * @param moduleName 待办事项所属的模块名
     * @param flagId 在同一个模块下标识该待办事项的Id
     * @param finisher 处理人
     */
    void doCloseItem(String moduleName, String flagId, Staff finisher);

    /**
     * 查询个人待办事宜
     * @param userId 用户Id 精确定位，不能为空
     * @param moduleName 模块名称，模糊查询
     * @return PageList Of PendingItem
     */
    Page selectPendingItemList(String userId, String moduleName);
}
