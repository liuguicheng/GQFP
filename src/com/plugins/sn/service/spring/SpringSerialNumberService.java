
package com.plugins.sn.service.spring;

import java.util.List;

import com.plugins.sn.entity.SysSerialNumber;
import com.plugins.sn.service.ISerialNumberService;
import com.plugins.sn.service.dao.ISerialNumberDao;


public class SpringSerialNumberService implements ISerialNumberService {

    /**
     *
     */
    private ISerialNumberDao serialNumberDao;

    /**
     * @see com.plugins.sn.service.ISerialNumberService#getSerialNumber(java.lang.String, java.lang.String, int)
     */
    @Override
	public String getSerialNumber(String classify, String prefix, int length) {
        return getSerialNumber(classify, prefix, length, true);
    }

    /**
     * @see com.plugins.sn.service.ISerialNumberService#getSerialNumber(java.lang.String, java.lang.String)
     */
    @Override
	public String getSerialNumber(String classify, String prefix) {
        return getSerialNumber(classify, prefix, true);
    }

    /**
     * @see com.plugins.sn.service.ISerialNumberService#getSerialNumber(java.lang.String, java.lang.String)
     */
    @Override
	public String getSerialNumber(String classifyIdentifier, String prefixIdentifier, boolean withPrefix) {
        synchronized (SpringSerialNumberService.class) {
			String tmp = createNumber(classifyIdentifier, prefixIdentifier).toString();
            if (withPrefix) {
                return prefixIdentifier + tmp;
            }
            return tmp;
        }
    }

    /**
     * @param classifyIdentifier classifyIdentifier
     * @param prefixIdentifier prefixIdentifier
     * @return 数字编号
     */
	private Integer createNumber(String classifyIdentifier, String prefixIdentifier) {
        List list = this.serialNumberDao.selectSysSerialNumber(classifyIdentifier, prefixIdentifier);
        SysSerialNumber sn;
        if (list == null || list.size() <= 0) {
            sn = new SysSerialNumber();
            sn.setClassifyIdentifier(classifyIdentifier);
            sn.setPrefixIdentifier(prefixIdentifier);
            sn.setSerialNumber(new Integer(0));
        } else {
            sn = (SysSerialNumber) list.get(0);
        }
        Integer value = new Integer(sn.getSerialNumber().intValue() + 1);
        sn.setSerialNumber(value);
        this.serialNumberDao.save(sn);
		// return value.toString();
		return value;// LiuYu 20150406 修改返回值类型为Integer，便于后面直接处理。
    }

    /**
     * @see com.plugins.sn.service.ISerialNumberService#getSerialNumber(java.lang.String, java.lang.String, int)
     */
    @Override
	public String getSerialNumber(String classifyIdentifier, String prefixIdentifier, int length, boolean withPrefix) {
        synchronized (SpringSerialNumberService.class) {
			Integer no = createNumber(classifyIdentifier, prefixIdentifier);
			// while (tmp.length() < length) {
			// tmp = "0" + tmp;
			// }
			String tmp = String.format("%0" + length + "d", no); // LiuYu
																	// 20150406
																	// 直接使用format方法格式化
            if (withPrefix) {
                return prefixIdentifier + tmp;
            }
            return tmp;
        }
    }

    /**
     * @param serialNumberDao The serialNumberDao to set.
     */
    public void setSerialNumberDao(ISerialNumberDao serialNumberDao) {
        this.serialNumberDao = serialNumberDao;
    }

}
