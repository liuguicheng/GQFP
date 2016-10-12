package com.systemic.unit;

import com.systemic.gq.entity.Member;

public class TreeModel {
	private String id;//节点id
	private String pid;//父节点id
	private String connectionId;//连接点 
	private String name;//节点名称
	private String icon;//自定义图标
	private String isParent;//是否为父节点
	private String iconOpen;//父节点点开图标
	private String iconClose;//父节点关闭图标
	private String region;//区域
	private String tdnum;//多个格子
	private String tdcolspan;//td 跨多少格子
	private String trnuml;//多少行
	private Member member;
	private String registerTime;//注册时间
	private String activationTime;//激活时间
	private String status;//状态
	public static String OPEN_TRUE="true";
	public static String OPEN_FALSE="false";
	public static String ICOIMG_ZS="../lib/ztree/zTreeStyle/img/ico/member.gif";
	public static String ICOIMG_LS="../lib/ztree/zTreeStyle/img/ico/user.gif";
	
	public static String NETWORK_Activation_NO="../lib/ztree/zTreeStyle/img/ico/0.png";
	public static String NETWORK_Activation_YES="../lib/ztree/zTreeStyle/img/ico/1.png";
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
	
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	
	
	/**
	 * @return the connectionId
	 */
	public String getConnectionId() {
		return connectionId;
	}
	
	
	/**
	 * @return the tdnum
	 */
	public String getTdnum() {
		return tdnum;
	}
	/**
	 * @param tdnum the tdnum to set
	 */
	public void setTdnum(String tdnum) {
		this.tdnum = tdnum;
	}
	/**
	 * @return the tdcolspan
	 */
	public String getTdcolspan() {
		return tdcolspan;
	}
	/**
	 * @param tdcolspan the tdcolspan to set
	 */
	public void setTdcolspan(String tdcolspan) {
		this.tdcolspan = tdcolspan;
	}
	/**
	 * @param connectionId the connectionId to set
	 */
	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}
	
	
	/**
	 * @return the trnuml
	 */
	public String getTrnuml() {
		return trnuml;
	}
	/**
	 * @param trnuml the trnuml to set
	 */
	public void setTrnuml(String trnuml) {
		this.trnuml = trnuml;
	}
	
	/**
	 * @return the registerTime
	 */
	public String getRegisterTime() {
		return registerTime;
	}
	/**
	 * @param registerTime the registerTime to set
	 */
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * @return the activationTime
	 */
	public String getActivationTime() {
		return activationTime;
	}
	/**
	 * @param activationTime the activationTime to set
	 */
	public void setActivationTime(String activationTime) {
		this.activationTime = activationTime;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
