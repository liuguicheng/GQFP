package com.systemic.gq.bonus.command;

import java.util.Date;

import org.springline.web.pagination.PaginationInfo;

public class BonusRecordInfo extends PaginationInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1119115641994929801L;
	private String id;
	private double money;//金额
	private String userid;//获得用户
	private String username;//获得用户姓名
	private Date create_time;//获得时间
	private Date end_time;//结束时间
	private Integer isSend;//是否发放 0未发放 1已发放
	private Date send_time;//发放时间
	/**
	 * @return the isSend
	 */
	public Integer getIsSend() {
		return isSend;
	}
	/**
	 * @param isSend the isSend to set
	 */
	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	private String bonustype;//奖金类型
	private Integer proportion;//比例
	private String remark;//备注
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getBonustype() {
		return bonustype;
	}
	public void setBonustype(String bonustype) {
		this.bonustype = bonustype;
	}
	public Integer getProportion() {
		return proportion;
	}
	public void setProportion(Integer proportion) {
		this.proportion = proportion;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the send_time
	 */
	public Date getSend_time() {
		return send_time;
	}
	/**
	 * @param send_time the send_time to set
	 */
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	
	
}
