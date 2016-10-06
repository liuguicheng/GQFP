package com.plugins.sn.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *        @hibernate.class
 *         table="sys_serial_number"
 *
*/
public class SysSerialNumber implements Serializable {

    /**
     * ∂‘œÛid
     */
    private static final long serialVersionUID = 1030552201155978968L;

    /** identifier field */
    private String id;

    /** persistent field */
    private String prefixIdentifier;

    /** nullable persistent field */
    private String classifyIdentifier;

    /** nullable persistent field */
    private Integer serialNumber;



    /** default constructor */
    public SysSerialNumber() {
    }




    /**
     * @return Returns the classifyIdentifier.
     */
    public String getClassifyIdentifier() {
        return classifyIdentifier;
    }




    /**
     * @param classifyIdentifier The classifyIdentifier to set.
     */
    public void setClassifyIdentifier(String classifyIdentifier) {
        this.classifyIdentifier = classifyIdentifier;
    }




    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }




    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }




    /**
     * @return Returns the prefixIdentifier.
     */
    public String getPrefixIdentifier() {
        return prefixIdentifier;
    }




    /**
     * @param prefixIdentifier The prefixIdentifier to set.
     */
    public void setPrefixIdentifier(String prefixIdentifier) {
        this.prefixIdentifier = prefixIdentifier;
    }




    /**
     * @return Returns the serialNumber.
     */
    public Integer getSerialNumber() {
        return serialNumber;
    }




    /**
     * @param serialNumber The serialNumber to set.
     */
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }




    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof SysSerialNumber)) {
            return false;
        }
        SysSerialNumber castOther = (SysSerialNumber) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

}
