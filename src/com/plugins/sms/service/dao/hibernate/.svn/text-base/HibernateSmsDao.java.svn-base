package com.plugins.sms.service.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.command.SmsReplyQueryInfo;
import com.plugins.sms.entity.SysSms;
import com.plugins.sms.entity.SysSmsHistory;
import com.plugins.sms.entity.SysSmsReply;
import com.plugins.sms.entity.SysSmsReplyHistory;
import com.plugins.sms.entity.VSysSms;
import com.plugins.sms.entity.VSysSmsReply;
import com.plugins.sms.service.dao.ISmsDao;

public class HibernateSmsDao extends HibernateCommonDao implements ISmsDao {

    /** ≤È—Øπ§æﬂ */
    private IQueryStringUtil smsQueryUtil;
    private IQueryStringUtil smsHistoryQueryUtil;
    private IQueryStringUtil vsmsQueryUtil;
    private IQueryStringUtil replyQueryUtil;
    private IQueryStringUtil replyHistoryQueryUtil;
    private IQueryStringUtil vReplyQueryUtil;

    public void setSmsQueryUtil(IQueryStringUtil smsQueryUtil) {
        this.smsQueryUtil = smsQueryUtil;
    }

    public void setSmsHistoryQueryUtil(IQueryStringUtil smsHistoryQueryUtil) {
        this.smsHistoryQueryUtil = smsHistoryQueryUtil;
    }

    public void setVsmsQueryUtil(IQueryStringUtil vsmsQueryUtil) {
        this.vsmsQueryUtil = vsmsQueryUtil;
    }

    public void setReplyQueryUtil(IQueryStringUtil replyQueryUtil) {
        this.replyQueryUtil = replyQueryUtil;
    }

    public void setReplyHistoryQueryUtil(IQueryStringUtil replyHistoryQueryUtil) {
        this.replyHistoryQueryUtil = replyHistoryQueryUtil;
    }

    public void setvReplyQueryUtil(IQueryStringUtil vReplyQueryUtil) {
        this.vReplyQueryUtil = vReplyQueryUtil;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page selectSmsPage(SmsQueryInfo info) {
        StringBuffer where = new StringBuffer();
        List<Object> objects = new ArrayList<Object>();
        if (info.getSendTimeUp() != null) {
            where.append("s.sendTime >= ? and");
            objects.add(info.getSendTimeUp());
        }
        if (info.getSendTimeDown() != null) {
            where.append("s.sendTime <= ?  and");
            objects.add(info.getSendTimeDown());
        }
        if (StringUtils.isNotBlank(info.getSmsStates())) {
            where.append("s.smsState in (?) and");
            objects.add(info.getSmsStates());
        }
        if (StringUtils.isNotBlank(info.getSmsExpStates())) {
            where.append("s.smsState not in (?) and");
            objects.add(info.getSmsExpStates());
        }
        IQueryObject qo = this.smsQueryUtil.getQueryObject(info, where.toString(), objects.toArray());
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List<SysSms> data = this.doQuery(qo.getQueryString(), qo.getParam());
            return this.putDataToPage(data);
        }
        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page selectSmsHistoryPage(SmsQueryInfo info) {
        StringBuffer where = new StringBuffer();
        List<Object> objects = new ArrayList<Object>();
        if (info.getSendTimeUp() != null) {
            where.append(" s.sendTime >= ? and");
            objects.add(info.getSendTimeUp());
        }
        if (info.getSendTimeDown() != null) {
            where.append(" s.sendTime <= ?  and");
            objects.add(info.getSendTimeDown());
        }
        if (StringUtils.isNotBlank(info.getSmsStates())) {
            where.append("s.smsState in (?) and");
            objects.add(info.getSmsStates());
        }
        if (StringUtils.isNotBlank(info.getSmsExpStates())) {
            where.append("s.smsState not in (?) and");
            objects.add(info.getSmsExpStates());
        }
        IQueryObject qo = this.smsHistoryQueryUtil.getQueryObject(info, where.toString(), objects.toArray());
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List<SysSmsHistory> data = this.doQuery(qo.getQueryString(), qo.getParam());
            return this.putDataToPage(data);
        }
        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page selectSmsViewPage(SmsQueryInfo info) {
        StringBuffer where = new StringBuffer();
        List<Object> objects = new ArrayList<Object>();
        if (info.getSendTimeUp() != null) {
            where.append(" sendTime >= ? and");
            objects.add(info.getSendTimeUp());
        }
        if (info.getSendTimeDown() != null) {
            where.append(" sendTime <= ?  and");
            objects.add(info.getSendTimeDown());
        }
        if (StringUtils.isNotBlank(info.getSmsStates())) {
            where.append("s.smsState in (?) and");
            objects.add(info.getSmsStates());
        }
        if (StringUtils.isNotBlank(info.getSmsExpStates())) {
            where.append("s.smsState not in (?) and");
            objects.add(info.getSmsExpStates());
        }
        IQueryObject qo = this.vsmsQueryUtil.getQueryObject(info, where.toString(), objects.toArray());
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List<VSysSms> data = this.doQuery(qo.getQueryString(), qo.getParam());
            return this.putDataToPage(data);
        }
        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page selectSmsReplyPage(SmsReplyQueryInfo info) {
        StringBuffer where = new StringBuffer();
        List<Object> objects = new ArrayList<Object>();
        if (info.getReplyTimeUp() != null) {
            where.append(" replyTime >= ? and");
            objects.add(info.getReplyTimeUp());
        }
        if (info.getReplyTimeDown() != null) {
            where.append(" replyTime <= ?  and");
            objects.add(info.getReplyTimeDown());
        }
        IQueryObject qo = this.replyQueryUtil.getQueryObject(info, where.toString(), objects.toArray());
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List<SysSmsReply> data = this.doQuery(qo.getQueryString(), qo.getParam());
            return this.putDataToPage(data);
        }
        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page selectSmsReplyHistoryPage(SmsReplyQueryInfo info) {
        StringBuffer where = new StringBuffer();
        List<Object> objects = new ArrayList<Object>();
        if (info.getReplyTimeUp() != null) {
            where.append(" replyTime >= ? and");
            objects.add(info.getReplyTimeUp());
        }
        if (info.getReplyTimeDown() != null) {
            where.append(" replyTime <= ?  and");
            objects.add(info.getReplyTimeDown());
        }
        IQueryObject qo = this.replyHistoryQueryUtil.getQueryObject(info, where.toString(), objects.toArray());
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List<SysSmsReplyHistory> data = this.doQuery(qo.getQueryString(), qo.getParam());
            return this.putDataToPage(data);
        }
        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page selectSmsReplyViewPage(SmsReplyQueryInfo info) {
        StringBuffer where = new StringBuffer();
        List<Object> objects = new ArrayList<Object>();
        if (info.getReplyTimeUp() != null) {
            where.append(" replyTime >= ? and");
            objects.add(info.getReplyTimeUp());
        }
        if (info.getReplyTimeDown() != null) {
            where.append(" replyTime <= ?  and");
            objects.add(info.getReplyTimeDown());
        }
        IQueryObject qo = this.vReplyQueryUtil.getQueryObject(info, where.toString(), objects.toArray());
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List<VSysSmsReply> data = this.doQuery(qo.getQueryString(), qo.getParam());
            return this.putDataToPage(data);
        }
        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
    }

}
