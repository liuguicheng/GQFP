package com.console.unit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBBackupUnit {

	public static void main(String[] args) {
		dbbackup();
		//load("E://file//20160923023025.sql");
	}

	// 数据库备份
	public static String dbbackup() {
		String hostIP = "192.168.1.133";
		String userName = "root";
		String password = "cisoft";
		String databaseName = "jeecms";
		String savePath = "E:/file/";
		String fileName = "";
		String returnstr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String file = sdf.format(new Date());
		fileName = file + ".sql";
		try {
			boolean fa = exportDatabaseTool(hostIP, userName, password, savePath, fileName, databaseName);
			if (fa) {
				returnstr = savePath + "," + fileName;
				System.out.println("备份成功，路径及文件名：" + returnstr);
			} else {
				System.out.println("备份失败");
			}

		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		}
		return returnstr;

	}

	// 数据库恢复
	public static void load(String filename) {

		 try {
			 String path = "E:\\";
				String commad = "cmd /c " + path+"mysql -u root -pcisoft jeecms "; 
	            Runtime rt = Runtime.getRuntime();
	            // 调用 mysql 的 cmd:
	            Process child = rt.exec(commad);
	            OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流
	            String inStr;
	            StringBuffer sb = new StringBuffer("");
	            String outStr;
	            BufferedReader br = new BufferedReader(new InputStreamReader(
	                    new FileInputStream(filename), "utf8"));
	            while ((inStr = br.readLine()) != null) {
	                sb.append(inStr + "\r\n");
	            }
	            outStr = sb.toString();

	            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
	            writer.write(outStr);
	            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
	            writer.flush();
	            // 别忘记关闭输入输出流
	            out.close();
	            br.close();
	            writer.close();

	            System.out.println("/* Load OK! */");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	}
	

	/**
	 * Java代码实现MySQL数据库备份
	 * 
	 * @author lgc
	 * @param hostIP
	 *            MySQL数据库所在服务器地址IP
	 * @param userName
	 *            进入数据库所需要的用户名
	 * @param password
	 *            进入数据库所需要的密码
	 * @param savePath
	 *            数据库导出文件保存路径
	 * @param fileName
	 *            数据库导出文件文件名
	 * @param databaseName
	 *            要导出的数据库名
	 * @return 返回true表示导出成功，否则返回false。
	 */
	public static boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath,
			String fileName, String databaseName) throws InterruptedException {
		boolean fa = false;
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {// 如果目录不存在
			saveFile.mkdirs();// 创建文件夹
		}
		if (!savePath.endsWith(File.separator)) {
			savePath = savePath + File.separator;
		}

		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
			String backup = "mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " " + databaseName
					+ " --set-charset=UTF8 ";
			String path = "E:\\";
			String commad = "cmd /c " + path + backup + ">" + savePath + fileName;
			System.out.println(commad);
			Runtime.getRuntime().exec(commad);
			fa = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
		return fa;
	}

}
