package com.plugins.pending.common;

public abstract class PendingItemHelper {

    /** 处理待办的标识 */
    public static String FLAG_PENDINGITEM = "pendingItemFlag";

    /** 待办模块的Url */
    private static String pendingItemBackUrl = "";

    /**
     *
     */
    private static IPendingItem pendingItem;

    /**
     * @param pendingItem
     *            IPendingItem
     */
    protected void setPendingItem(IPendingItem pendingItem) {
        PendingItemHelper.pendingItem = pendingItem;
    }

    /**
     * @return IPendingItem
     */
    public static IPendingItem getInstance() {
        return pendingItem;
    }

    /**
     * 处理业务后返回待办模块的Url
     * 
     * @return
     */
    public static String getPendingItemBackUrl() {
        return pendingItemBackUrl;
    }

    protected void setPendingItemBackUrl(String url) {
        PendingItemHelper.pendingItemBackUrl = url;

    }
}
