package com.systemic.gq.entity;

import java.util.Date;


/**
 * TransferAccounts entity. @author MyEclipse Persistence Tools
 */

public class TransferAccounts  implements java.io.Serializable {


    // Fields    

     /**
	 * 转账管理
	 */
	private static final long serialVersionUID = -6266883737592223803L;
	private String taId;//id
     private String taTurnoutAccount;//转出账号
     private String taTurnoutName;//转出会员
     private String taIntoAccount;//转入账号
     private String taIntoName;//转入姓名
     private String taType;//转账钱币种类
     private Double taMoeny;//金额
     private Date taTime;//时间
     private String remark;//备注


    // Constructors

    /** default constructor */
    public TransferAccounts() {
    }

    
    /** full constructor */
    public TransferAccounts(String taTurnoutAccount, String taTurnoutName, String taIntoAccount, String taIntoName, Double taMoeny, Date taTime, String remark) {
        this.taTurnoutAccount = taTurnoutAccount;
        this.taTurnoutName = taTurnoutName;
        this.taIntoAccount = taIntoAccount;
        this.taIntoName = taIntoName;
        this.taMoeny = taMoeny;
        this.taTime = taTime;
        this.remark = remark;
    }

   
    // Property accessors

    public String getTaId() {
        return this.taId;
    }
    
    public void setTaId(String taId) {
        this.taId = taId;
    }

    public String getTaTurnoutAccount() {
        return this.taTurnoutAccount;
    }
    
    public void setTaTurnoutAccount(String taTurnoutAccount) {
        this.taTurnoutAccount = taTurnoutAccount;
    }

    public String getTaTurnoutName() {
        return this.taTurnoutName;
    }
    
    public void setTaTurnoutName(String taTurnoutName) {
        this.taTurnoutName = taTurnoutName;
    }

    public String getTaIntoAccount() {
        return this.taIntoAccount;
    }
    
    public void setTaIntoAccount(String taIntoAccount) {
        this.taIntoAccount = taIntoAccount;
    }

    public String getTaIntoName() {
        return this.taIntoName;
    }
    
    public void setTaIntoName(String taIntoName) {
        this.taIntoName = taIntoName;
    }

    public Double getTaMoeny() {
        return this.taMoeny;
    }
    
    public void setTaMoeny(Double taMoeny) {
        this.taMoeny = taMoeny;
    }

    public Date getTaTime() {
        return this.taTime;
    }
    
    public void setTaTime(Date taTime) {
        this.taTime = taTime;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }


	/**
	 * @return the taType
	 */
	public String getTaType() {
		return taType;
	}


	/**
	 * @param taType the taType to set
	 */
	public void setTaType(String taType) {
		this.taType = taType;
	}
   








}