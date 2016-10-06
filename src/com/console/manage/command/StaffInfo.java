package com.console.manage.command;

import java.util.Date;

import org.springline.web.mvc.SpringlineCommand;

public class StaffInfo extends SpringlineCommand {
    /**
     * 初始密码1
     */
    //public static final String DEFAULT_PASSWORD = "c4ca4238a0b923820dcc509a6f75849b";
    /** 员工编号. */
    private String id;
    /** 姓名. */
    private String name;
    /** 登录工号. */
    private String loginName;
    /** 密码. */
    private String password;
    /** 职务. */
    private String dutyId;
    /** 电话. */
    private String phone;
    /** 传真. */
    private String fax;
    /** 手机. */
    private String mobile;
    /** 电子函件. */
    private String email;
    /** 备注. */
    private String description;
    /** 分机号. */
    private String extensionNumber;
    /** 注册时间. */
    private Date regDate;
    /** 所属部门. */
    private String department;
    /** 所属部门. */
    private String departmentName;
    /** 是否有效. */
    private String valid;
    /** 系统模板. */
    private String sysTemplate;
    /** 操作类型：新增修改. */
    private String opType;
    /** 顺序号 */
    private Integer orderNumber;
    /** 角色id */
    private String[] roleId;
    private String memberId;

    /** 性别 */
    private String sex;
    private String idNum;
    private String staffType;
    private Date inDate;
    private Date exitDate;
    private String duty;
    private String roomNum;
    private String isLogin;
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

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * 工作队列
     */
    private String[] queueName;

    /**
     * @return 返回 orderNumber。
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber
     *            要设置的 orderNumber。
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    /**
     * @param email
     *            The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the extensionNumber.
     */
    public String getExtensionNumber() {
        return extensionNumber;
    }

    /**
     * @param extensionNumber
     *            The extensionNumber to set.
     */
    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    /**
     * @return Returns the fax.
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     *            The fax to set.
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Returns the loginName.
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     *            The loginName to set.
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return Returns the mobile.
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            The mobile to set.
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return Returns the valid.
     */
    public String getValid() {
        return valid;
    }

    /**
     * @param valid
     *            The valid to set.
     */
    public void setValid(String valid) {
        this.valid = valid;
    }

    /**
     * @return Returns the opType.
     */
    public String getOpType() {
        return opType;
    }

    /**
     * @param opType
     *            The opType to set.
     */
    public void setOpType(String opType) {
        this.opType = opType;
    }

    /**
     * @return 返回 sysTemplate。
     */
    public String getSysTemplate() {
        return sysTemplate;
    }

    /**
     * @param sysTemplate
     *            要设置的 sysTemplate。
     */
    public void setSysTemplate(String sysTemplate) {
        this.sysTemplate = sysTemplate;
    }

    /**
     * @return 返回 department。
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department
     *            要设置的 department。
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return 返回 roleId。
     */
    public String[] getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     *            要设置的 roleId。
     */
    public void setRoleId(String[] roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the queueName
     */
    public String[] getQueueName() {
        return queueName;
    }

    /**
     * @param queueName
     *            the queueName to set
     */
    public void setQueueName(String[] queueName) {
        this.queueName = queueName;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     *            the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
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

}