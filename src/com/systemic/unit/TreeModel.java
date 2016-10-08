package com.systemic.unit;

import com.systemic.gq.entity.Member;

public class TreeModel {
	private String id;//节点id
	private String pid;//父节点id
	private String name;//节点名称
	private String icon;//自定义图标
	private String isParent;//是否为父节点
	private String iconOpen;//父节点点开图标
	private String iconClose;//父节点关闭图标
	private Member member;
	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}
	/**
	 * @param member the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}
	public static String OPEN_TRUE="true";
	public static String OPEN_FALSE="false";
	public static String ICOIMG_ZS="../lib/ztree/zTreeStyle/img/ico/member.gif";
	public static String ICOIMG_LS="../lib/ztree/zTreeStyle/img/ico/user.gif";
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the isParent
	 */
	public String getIsParent() {
		return isParent;
	}
	/**
	 * @param isParent the isParent to set
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	/**
	 * @return the iconOpen
	 */
	public String getIconOpen() {
		return iconOpen;
	}
	/**
	 * @param iconOpen the iconOpen to set
	 */
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	/**
	 * @return the iconClose
	 */
	public String getIconClose() {
		return iconClose;
	}
	/**
	 * @param iconClose the iconClose to set
	 */
	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}
	
	
	public TreeModel(String id,  String name,String pid) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
	}
	public TreeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	
	
}
