package com.plugins.pending.service.spring;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.BeanUtils;
import org.springline.orm.Page;

import com.console.entity.Staff;
import com.plugins.pending.command.FinishedItemQueryInfo;
import com.plugins.pending.command.PendingItemQureyInfo;
import com.plugins.pending.common.IPendingItem;
import com.plugins.pending.entity.FinishedItem;
import com.plugins.pending.entity.PendingItem;
import com.plugins.pending.entity.PendingItemRecipients;
import com.plugins.pending.entity.PendingItemRecipientsBak;
import com.plugins.pending.service.IPendingItemService;
import com.plugins.pending.service.dao.IPendingItemDao;

/**
 * @description
 */
public class SpringPendingItemService implements IPendingItemService, IPendingItem {
    /**  */
    private IPendingItemDao pendingItemDao;

    /**
     * @see com.plugins.pending.common.IPendingItem#openItem(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      com.console.entity.Staff, java.util.List)
     */
    public void doOpenItem(String moduleName, String flagId, String url, String title, Staff sender, List recipients,
            Properties p) {
        PendingItem pendingItem = new PendingItem();

        pendingItem.setModuleName(moduleName);
        pendingItem.setFlagId(flagId);
        pendingItem.setUrl(url);
        pendingItem.setTitle(title);
        pendingItem.setSender(sender);
        pendingItem.setSenderName(sender.getName());
        pendingItem.setSendTime(new Date());
        pendingItem.setProps(p);
        this.pendingItemDao.save(pendingItem);
        String staffId;
        for (Iterator iter = recipients.iterator(); iter.hasNext();) {
            Object element = iter.next();
            // 为了兼容其它系统发送待办事宜的需求，要判断Recipients中的内容：原OA系统的Recipients为staff对象列表，而其它系统通过webservice发来的
            // 代办事宜Recipients中的对象为staffId
            if (element instanceof Staff) {
                staffId = ((Staff) element).getId();
            } else {
                staffId = element.toString();
            }
            PendingItemRecipients pr = new PendingItemRecipients();
            pr.setForDoId(pendingItem.getForDoId());
            pr.setRecipientId(staffId);
            this.pendingItemDao.save(pr);

        }
    }

    /**
     * @see com.plugins.pending.common.IPendingItem#closeItem(java.lang.String,
     *      java.lang.String, com.console.entity.Staff)
     */
    public void doCloseItem(String moduleName, String flagId, Staff finisher) {
        PendingItem pendingItem = this.pendingItemDao.getPendingItem(moduleName, flagId);
        if (pendingItem == null) {
            // 运行中的流程，中途加入待办事宜控制，前面环节对应的待办事宜为null，为兼容这种情况，应该在次做空值的判断处理
            return;
        }
        List recipients = this.pendingItemDao.getPendingItemRecipients(pendingItem.getForDoId());

        FinishedItem finishedItem = new FinishedItem();

        BeanUtils.copyProperties(pendingItem, finishedItem);
        finishedItem.setFinisher(finisher);
        finishedItem.setFinisherName(finisher.getName());
        finishedItem.setFinishTime(new Date());

        this.pendingItemDao.save(finishedItem);

        for (Iterator iter = recipients.iterator(); iter.hasNext();) {
            PendingItemRecipients element = (PendingItemRecipients) iter.next();
            PendingItemRecipientsBak pb = new PendingItemRecipientsBak();

            BeanUtils.copyProperties(element, pb);
            pb.setFinishedId(finishedItem.getFinishedId());

            this.pendingItemDao.save(pb);

        }

        for (Iterator iter = recipients.iterator(); iter.hasNext();) {
            PendingItemRecipients element = (PendingItemRecipients) iter.next();
            this.pendingItemDao.delete(element);
        }
        this.pendingItemDao.delete(pendingItem);
    }

    /**
     * @see com.plugins.pending.common.IPendingItem#selectPendingItemList(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Page selectPendingItemList(String userId, String moduleName) {
        PendingItemQureyInfo info = new PendingItemQureyInfo();
        info.setNotPage(true);
        info.setRecipientId(userId);
        info.setModuleName(moduleName);
        return this.selectPendingItem(info);
    }

    /**
     * @param pendingItemDao
     *            要设置的 pendingItemDao。
     */
    public void setPendingItemDao(IPendingItemDao pendingItemDao) {
        this.pendingItemDao = pendingItemDao;
    }

    /**
     * 获取待办事项里各分类的统计数
     * 
     * @see com.plugins.pending.service.IPendingItemService#selectPendingItemCount(java.lang.String)
     */
    public Integer selectPendingItemCount(String modulename, String staffId) {
        Integer count = new Integer(0);
        List list = this.pendingItemDao.selectPendingItemCount(modulename, staffId);
        if (list != null && list.get(0) != null) {
            count = (Integer) list.get(0);
        }
        return count;
    }

    /**
     * @see com.plugins.pending.service.IPendingItemService#selectPendingItem(com.plugins.pending.command.PendingItemQureyInfo)
     */
    public Page selectPendingItem(PendingItemQureyInfo info) {
        return this.pendingItemDao.selectPendingItem(info);
    }

    /**
     * @see com.plugins.pending.service.IPendingItemService#selectFinishedItems(com.plugins.pending.command.FinishedItemQueryInfo)
     */
    public Page selectFinishedItems(FinishedItemQueryInfo info) {
        return this.pendingItemDao.selectFinishedItems(info);
    }

}
