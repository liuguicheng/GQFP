package com.console.backup.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.view.GBRedirectView;

import com.console.backup.command.DBBackupEdit;
import com.console.backup.service.IDBBackupService;
import com.console.entity.DBBackup;
import com.console.unit.DBBackupUnit;

public class DBBackupManageController extends SpringlineMultiActionController {

	private IDBBackupService dbbackupService;

	/**
	 * @param dbbackupService
	 *            the dbbackupService to set
	 */
	public void setDbbackupService(IDBBackupService dbbackupService) {
		this.dbbackupService = dbbackupService;
	}

	public ModelAndView addBackup(HttpServletRequest arg0, HttpServletResponse arg1, DBBackupEdit bedit) {

		String dburl = DBBackupUnit.dbbackup();
		if (!dburl.equals("")) {
			String[] dburlstr = dburl.split(",");
			String filename = dburlstr[1];
			String fileurl = dburlstr[0] + "/" + dburlstr[1];
			bedit.setDb_time(new Date());
			bedit.setDb_bskcupName(filename);
			bedit.setDb_bskcpuUrl(fileurl);
			dbbackupService.save(bedit);
		}
		Map model = new HashMap();
		model.put("message", "备份成功");
		return new ModelAndView(new GBRedirectView("../db/backuplist.do"), model);
	}

	public ModelAndView recoveryBackup(HttpServletRequest arg0, HttpServletResponse arg1) {
		Map model = new HashMap();
		String id = arg0.getParameter("db_id");
		DBBackup bdbackupinf = dbbackupService.loadById(id);
		if (bdbackupinf != null) {
			if (!bdbackupinf.getDb_bskcpuUrl().equals("")) {
				DBBackupUnit.load(bdbackupinf.getDb_bskcpuUrl());
				model.put("message", "恢复成功");
			}
		}

		return new ModelAndView(new GBRedirectView("../db/backuplist.do"), model);
	}

	public ModelAndView delBackup(HttpServletRequest arg0, HttpServletResponse arg1) {
		String id = arg0.getParameter("db_id");
		DBBackup bdbackupinf = dbbackupService.loadById(id);
		if (bdbackupinf != null) {
			dbbackupService.delete(bdbackupinf);
		}
		Map model = new HashMap();
		model.put("message", "删除成功");
		return new ModelAndView(new GBRedirectView("../db/backuplist.do"), model);
	}

	public String fileDownload(HttpServletRequest arg0, HttpServletResponse response) {
		String filename = "";
		String fileurl = "";
		String id = arg0.getParameter("db_id");
		DBBackup bdbackupinf = dbbackupService.loadById(id);
		if (bdbackupinf != null) {
			filename = bdbackupinf.getDb_bskcupName();
			fileurl = bdbackupinf.getDb_bskcpuUrl();

			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
			try {
				// String path = Thread.currentThread().getContextClassLoader()
				// .getResource("").getPath()
				// + "download";//这个download目录为啥建立在classes下的
				InputStream inputStream = new FileInputStream(new File(fileurl));

				OutputStream os = response.getOutputStream();
				byte[] b = new byte[2048];
				int length;
				while ((length = inputStream.read(b)) > 0) {
					os.write(b, 0, length);
				}

				// 这里主要关闭。
				os.close();

				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		// 返回值要注意，要不然就出现下面这句错误！
		// java+getOutputStream() has already been called for this response
		return null;
	}

}
