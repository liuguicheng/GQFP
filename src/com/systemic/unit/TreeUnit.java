package com.systemic.unit;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class TreeUnit {

	
	private static List<TreeModel> returnList = new ArrayList<TreeModel>();
    
    /**
	 * @return the returnList
	 */
	public static List<TreeModel> getReturnList() {
		return returnList;
	}

	/**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @return String
     */ 
    public static List<TreeModel> getChildNodes(List<TreeModel> list, String typeId) {
        if(list == null || typeId == null) return null;
        for (Iterator<TreeModel> iterator = list.iterator(); iterator.hasNext();) {
        	TreeModel node = (TreeModel) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (typeId.equals(node.getId())) {
                recursionFn(list, node);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getParentId()==0) {
                recursionFn(list, node);
            }*/
        }
        return returnList;
    }
     
    private static void recursionFn(List<TreeModel> list, TreeModel node) {
        List<TreeModel> childList = getChildList(list, node);// 得到子节点列表
        if (hasChild(list, node)) {// 判断是否有子节点
            returnList.add(node);
            Iterator<TreeModel> it = childList.iterator();
            while (it.hasNext()) {
            	TreeModel n = (TreeModel) it.next();
                recursionFn(list, n);
            }
        } else {
            returnList.add(node);
        }
    }
     
    // 得到子节点列表
    private static List<TreeModel> getChildList(List<TreeModel> list, TreeModel node) {
        List<TreeModel> nodeList = new ArrayList<TreeModel>();
        Iterator<TreeModel> it = list.iterator();
        while (it.hasNext()) {
        	TreeModel n = (TreeModel) it.next();
        	if(n.getPid()!=null&&!"".equals(n.getPid())){
        		if(node.getId()!=null&&!"".equals(node.getId())){
        			if (n.getPid().equals(node.getId())) {
                        nodeList.add(n);
                    }
        		}
        		
        	}
            
        }
        return nodeList;
    }
 
    // 判断是否有子节点
    private static boolean hasChild(List<TreeModel> list, TreeModel node) {
        return getChildList(list, node).size() > 0 ? true : false;
    }
    
 // 本地模拟数据测试
    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        List<TreeModel> nodeList = new ArrayList<TreeModel>();
//        TreeModel node1 = new TreeModel(String.valueOf(1), "蔬菜", String.valueOf(0));
//        TreeModel node2 = new TreeModel(String.valueOf(2), "水产", String.valueOf(0));
//        TreeModel node3 = new TreeModel(String.valueOf(3), "畜牧", String.valueOf(0));
//        TreeModel node4 = new TreeModel(String.valueOf(4), "瓜类", String.valueOf(1));
//        TreeModel node5 = new TreeModel(String.valueOf(5), "叶类", String.valueOf(1));
//        TreeModel node6 = new TreeModel(String.valueOf(6), "丝瓜", String.valueOf(4));
//        TreeModel node7 = new TreeModel(String.valueOf(7), "黄瓜", String.valueOf(4));
//        TreeModel node8 = new TreeModel(String.valueOf(8), "白菜", String.valueOf(1));
//        TreeModel node9 = new TreeModel(String.valueOf(9), "虾", String.valueOf(10));
//        TreeModel node10 = new TreeModel(String.valueOf(10), "鱼", String.valueOf(2));
//        TreeModel node11 = new TreeModel(String.valueOf(11), "牛", String.valueOf(3));
//         
//        nodeList.add(node1);
//        nodeList.add(node2);
//        nodeList.add(node3);
//        nodeList.add(node4);
//        nodeList.add(node5);
//        nodeList.add(node6);
//        nodeList.add(node7);
//        nodeList.add(node8);
//        nodeList.add(node9);
//        nodeList.add(node10);
//        nodeList.add(node11);
//         
//        List<TreeModel> reemodellist= getChildNodes(nodeList,"1");
//        for (TreeModel treeModel : reemodellist) {
//			System.out.println("id:"+treeModel.getId()+"-"+"父id:"+treeModel.getPid()+"-"+"name:"+treeModel.getName());
//		}
//        long end = System.currentTimeMillis();
//        System.out.println("用时:" + (end - start) + "ms");
    	
    	
    }

}
