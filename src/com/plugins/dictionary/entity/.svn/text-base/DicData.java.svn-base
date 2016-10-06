package com.plugins.dictionary.entity;

/**
 * DicData entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class DicData implements java.io.Serializable{

	//编码手工录入方式
	public static final String NOT_AUTO_GENERATE = "1";
	//编码自动增长方式
	public static final String AUTO_GENERATE = "2";
    // Fields

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

	/**说明*/
	private String remark;

    // Constructors

    /** default constructor */
    public DicData() {
    }

    /** minimal constructor */
    public DicData(String dataId, String dataName, String typeCode,
            String isValid, String dataCode) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.typeCode = typeCode;
        this.isValid = isValid;
        this.dataCode = dataCode;
    }

    /** full constructor */
    public DicData(String dataId, String dataName, String typeCode,
            String isValid, Integer dataSort, String dataCode) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.typeCode = typeCode;
        this.isValid = isValid;
        this.dataSort = dataSort;
        this.dataCode = dataCode;
    }

    // Property accessors

    /**
     * 返回dataId
     */
    public String getDataId() {
        return this.dataId;
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

	/**
     *
     * @param dataId 设置dataId
     */
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    /**
     *
     * @return 返回dataName
     */
    public String getDataName() {
        return this.dataName;
    }

    /**
     *
     * @param dataName 设置dataName
     */
    public void setDataName(String dataName) {
        this.dataName = dataName;
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

}