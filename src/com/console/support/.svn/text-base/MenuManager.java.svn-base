
package com.console.support;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springline.beans.tree.ITreeNode;
import org.springline.beans.tree.support.TreeNodeImpl;

import com.console.entity.Department;
import com.console.entity.Power;
import com.console.entity.Staff;


/**
 * @description
 */
public final class MenuManager {


    /**
     * 缺省构造方法.
     */
    private MenuManager() {
        //
    }


    /**
     * 创建菜单树.
     * @param root          菜单的相关数据
     * @param currentStaff  当前登录的员工
     * @param dept          所属单位
     * @return  MenuTree实例
     */
    public static ITreeNode buildMenuTree(ITreeNode root, Staff currentStaff, Department dept) {
        ITreeNode result = new TreeNodeImpl(root.getNodeKey(), root.getNodeName(), root.getData());
        result.getChildren().addAll(buildMenuTree(root.getChildren(), currentStaff, dept));

        return result;
    }

    private static List buildMenuTree(List children, Staff currentStaff, Department dept) {
        List list = new ArrayList();
        if (children == null || children.size() == 0) {
            return list;
        }
        for (Iterator it = children.iterator(); it.hasNext();) {
            ITreeNode node = (ITreeNode) it.next();
            Power power = (Power) node.getData();
            ITreeNode tmp = new TreeNodeImpl(node.getNodeKey(), node.getNodeName(), node.getData());
            if (hasPower(power, currentStaff, dept)) {
                list.add(tmp);
            }
            tmp.getChildren().addAll(buildMenuTree(node.getChildren(), currentStaff, dept));
        }
        return list;

    }
    /**判断当前用户是否拥有相应权限
     * @param power Map of power
     * @param currentUser 当前用户
     * @param dept 用户所在单位
     * @return true or false
     */
    public static boolean hasPower(Power power, Staff currentUser, Department dept) {
        if (power != null) {
            if (Power.POWER_TYPE_COMMON.equals(power.getTypeFlag())) { //无需授权
                return true;
            } else if (Power.POWER_TYPE_ROLE.equals(power.getTypeFlag())) { //角色授权
                return currentUser.hasPower(power.getCode());
            } else if (Power.POWER_TYPE_DEPT.equals(power.getTypeFlag())) { //部门授权
                return  dept.hasPower(power.getCode());
            } else if (Power.POWER_TYPE_ITERATE.equals(power.getTypeFlag())) { //迭代授权
                return currentUser.hasPower(power.getCode()) && dept.hasPower(power.getCode());
            }
        }
        return false;
    }

}
