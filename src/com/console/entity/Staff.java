package com.console.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.console.ConsoleHelper;

public class Staff implements Serializable {
    /**
     * 对象id
     */
    private static final long serialVersionUID = -8024987555656772049L;

    /**
     * 简单字典标识
     */
    public static final String SIMPLE_DIC_IDENTIFICATION = "dicStaff";

    /** 员工编号. */
    private String id;
    /** 姓名. */
    private String name;
    /** 登录工号. */
    private String loginName;
    /** 密码. */
    private String password;
    /** 电话. */
    private String phone;
    /** 传真. */
    private String fax;
    /** 手机. */
    private String mobile;
    /** 电子函件. */
    private String email;
    /** 注册日期. */
    private Date regDate;
    /** 是否有效. */
    private String valid;
    /** 备注. */
    private String description;
    /** 分机号. */
    private String extensionNumber;
    /** 系统模板. */
    private String sysTemplate;
    /** 所属部门. */
    private Department department;
    /**人员类型*/
    private String staffType;
    
    /**
     * 入职时间
     */
    private Date inDate;
    
    /**
     * 离职时间
     */
    private Date  exitDate;
    
    private String idNum;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 房号
     */
    private String roomNum;
    
    public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	//    /** 职务 */
   private String duty;

    /** 本员工所具有的系统权限编号集. */
    private Set powerIdSet;

    /** 所授与的系统角色集. */
    private Set systemRole;

    /**顺序号*/
    private Integer orderNumber;
//
//    private Set<String> workQueue;
   private String isLogin;//在线情况
   private String memberId;
   /**2016/1/25*/
   /**政治面貌*/
   private String politics;
   /**文化程度*/
   private String education;
   /**出生日期*/
   private Date birth;
   /**待遇级别*/
   private String treatmentLevel;
   /**入党时间*/
   private Date partyDate;
   /**入编时间*/
   private Date editDate;
   /**报到时间*/
   private Date registerDate;
   /**试用期时间*/
   private String tryDate;
   /**转正定级时间*/
   private Date levelDate;
   /**转任/调入时间*/
   private Date transferDate;
   /**任现职时间*/
   private Date currentDated;
   /**任现职文号*/
   private String currentNum;
   /**年度考核信息*/
   private String yearGrade;
   /**退休时间*/
   private Date retireDate;
   /**退休时职务级别*/
   private String retireDutyLevel;
   /**合同期限*/
   private String contractPeriod;
   /**参保情况 */
   private String insured;
   /**聘用岗位（技术行政/工勤）*/
   private String hireDuty;
   /**录用时间*/
   private Date hireDate;
   
	public String getPolitics() {
    return politics;
}

public void setPolitics(String politics) {
    this.politics = politics;
}

public String getEducation() {
    return education;
}

public void setEducation(String education) {
    this.education = education;
}

public Date getBirth() {
    return birth;
}

public void setBirth(Date birth) {
    this.birth = birth;
}

public String getTreatmentLevel() {
    return treatmentLevel;
}

public void setTreatmentLevel(String treatmentLevel) {
    this.treatmentLevel = treatmentLevel;
}

public Date getPartyDate() {
    return partyDate;
}

public void setPartyDate(Date partyDate) {
    this.partyDate = partyDate;
}

public Date getEditDate() {
    return editDate;
}

public void setEditDate(Date editDate) {
    this.editDate = editDate;
}

public Date getRegisterDate() {
    return registerDate;
}

public void setRegisterDate(Date registerDate) {
    this.registerDate = registerDate;
}

public String getTryDate() {
    return tryDate;
}

public void setTryDate(String tryDate) {
    this.tryDate = tryDate;
}

public Date getLevelDate() {
    return levelDate;
}

public void setLevelDate(Date levelDate) {
    this.levelDate = levelDate;
}

public Date getTransferDate() {
    return transferDate;
}

public void setTransferDate(Date transferDate) {
    this.transferDate = transferDate;
}

public Date getCurrentDated() {
    return currentDated;
}

public void setCurrentDated(Date currentDated) {
    this.currentDated = currentDated;
}

public String getCurrentNum() {
    return currentNum;
}

public void setCurrentNum(String currentNum) {
    this.currentNum = currentNum;
}

public String getYearGrade() {
    return yearGrade;
}

public void setYearGrade(String yearGrade) {
    this.yearGrade = yearGrade;
}

public Date getRetireDate() {
    return retireDate;
}

public void setRetireDate(Date retireDate) {
    this.retireDate = retireDate;
}

public String getRetireDutyLevel() {
    return retireDutyLevel;
}

public void setRetireDutyLevel(String retireDutyLevel) {
    this.retireDutyLevel = retireDutyLevel;
}

public String getContractPeriod() {
    return contractPeriod;
}

public void setContractPeriod(String contractPeriod) {
    this.contractPeriod = contractPeriod;
}

public String getInsured() {
    return insured;
}

public void setInsured(String insured) {
    this.insured = insured;
}

public String getHireDuty() {
    return hireDuty;
}

public void setHireDuty(String hireDuty) {
    this.hireDuty = hireDuty;
}

public Date getHireDate() {
    return hireDate;
}

public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
}

    public String getMemberId() {
	return memberId;
}

