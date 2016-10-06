package com.plugins.pending.common.support;

import org.springframework.beans.factory.InitializingBean;

import com.plugins.pending.common.IPendingItem;
import com.plugins.pending.common.PendingItemHelper;

public class SimplePendingItemFactory extends PendingItemHelper implements InitializingBean {

    /**
     *
     */
    private IPendingItem pendingItem;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        super.setPendingItem(this.pendingItem);
    }

    /**
     * @param pendingItem
     *            the pendingItem to set
     */
    public void setPendingItem(IPendingItem pendingItem) {
        this.pendingItem = pendingItem;
    }

    public void setPendingItemBackUrl(String pendingItemBackUrl) {
        super.setPendingItemBackUrl(pendingItemBackUrl);
    }

}
