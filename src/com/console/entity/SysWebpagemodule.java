package com.console.entity;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *        @hibernate.class
 *         table="sys_webpagemodule"
 *
*/
public class SysWebpagemodule implements Serializable {

    /**     *     */
    private static final long serialVersionUID = 7907312695306052705L;

    /** identifier field */
    private String pagemodid;

    /** nullable persistent field */
    private String pagemodname;

    /** nullable persistent field */
    private String webpagemodpath;

    /** nullable persistent field */
    private String remark;

    /** nullable persistent field */
    private String exampleurl;


    /** default constructor */
    public SysWebpagemodule() {
    }





    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
            .append("pagemodid", getPagemodid())
            .toString();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof SysWebpagemodule)) {
            return false;
        }
        SysWebpagemodule castOther = (SysWebpagemodule) other;
        return new EqualsBuilder()
            .append(this.getPagemodid(), castOther.getPagemodid())
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder()
            .append(getPagemodid())
            .toHashCode();
    }





    /**
     * @return Returns the exampleurl.
     */
    public String getExampleurl() {
        return exampleurl;
    }





    /**
     * @param exampleurl The exampleurl to set.
     */
    public void setExampleurl(String exampleurl) {
        this.exampleurl = exampleurl;
    }





    /**
     * @return Returns the pagemodid.
     */
    public String getPagemodid() {
        return pagemodid;
    }





    /**
     * @param pagemodid The pagemodid to set.
     */
    public void setPagemodid(String pagemodid) {
        this.pagemodid = pagemodid;
    }





    /**
     * @return Returns the pagemodname.
     */
    public String getPagemodname() {
        return pagemodname;
    }





    /**
     * @param pagemodname The pagemodname to set.
     */
    public void setPagemodname(String pagemodname) {
        this.pagemodname = pagemodname;
    }





    /**
     * @return Returns the remark.
     */
    public String getRemark() {
        return remark;
    }





    /**
     * @param remark The remark to set.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }





    /**
     * @return Returns the webpagemodpath.
     */
    public String getWebpagemodpath() {
        return webpagemodpath;
    }





    /**
     * @param webpagemodpath The webpagemodpath to set.
     */
    public void setWebpagemodpath(String webpagemodpath) {
        this.webpagemodpath = webpagemodpath;
    }

}
