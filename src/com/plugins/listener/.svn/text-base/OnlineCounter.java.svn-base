package com.plugins.listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.plugins.listener.utils.PathUtil;

public class OnlineCounter implements HttpSessionListener {

	private static int sessionCount = 0;
	public static int pageviewTotalCount = 0;
	private String XML_PATH = "portal\\data\\counter\\counter.xml";

	public OnlineCounter() {
		super();
		initTotalCount();
		// TODO Auto-generated constructor stub
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		synchronized (OnlineCounter.class) {
			sessionCount++;
			pageviewTotalCount++;
			setCount(pageviewTotalCount);
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		synchronized (OnlineCounter.class) {
			if (sessionCount > 0) {
				sessionCount--;
			}
		}
	}

	/**
	 * @return sessionCount
	 */
	public static synchronized int getSessionCount() {
		if (sessionCount < 1) {
			sessionCount = 1;
		}
		return sessionCount;
	}

	private void initTotalCount() {
		try {

			SAXReader reader = new SAXReader();
			String path = new PathUtil().getWebRoot() + XML_PATH;
			File xmlDoc = new File(path);
			if (xmlDoc.exists()) {
				Document doc = reader.read(xmlDoc);
				Node node = doc.selectSingleNode("//total");
				if (node != null && node.hasContent()) {
					pageviewTotalCount = Integer.parseInt(node.getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean setCount(int count) {
		try {
			SAXReader reader = new SAXReader();
			String path = new PathUtil().getWebRoot() + XML_PATH;
			File xmlDoc = new File(path);
			if (xmlDoc.exists()) {
				Document doc = reader.read(xmlDoc);
				Node node = doc.selectSingleNode("//total");
				if (node != null) {
					node.setText(String.valueOf(count));
					saveXMLDoc(doc);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void saveXMLDoc(Document doc) throws IOException {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			String savePath = new PathUtil().getWebRoot() + XML_PATH;
			XMLWriter writer = new XMLWriter(new FileOutputStream(savePath),
					format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
