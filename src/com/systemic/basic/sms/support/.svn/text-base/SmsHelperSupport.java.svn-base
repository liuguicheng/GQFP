package com.systemic.basic.sms.support;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import org.springline.beans.cache.CacheHelper;
import org.springline.beans.dictionary.IDictionaryMapValueItem;
import org.springline.web.view.support.HtmlHelper;

import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.SmsManageHelper;

public class SmsHelperSupport extends SmsManageHelper {

	/**
	 * 构造函数。将句柄保存进单例变量。
	 */
	public SmsHelperSupport() {
		super.setInstance(this);
	}

	/**
	 * @param content
	 * @param mobileNum
	 * @return Socket
	 */
	public void smsClientSupport(String content, String mobileNum) {
		Socket socket;
		/**
		 * 获取当前时间戳
		 */
		long lognTime = System.currentTimeMillis();
		String StringTime = Long.toString(lognTime);

		/**
		 * 判断手机号!=0||+!=11
		 */
		if (Tools.checkMobile(mobileNum) == false) {
			mobileNum = null;
		} else {

			/**
			 * 取得手机所属的运营商 电话号码 (0移动,1联通,2模拟,3电信)
			 */
			String phoneType = Tools.getMobileType(mobileNum);
			// String IP地址 　端口号　　
			String ip = SmsConfig.getSmsIPUrl();
			String port = SmsConfig.getSmsPortUrl();
			int pro = Integer.parseInt(port);
			try {
				socket = new Socket(ip, pro); // 建立Socket连接

				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());

				String strMsg = "<?xml version=\"1.0\" encoding=\"gbk\"?>\n"
						+ "<Root>\n<Head>\n<TxCode>5100</TxCode>\n"
						+ String.format("<TxId>NNFX_%s</TxId>", StringTime)
						+ "\n"
						+ // 这里加个时间戳
						"<SystemID>NNFX</SystemID>\n"
						+ "</Head>\n"
						+ "<Body>\n"
						+ String.format("<ShortMsg><![CDATA[%s]]></ShortMsg>",content)
						+ "\n"
						+ "<ServiceType>HELP</ServiceType> \n"
						+ "<ServiceNumPostfix>0</ServiceNumPostfix> \n"
						+ String.format("<RecvMobileNum>%s</RecvMobileNum> ",mobileNum)
						+ "\n"
						+ "<FeeMobileNum></FeeMobileNum>\n"
						+ "<dcs>8</dcs> \n"
						+ String.format("<MobileType>%s</MobileType>",phoneType) + "\n"
						+ // 手机号码类型（0--移动 1--联通 3--电信）
						"<MorelatetoMTFlag>0</MorelatetoMTFlag>\n"
						+ "<MsgType>5</MsgType>\n</Body>\n</Root>\n";
				byte[] buffer = strMsg.getBytes();
				byte[] sendBuff = new byte[buffer.length + 12];
				System.arraycopy(buffer, 0, sendBuff, 12, buffer.length);

				intToByte(buffer.length, sendBuff, 0); // 数据包的长度
				intToByte(0, sendBuff, 8); // 传送模式0同步，1异步

				out.write(sendBuff);
				in.read(sendBuff);

				out.close();
				in.close();
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void intToByte(int n, byte[] buf, int offset) {
		buf[offset + 3] = (byte) (n >> 24);
		buf[offset + 2] = (byte) (n >> 16);
		buf[offset + 1] = (byte) (n >> 8);
		buf[offset] = (byte) n;
	}

	public static void main(String[] args) {
		 SmsHelperSupport sk = new SmsHelperSupport();
		 sk.smsClientSupport("我是内容", "13878874644");

	}

	@SuppressWarnings("rawtypes")
	@Override
	public String smsSendClientSupport(String smsType, String mobileNum) {
		String message ="";
		Socket socket;
		Map dicData = (Map)CacheHelper.getInstance().getCacheObject("dicMoibleSmsContent");
		IDictionaryMapValueItem item = (IDictionaryMapValueItem)HtmlHelper.getMapData(dicData, smsType);
		SysSmsType sysSms = null;
		if(item != null){
			sysSms = (SysSmsType) item.getData();
		} 		
		if(sysSms.getSmsContent() == null){
			message ="短信内容不能为空！";
		} else {
		/**
		 * 获取当前时间戳
		 */
		long lognTime = System.currentTimeMillis();
		String StringTime = Long.toString(lognTime);

		/**
		 * 判断手机号!=0||+!=11
		 */
		if (Tools.checkMobile(mobileNum) == false) {
			mobileNum = null;
		} else {

			/**
			 * 取得手机所属的运营商 电话号码 (0移动,1联通,2模拟,3电信)
			 */
			String phoneType = Tools.getMobileType(mobileNum);
			// String IP地址 　端口号　　
			String ip = SmsConfig.getSmsIPUrl();
			String port = SmsConfig.getSmsPortUrl();
			int pro = Integer.parseInt(port);
			try {
				socket = new Socket(ip, pro); // 建立Socket连接

				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());

				String strMsg = "<?xml version=\"1.0\" encoding=\"gbk\"?>\n"
						+ "<Root>\n<Head>\n<TxCode>5100</TxCode>\n"
						+ String.format("<TxId>NNFX_%s</TxId>", StringTime)
						+ "\n"
						+ // 这里加个时间戳
						"<SystemID>NNFX</SystemID>\n"
						+ "</Head>\n"
						+ "<Body>\n"
						+ String.format("<ShortMsg><![CDATA[%s]]></ShortMsg>",sysSms.getSmsContent())
						+ "\n"
						+ "<ServiceType>HELP</ServiceType> \n"
						+ "<ServiceNumPostfix>0</ServiceNumPostfix> \n"
						+ String.format("<RecvMobileNum>%s</RecvMobileNum> ",mobileNum)
						+ "\n"
						+ "<FeeMobileNum></FeeMobileNum>\n"
						+ "<dcs>8</dcs> \n"
						+ String.format("<MobileType>%s</MobileType>",phoneType) + "\n"
						+ // 手机号码类型（0--移动 1--联通 3--电信）
						"<MorelatetoMTFlag>0</MorelatetoMTFlag>\n"
						+ "<MsgType>5</MsgType>\n</Body>\n</Root>\n";
				byte[] buffer = strMsg.getBytes();
				byte[] sendBuff = new byte[buffer.length + 12];
				System.arraycopy(buffer, 0, sendBuff, 12, buffer.length);

				intToByte(buffer.length, sendBuff, 0); // 数据包的长度
				intToByte(0, sendBuff, 8); // 传送模式0同步，1异步

				out.write(sendBuff);
				in.read(sendBuff);

				out.close();
				in.close();
				socket.close();
				message="短信发送成功!";

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	  }
		return message;	
		
	}
	
	public String smsSubClientSupport(String smsType) {
		
		String mobileNum="18176266556";
		String smsContert="测试数据";
		String message="";
		try {
			smsClientSupport(smsContert, mobileNum);
			message="短信发送成功!";
		} catch (Exception e) {
			message="短信发送成功!";
		}
		return message;
		
	}

}
