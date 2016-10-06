package com.plugins.dictionary.entity;

import java.io.Serializable;

import org.springline.beans.tree.support.layered.ILayerTreeAbility;

public class DicDataTree implements Serializable,ILayerTreeAbility {

	/**
     * 树标识
     */
    public static final String TREE_DIC_QUALIFYTYPE = "qualifyTree";

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

	/**
     * 为实现菜单树而增加的属性，
     *
     */
    private int layer;

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
		//设定级别，前提是 排序一定要正确。。
		if(dataCode.length() == 5){
			//后面三位为‘000’的，是第一级
			if(dataCode.substring(2).equals("000")){
				this.layer = 1;
			}else{
				//后面两位为‘00’的，是第二级，其余的为第三级
				if(dataCode.substring(3).equals("00")){
					this.layer = 2;
				}else{
					this.layer = 3;
				}
			}
		}
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

	/**
     * @return the layer
     */
    public int getLayer() {
        return layer;
    }

    /**
     * @param layer the layer to set
     */
    public void setLayer(int layer) {
        this.layer = layer;
    }

	public String getNodeKey() {
		if (this.typeCode == null) {
            return null;
        }
		return this.typeCode.toString();
	}

	public String getNodeName() {

		return this.dataName;
	}

}
