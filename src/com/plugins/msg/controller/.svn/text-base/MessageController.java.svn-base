package com.plugins.msg.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springline.beans.cache.CacheHelper;
import org.springline.beans.dictionary.IDictionaryMapValueItem;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;
import org.springline.web.view.support.HtmlHelper;

import com.console.ConsoleHelper;
import com.console.entity.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.entity.SysMessage;
import com.plugins.msg.service.IMsgService;
import com.systemic.gq.entity.Member;

public class MessageController extends SpringlineMultiActionController {
	private IMsgService msgService;

	public void setMsgService(IMsgService msgService) {
		this.msgService = msgService;
	}

	/**
	 * 
	 * 消息类型tab切换
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView messageManage(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		model.put("message", request.getParameter("message"));
		model.put("docType", request.getParameter("docType"));
		// 设置显示的tab页
		model.put("tab", request.getParameter("tab"));
		model.put("token", request.getParameter("token"));
		return new ModelAndView(getViewMap().get("messageManageView").toString(), model);
	}

	/**
	 * 已阅消息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView messageRemove(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		String[] ids = request.getParameterValues("msgId");
		try {
			this.msgService.deleteMessage(ids);
			model.put("message", "操作成功！");
		} catch (Exception e) {
			model.put("message", "操作失败！");
		}
		model.put("token", request.getParameter("token"));
		return new ModelAndView(new GBRedirectView(getViewMap().get("messageList").toString()), model);
	}

	/**
	 * 清理所有未读消息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView messageAllRemove(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		String msgIds = request.getParameter("ids");
		if (StringUtils.isNotBlank(msgIds)) {
			String[] ids = msgIds.split(",");
			try {
				this.msgService.deleteMessage(ids);
				model.put("message", "操作成功！");
			} catch (Exception e) {
				e.printStackTrace();
				model.put("message", "操作失败！");
			}
		}
		return null;
	}

	/**
	 * 未读消息列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView messageUnreadedList(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		MessageQueryInfo info = new MessageQueryInfo();
		info.setNotPage(true);
		info.setIsReaded(ConsoleHelper.NO);
		if (info.getStaffId() == null || info.getStaffId().trim().length() < 1) {
			Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
			if (self != null) {
				info.setStaffId(self.getId());
			}
		}
		Page data = this.msgService.selectMessage(info);
		if (data != null && data.getData() != null) {
			model.put("msgList", data.getData());
		}
		return new ModelAndView(getViewMap().get("unreadedView").toString(), model);
	}

	/**
	 * 异步查询系统消息
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	public void messageAsyncList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charSet=gbk");
		PrintWriter out = null;
		String staffId = request.getParameter("staffId");
		try {
			List<SysMessage> msgList = new ArrayList<SysMessage>();
			MessageQueryInfo msgInfo = new MessageQueryInfo();
			msgInfo.setNotPage(true);
			msgInfo.setIsReaded(ConsoleHelper.NO);
			if (StringUtils.isNotBlank(staffId)) {
				msgInfo.setStaffId(staffId);
			} else {
				Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
				msgInfo.setStaffId(self.getId());
			}

			Page msgData = this.msgService.selectMessage(msgInfo);
			if (msgData != null && msgData.getData() != null) {
				msgList.addAll(msgData.getData());
			}
			Gson json = new GsonBuilder().create();
			out = response.getWriter();
			out.print(json.toJson(msgList));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 异步查询聊天消息
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	public void messageChatList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charSet=gbk");
		PrintWriter out = null;
		String staffId = request.getParameter("staffId");
		Map staffMap = (Map) CacheHelper.getInstance().getCacheObject("dicStaff");
		try {

			List<SysMessage> jsonlist = new ArrayList();
			MessageQueryInfo msgInfo = new MessageQueryInfo();
			msgInfo.setNotPage(true);
			msgInfo.setChatState(ConsoleHelper.NO);
			if (StringUtils.isNotBlank(staffId)) {
				msgInfo.setStaffId(staffId);
			} else {
				Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
				msgInfo.setStaffId(self.getId());
			}

			Page msgData = this.msgService.selectMessage(msgInfo);
			List<SysMessage> msgList = msgData.getData();
			for (int i = 0; i < msgList.size(); i++) {
				SysMessage message = msgList.get(i);
				message.setSendManId(message.getSendMan());
				IDictionaryMapValueItem item = (IDictionaryMapValueItem) HtmlHelper.getMapData(staffMap,
						message.getSendMan());
				message.setSendMan(item.getName().toString());

				jsonlist.add(message);
			}
			Gson json = new GsonBuilder().create();
			out = response.getWriter();
			out.print(json.toJson(msgList));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 办公助手：异步查询消息，没有校验
	 * 
	 * @param request
	 * @param response
	 */
	public void msgAsyncList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charSet=gbk");
		PrintWriter out = null;
		String staffId = request.getParameter("staffId");
		try {
			out = response.getWriter();
			if (StringUtils.isNotBlank(staffId)) {
				MessageQueryInfo info = new MessageQueryInfo();
				info.setNotPage(true);
				info.setIsReaded(ConsoleHelper.NO);
				info.setStaffId(staffId);
				Page page = this.msgService.selectMessage(info);
				if (page != null && page.getData() != null & page.getData().size() > 0) {
					Gson json = new GsonBuilder().create();
					out.print(json.toJson(page.getData()));
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 办公助手：清理所有未读消息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView msgAllRemove(HttpServletRequest request, HttpServletResponse response) {
		Map model = new HashMap();
		String msgIds = request.getParameter("ids");
		if (StringUtils.isNotBlank(msgIds)) {
			String[] ids = msgIds.split(",");
			try {
				this.msgService.deleteMessage(ids);
				model.put("message", "操作成功！");
			} catch (Exception e) {
				e.printStackTrace();
				model.put("message", "操作失败！");
			}
		}
		return null;
	}

	/**
	 * to 添加公告 页面
	 */
	public ModelAndView toaddNoticeMessage(HttpServletRequest request, HttpServletResponse response) {
		Map map = doGetMessage(request);
		return new ModelAndView(getViewMap().get("toaddNoticeMessageView").toString(), map);
	}

	/**
	 * 添加公告
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addNoticeMessage(HttpServletRequest request, HttpServletResponse response) {
		addMessage(request,"2");
		return new ModelAndView(new GBRedirectView(getViewMap().get("addNoticeMessageView").toString()), null);
	}
	/**
	 * 删除公告
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView doDelNoticeMessage(HttpServletRequest request, HttpServletResponse response) {
		Map model = delMessage(request);
		return new ModelAndView(new GBRedirectView(getViewMap().get("addNoticeMessageView").toString()), model);
	}
	
	/**
	 * 去添加帮助页面
	 */
	public ModelAndView toaddhelpMessage(HttpServletRequest request, HttpServletResponse response) {
		Map map = doGetMessage(request);
		return new ModelAndView(getViewMap().get("toaddhelpMessageView").toString(), map);
	}

	private Map doGetMessage(HttpServletRequest request) {
		Map map = new HashMap();
		String id = request.getParameter("id");
		if (id != null && !"".equals(id)) {
			SysMessage message = this.msgService.selectMessageById(id);
			map.put("command", message);
		}
		return map;
	}
	
	/**
	 * 添加帮助
	 */
	
	public ModelAndView addHelpMessage(HttpServletRequest request, HttpServletResponse response) {
		addMessage(request,"3");
		return new ModelAndView(new GBRedirectView(getViewMap().get("addhelpMessageView").toString()), null);
	}

	private void addMessage(HttpServletRequest request,String messageType) {
		try {
			String title = request.getParameter("messageTitel");
			String content = request.getParameter("content");
			String id = request.getParameter("sysMessageInfoId");
			if (id != null && !"".equals(id)) {
				SysMessage sysmessage = this.msgService.selectMessageById(id);
				sysmessage.setMessageTitel(title);
				sysmessage.setContent(content);
				this.msgService.updateMessage(sysmessage);
			} else {
				Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
				Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
				this.msgService.insertMessageFor(content, title, messageType, member.getMemberId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除帮助
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView doDelHelpMessage(HttpServletRequest request, HttpServletResponse response) {
		Map model = delMessage(request);
		return new ModelAndView(new GBRedirectView(getViewMap().get("addhelpMessageView").toString()), model);
	}
	

	private Map delMessage(HttpServletRequest request) {
		Map model = new HashMap();
		String msgIds = request.getParameter("id");
		if (StringUtils.isNotBlank(msgIds)) {
			String[] ids = msgIds.split(",");
			try {
				this.msgService.deleteMessageForDel(ids);
				model.put("message", "操作成功！");
			} catch (Exception e) {
				e.printStackTrace();
				model.put("message", "操作失败！");
			}
		}
		return model;
	}
	
	
	
	
	/**
	 * to 发送邮件页面
	 */
	public ModelAndView toaddEmailMessage(HttpServletRequest request, HttpServletResponse response) {
		Map map = doGetMessage(request);
		return new ModelAndView(getViewMap().get("toaddemailMessageView").toString(), map);
	}

	/**
	 * 发送邮件
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addEmailMessage(HttpServletRequest request, HttpServletResponse response) {
		try {
			String title = request.getParameter("messageTitel");
			String content = request.getParameter("content");
			String receiveMan = request.getParameter("receiveMan");
			Staff staff = (Staff) AuthenticationFilter.getAuthenticator(request);
			Member member = ConsoleHelper.getInstance().getManageService().selectMemberByStaffId(staff.getId());
			this.msgService.insertMessageForEmail(receiveMan,content, title, "4", member.getMemberId(),member.getUserName());
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(new GBRedirectView(getViewMap().get("addemailMessageView").toString()), null);
	}
	/**
	 * 删除邮件
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ModelAndView doDelEmailMessage(HttpServletRequest request, HttpServletResponse response) {
		Map model = delMessage(request);
		return new ModelAndView(new GBRedirectView(getViewMap().get("addemailMessageView").toString()), model);
	}

}
