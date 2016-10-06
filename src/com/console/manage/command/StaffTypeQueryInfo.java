package com.console.manage.command;

import org.springline.web.pagination.PaginationInfo;

public class StaffTypeQueryInfo extends PaginationInfo {
	/**  */
	private static final long serialVersionUID = 5970311147861687881L;
	/** 姓名. */
	private String name;
	/** 所属部门. */
	private String departmentName;

	/** 所属部门编码 */
	private String depCode;

	/** 账户类型 */
	private String staffType;
	/** 是否有效. */
	private String valid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

}