public void setMemberId(String memberId) {
	this.memberId = memberId;
}

	/**
     * 空构造函数
     */
    public Staff() {
    }

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	/**
     * 构造函数
     * @param id 人员编号
     */
    public Staff(String id) {
        this.id = id;
    }

    /**
     * @return 从属的单位节点，通过Department.Code设置行政区划来控制
     */
    public Department getBelong() {
        Department dep = getDepartment();
        while (dep != null) {
            if (dep.getCode() != null && dep.getCode().trim().length() > 0) {
                return dep;
            } else {
                dep = ConsoleHelper.getDepartment(dep.getParentId());
            }
        }
        return null;
    }
    
    /**
     * @return 从属的区划编码
     */
    public String getBelongCode() {
        Department dep =  getBelong();
        if (dep != null) {
            return cleanCode(dep.getCode());
        }
        return "";
    }
    /**
     * 清除行政区域编码中的无效字符
     * @param divisionCode
     * @return 清除后的行政编码
     */
    private final String cleanCode(String divisionCode) {
        if (divisionCode == null || divisionCode.trim().length() == 0) {
            return "";
        }
        divisionCode = divisionCode.trim();
        int length = divisionCode.length();
        if (length == 6) { //只有六位编码的，才有补零规则
            for (int i = length - 1; i >=0; i--) {
                if (divisionCode.charAt(i) == '0') {
                    length--;
                } else {
                    break;
                }
            }
        }
        return divisionCode.substring(0, length);
    }
    /**
     * @hibernate.many-to-one column = "dep_id" not-null = "true"
     * @return Returns the department.
     */
    public Department getDepartment() {
        return this.department;
    }

    /**
     * @param department The department to set.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

  

    public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	/**
     * @hibernate.property column = "description" length = "100" not-null = "false"
     * @return Returns the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @hibernate.property column = "email" length = "60" not-null = "false"
     * @return Returns the email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @hibernate.property column = "extension_number" length = "10" not-null = "false"
     * @return Returns the extensionNumber.
     */
    public String getExtensionNumber() {
        return this.extensionNumber;
    }

    /**
     * @param extensionNumber The extensionNumber to set.
     */
    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    /**
     * @hibernate.property column = "fax" length = "40" not-null = "false"
     * @return Returns the fax.
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * @param fax The fax to set.
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @hibernate.id column = "staff_id" generator-class = "native"
     * @return Returns the id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @hibernate.property column = "login_name" length = "40" not-null = "true" unique = "true"
     * @return Returns the loginName.
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * @param loginName The loginName to set.
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @hibernate.property column = "mobile_phone" length = "40" not-null = "false"
     * @return Returns the mobile.
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * @param mobile The mobile to set.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @hibernate.property column = "staff_name" length = "20" not-null = "true"
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @hibernate.property column = "password" length = "40" not-null = "true"
     * @return Returns the password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @hibernate.property column = "phone" length = "40" not-null = "false"
     * @return Returns the phone.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * @param phone The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @hibernate.property column = "reg_date" not-null = "false"
     * @return Returns the regDate.
     */
    public Date getRegDate() {
        return this.regDate;
    }



    /**
     * @param regDate The regDate to set.
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * @hibernate.property column = "valid" length = "1" not-null = "true"
     * @return Returns the valid.
     */
    public String getValid() {
        return this.valid;
    }

    /**
     * @param valid The valid to set.
     */
    public void setValid(String valid) {
        this.valid = valid;
    }

