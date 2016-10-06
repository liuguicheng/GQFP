package com.plugins.dictionary.command;

/**
 *
 * @description 字典内容Command
 */
public class DicDataEditInfo {

	/**字典内容ID*/
    private String dataId;

    /**字典内容名称*/
    private String dataName;

    /**字典内容编码*/
    private String dataCode;

    /**字典内容状态1表示正常，0表示非正常*/
    private String isValid;

    /**字典内容排序*/
    private Integer dataSort;

    /**类型编码*/
	private String typeCode;

	/**标识生成方式*/
	private String typeWay;

	/**字典长度*/
	private Integer dataLength;

	/**是否定长*/
	private String isLonger;

	/**说明*/
	private String remark;


	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the dataLength
	 */
	public Integer getDataLength() {
		return dataLength;
	}

	/**
	 * @param dataLength the dataLength to set
	 */
	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	/**
	 * @return the isLonger
	 */
	public String getIsLonger() {
		return isLonger;
	}

	/**
	 * @param isLonger the isLonger to set
	 */
	public void setIsLonger(String isLonger) {
		this.isLonger = isLonger;
	}

	/**
	 * @return the typeWay
	 */
	public String getTypeWay() {
		return typeWay;
	}

	/**
	 * @param typeWay the typeWay to set
	 */
	public void setTypeWay(String typeWay) {
		this.typeWay = typeWay;
	}

	/**
	 * @return the dataId
	 */
	public String getDataId() {
		return dataId;
	}

	/**
	 * @param dataId the dataId to set
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	/**
	 * @return the dataName
	 */
	public String getDataName() {
		return dataName;
	}

	/**
	 * @param dataName the dataName to set
	 */
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	/**
	 * @return the dataCode
	 */
	public String getDataCode() {
		return dataCode;
	}

	/**
	 * @param dataCode the dataCode to set
	 */
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	/**
	 * @return the isValid
	 */
	public String getIsValid() {
		return isValid;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return the dataSort
	 */
	public Integer getDataSort() {
		return dataSort;
	}

	/**
	 * @param dataSort the dataSort to set
	 */
	public void setDataSort(Integer dataSort) {
		this.dataSort = dataSort;
	}

	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

}
