package com.systemic.gq.entity;

import java.util.Date;


/**
 * Stock entity. @author MyEclipse Persistence Tools
 */

public class Stock  implements java.io.Serializable {
    
	 /**
	 * 字典标识,新增会员时使用
	 */
	public static final String SIMPLE_DIC_IDENTIFICATION = "dicStock";
     /**
	 * 股权种类表
	 */
	private static final long serialVersionUID = -7380768251659407376L;
	private String id;
     private String gradeNum;//等级
     private Double money;//所需金钱
     private int stockMoney;//股权份额
     private Double cap;//投资封顶
     private Integer istrue;//是否开放
     private Date createTime;//创建时间
     
     private String bonusContentID;//股权奖励id（外键）
     //private BonusContent bonuscontent;//股权奖级实体



	/** default constructor */
    public Stock() {
    }

    
    /** full constructor */
    public Stock(String gradeNum, Double money, int stockMoney, Double cap, Integer istrue, Date createTime, String bonusContentID) {
        this.gradeNum = gradeNum;
        this.money = money;
        this.stockMoney = stockMoney;
        this.cap = cap;
        this.istrue = istrue;
        this.createTime = createTime;
        this.bonusContentID = bonusContentID;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Double getMoney() {
        return this.money;
    }
    
    public void setMoney(Double money) {
        this.money = money;
    }


    public int getStockMoney() {
		return stockMoney;
	}


	public void setStockMoney(int stockMoney) {
		this.stockMoney = stockMoney;
	}


	public Double getCap() {
        return this.cap;
    }
    
    public void setCap(Double cap) {
        this.cap = cap;
    }

    public Integer getIstrue() {
        return this.istrue;
    }
    
    public void setIstrue(Integer istrue) {
        this.istrue = istrue;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



	public String getBonusContentID() {
		return bonusContentID;
	}


	public void setBonusContentID(String bonusContentID) {
		this.bonusContentID = bonusContentID;
	}


	public String getGradeNum() {
		return gradeNum;
	}


	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}
   








}