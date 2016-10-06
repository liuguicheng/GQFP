package com.systemic.gq.entity;

import java.util.Date;

import com.console.entity.Role;


/**
 * BonusContent entity. @author MyEclipse Persistence Tools
 */

public class BonusContent   implements java.io.Serializable {
    // Fields    

     /**
	 *股权奖励表
	 */
	private static final long serialVersionUID = -2209499732658021087L;
	private String id;
     private Integer begin;//奖励发放条件
     private Integer end;//奖励结束条件
     private Integer proportion;//比例
     private Date releaseTime;//发放时间
     private String bz;//备注
     private String bonusType;//奖励类型（外键字典表  ）

    // Constructors

    /** default constructor */
    public BonusContent() {
    }

    
    /** full constructor */
    public BonusContent(Integer begin, Integer end, Integer proportion, Date releaseTime, String bz,String bonusType) {
        this.begin = begin;
        this.end = end;
        this.proportion = proportion;
        this.releaseTime = releaseTime;
        this.bz = bz;
        this.bonusType=bonusType;
    }

   
    // Property accessors

    public String getBonusType() {
		return bonusType;
	}


	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}


	public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public Integer getBegin() {
        return this.begin;
    }
    
    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return this.end;
    }
    
    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getProportion() {
        return this.proportion;
    }
    
    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public Date getReleaseTime() {
        return this.releaseTime;
    }
    
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getBz() {
        return this.bz;
    }
    
    public void setBz(String bz) {
        this.bz = bz;
    }
   

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Role) {
            if (this.id != null) {
                return this.id.equals(((Role) obj).getId());
            }
        }
        return false;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.id.hashCode();
    }



   


}