//    /**
//     * @hibernate.set table = "cg_sys_staff_flow_role_rel" cascade = "none"
//     *                inverse = "false" lazy = "true" order-by = "flow_role_name asc"
//     * @hibernate.collection-key column = "staff_id"
//     * @hibernate.collection-many-to-many class = "com.spower.basesystem.manage.valueobject.FlowRole"
//     *                column = "flow_role_name"
//     * @return Returns the flowRole.
//     */
//    public Set getFlowRole() {
//        return this.flowRole;
//    }
//
//    /**
//     * @param flowRole The flowRole to set.
//     */
//    public void setFlowRole(Set flowRole) {
//        this.flowRole = flowRole;
//    }

    /**
     * @hibernate.set table = "cg_sys_staff_role_rel" cascade = "none"
     *                inverse = "false" lazy = "true" order-by = "role_id asc"
     * @hibernate.collection-key column = "staff_id"
     * @hibernate.collection-many-to-many class = "com.spower.basesystem.manage.valueobject.Role"
     *                column = "role_id"
     * @return Returns the systemRole.
     */
    public Set getSystemRole() {
        return this.systemRole;
    }

    /**
     * @param systemRole The systemRole to set.
     */
    public void setSystemRole(Set systemRole) {
        this.systemRole = systemRole;
    }

    /**
     * 加入员工具有的权限.
     * @param powerId   权限编号
     */
    public void addPowerId(String powerId) {
        if (this.powerIdSet == null) {
            this.powerIdSet = new HashSet();
        }
        if (this.powerIdSet.add(powerId)) {
            String temp = powerId;
            while (temp.length() > Power.LEVEL_LENGTH) {
                temp = temp.substring(0, temp.length() - Power.LEVEL_LENGTH);
                if (!this.powerIdSet.add(temp)) {
                    break;
                }
            }
        }
    }

    /**
     * 判定员工是否具有权限编号所示的权限.
     * @param powerId   权限编号
     * @return  具有权限返回true，否则返回false
     */
    public boolean hasPower(String powerId) {
        return this.powerIdSet != null && this.powerIdSet.contains(powerId);
    }

    /** 判定员工是否具有 页面操作权限编码 对应的权限.
     * @param opCode
     * @return  具有权限返回true，否则返回false
     */
    public boolean hasOperate(String opCode) {
        Set operateCodes = new HashSet();
        Iterator iter = getSystemRole().iterator();
        while(iter.hasNext()){
            Role role = (Role) iter.next();
            Iterator opIterator = role.getOperates().iterator();
            while(opIterator.hasNext()){
                Operate op = (Operate) opIterator.next();
                operateCodes.add(op.getOperateCode());
            }
        }

        return operateCodes.contains(opCode);
    }
    /**
     * 判断是否授与了员工任意权限
     * @return  true-->授与了权限，false-->没有任何授权
     */
    public boolean isAnyPower() {
        return this.powerIdSet != null && this.powerIdSet.size() > 0;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Staff) {
            if (this.id != null) {
                return this.id.equals(((Staff) obj).getId());
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

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[编号：" + this.id + " 姓名：" + this.name + "]";
    }

    /**
     * @return 返回 orderNumber。
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber 要设置的 orderNumber。
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return 返回 sysTemplate。
     */
    public String getSysTemplate() {
        return sysTemplate;
    }

    /**
     * @param sysTemplate 要设置的 sysTemplate。
     */
    public void setSysTemplate(String sysTemplate) {
        this.sysTemplate = sysTemplate;
    }


    /** 判断用户是否拥有某个角色
     * @param roleName
     * @return
     */
    public boolean hasRole(String roleName) {
        if (this.systemRole != null) {
            for (Iterator it = this.systemRole.iterator(); it.hasNext();) {
                Role role = (Role) it.next();
                if (role.getName().equals(roleName)) {
                    return true;
                }
            }
        }
        return false;
    }

	

//    /**
//     * @return the workQueue
//     */
//    public Set<String> getWorkQueue() {
//        return workQueue;
//    }
//
//    /**
//     * @param workQueue the workQueue to set
//     */
//    public void setWorkQueue(Set<String> workQueue) {
//        this.workQueue = workQueue;
//    }

}
