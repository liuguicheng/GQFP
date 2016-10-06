
package com.console.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springline.beans.tree.ITreeNode;
import org.springline.web.filter.AuthenticationFilter;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.console.manage.service.IManageService;


public class StaffSelectController extends AbstractController {

    /**全局**/
    public static final String RANGE_ALL = "all";

    /**     *     */
    private IManageService manageService;
    /**选择人员视图**/
    private String selectStaffView;

    /**
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected final ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map model = new HashMap();
        //按根限定组织结构范围
        String rootId = request.getParameter("rootId");
        String range = request.getParameter("range");
        String depId = null;
        if (rootId != null && rootId.trim().length() > 0) {
            depId = rootId;
        } else {
            if (!RANGE_ALL.equals(range)) {
                Staff currentUser = (Staff) AuthenticationFilter.getAuthenticator(request);
                depId = currentUser.getDepartment().getAncestorDep();
            }
        }
        List depTree = this.manageService.selectDepartmentTree(depId);
        if (depId != null) {
            //depTree = cloneAndFilterDepartmentTree(depTree, depId);
        } else if (depTree != null && depTree.size() > 0) { //返回默认单位值
            depId = ((ITreeNode) depTree.get(0)).getNodeKey();
        }
        model.put("rootId", rootId);
        model.put("range", range);
        model.put("depId", depId);
        model.put("departmentTree", depTree);
        //取其他数据
        model.putAll(getCustomDataMap(request));
        return new ModelAndView(this.selectStaffView, model);
    }

    /** 默认按staffs传递已选择的人员信息，若有其他参数要求，交由子类重载该函数进行处理
     * @param request HttpServletRequest
     * @return 已选择的人员信息Map
     */
    protected Map getCustomDataMap(HttpServletRequest request) {
        //解析初始选择的人员
        String staffs = request.getParameter("staffs");
        Map model = new HashMap();
        if (staffs != null) {
            String[] man = staffs.split(",");
            List manList = new ArrayList();
            for (int i = 0; i < man.length; i++) {
                if (man[i] != null && man[i].trim().length() > 0) {
                    manList.add(ConsoleHelper.getStaff(man[i]));
                }
            }
            model.put("staffList", manList);
        }
        return model;
    }

//    /**
//     * 克隆组织结构树，并去除其中下属单位
//     * 不能在原有树上直接修改，会影响到内存中的数据
//     * @param depList
//     * @return
//     */
//    private List cloneAndFilterDepartmentTree(List depList, Integer rootId) {
//        List cloneList = new ArrayList();
//        for (int i = 0; i < depList.size(); i++) {
//            ITreeNode node = (ITreeNode) depList.get(i);
//            ITreeNode newNode = cloneAndFilterDepartmentTreeNode(node, rootId);
//            if (newNode != null) {
//                cloneList.add(newNode);
//                if (node.getChildren() == null) {
//                    continue;
//                }
//                cloneAndFileterChildren(newNode, node, rootId);
//            }
//        }
//        return cloneList;
//    }
//
//    /** 克隆oldNode所有子节点，加入到newNode的子节点中，中间过滤是单位的节点
//     * @param newNode 新节点
//     * @param oldNode 旧节点
//     * @param rootId 单位Id
//     */
//    private void cloneAndFileterChildren(ITreeNode newNode, ITreeNode oldNode, Integer rootId) {
//        if (oldNode.getChildren() == null) {
//            return;
//        }
//        for (int j = 0; j < oldNode.getChildren().size(); ++j) {
//            ITreeNode tmp = (ITreeNode) oldNode.getChildren().get(j);
//            ITreeNode tmp2 = cloneAndFilterDepartmentTreeNode(tmp, rootId);
//            if (tmp2 != null) {
//                newNode.getChildren().add(tmp2);
//                cloneAndFileterChildren(tmp2, tmp, rootId);
//            }
//       }
//    }
//
//    /** 克隆单个节点，如果是单位，返回null，如果不是返回新节点
//     * @param node
//     * @param rootId
//     * @return
//     */
//    private ITreeNode cloneAndFilterDepartmentTreeNode(ITreeNode node, Integer rootId) {
//        Department dep = (Department) node.getData();
//        if (!dep.getId().equals(rootId) && dep.getId().equals(dep.getAncestorDep())) {//不是根，并且是单位
//            return null;
//        } else {
//            return new TreeNode(node.getNodeKey(), node.getNodeName(), node.getData());
//        }
//    }

    /**
     * @param manageService The manageService to set.
     */
    public void setManageService(IManageService manageService) {
        this.manageService = manageService;
    }

    /**
     * @param selectStaffView The selectStaffView to set.
     */
    public void setSelectStaffView(String selectStaffView) {
        this.selectStaffView = selectStaffView;
    }


}
