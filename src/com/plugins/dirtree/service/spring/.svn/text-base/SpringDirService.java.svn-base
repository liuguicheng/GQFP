package com.plugins.dirtree.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springline.beans.dictionary.DictionaryLoader;
import org.springline.beans.tree.ITreeNode;
import org.springline.beans.tree.support.paternity.PaternityTreeBuilder;

import com.plugins.dirtree.command.DirDataEditInfo;
import com.plugins.dirtree.command.DirTypeEditInfo;
import com.plugins.dirtree.entity.DirData;
import com.plugins.dirtree.entity.DirType;
import com.plugins.dirtree.service.IDirService;
import com.plugins.dirtree.service.dao.IDirDataDao;
import com.plugins.dirtree.service.dao.IDirTypeDao;

public class SpringDirService implements IDirService, InitializingBean {

    private IDirTypeDao dirTypeDao;
    private IDirDataDao dirDataDao;

    public void setDirTypeDao(IDirTypeDao dirTypeDao) {
        this.dirTypeDao = dirTypeDao;
    }

    public void setDirDataDao(IDirDataDao dirDataDao) {
        this.dirDataDao = dirDataDao;
    }

    public void deleteDirType(String[] ids) {
        // TODO Auto-generated method stub

        for (String id : ids) {
            DirType dirtype = selectDirType(id);
            dirtype.setIsValid("0");
            dirTypeDao.update(dirtype);

            DictionaryLoader.getInstance().getAllDictionary().remove(
                    dirtype.getDirTypeCode());
        }
    }

    public boolean saveDirType(DirTypeEditInfo info) {
        // TODO Auto-generated method stub
        boolean sucess = false;
        try {
            String oldcode = null;
            DirType dirtype = null;

            if (info.getDirTypeId() != null
                    && info.getDirTypeId().trim().length() > 0) {
                dirtype = selectDirType(info.getDirTypeId());
                oldcode = dirtype.getDirTypeCode();
            }

            if (dirTypeDao.checkEditDirTypeCode(oldcode, info.getDirTypeCode()))
                throw new RuntimeException("类型标识『"
                        + info.getDirTypeCode() + "』已被使用，请重新编辑类型标识！");

            if (dirtype == null) {
                dirtype = new DirType();
                BeanUtils.copyProperties(dirtype, info);

                refleshDirDataDic(0, dirtype.getDirTypeId(), oldcode, dirtype
                        .getDirTypeCode());
            } else {
                org.springframework.beans.BeanUtils.copyProperties(info,
                        dirtype, new String[] { "dirTypeId", "createTime",
                                "isValid" });

                if (!info.getDirTypeCode().equals(oldcode))
                    refleshDirDataDic(2, dirtype.getDirTypeId(), oldcode,
                            dirtype.getDirTypeCode());
            }

            dirTypeDao.save(dirtype);
            sucess = true;

        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return sucess;
    }

    public List selectAllDirTypes() {
        return dirTypeDao.loadAll(DirType.class, "sortOrder");
    }

    public DirType selectDirType(String dirTypeId) {
        try {
            return (DirType) dirTypeDao.load(DirType.class, dirTypeId);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void doDirTypeSort(String[] ids) {
        DirType dirtype;
        for (int i = 0; i < ids.length; i++) {
            dirtype = this.selectDirType(ids[i]);
            dirtype.setSortOrder(new Integer(i + 1));
            dirTypeDao.update(dirtype);
        }
    }

    public void doDirTypeUse(String id) {
        DirType dirtype = this.selectDirType(id);
        dirtype.setIsValid("1");
        dirTypeDao.update(dirtype);

        refleshDirDataDic(1, dirtype.getDirTypeId(), dirtype.getDirTypeCode(),
                dirtype.getDirTypeCode());
    }

    public List selectValidDirTypes() {
        return dirTypeDao.selectValidDirTypes();
    }

    public DirType selectDirTypeByCode(String dirTypeCode) {
        return dirTypeDao.selectDirTypeByCode(dirTypeCode);
    }

    /**
     * 将目录类型添加到字典里
     *
     * @param type
     *            ： 0 添加类型，1 设置类型为有效, 2 修改类型名称
     */
    private void refleshDirDataDic(int type, String typeid, String oldtypecode,
            String typecode) {
        List datalist = null;

        if (type == 2) {
            if (DictionaryLoader.getInstance().getAllDictionary().containsKey(
                    oldtypecode))
                DictionaryLoader.getInstance().getAllDictionary().remove(
                        oldtypecode);
        }
        if (!DictionaryLoader.getInstance().getAllDictionary().containsKey(
                typecode)) {
            if (type == 0)
                datalist = new java.util.ArrayList();
            else
                datalist = this.dirDataDao.selectDirData(typeid, DirData.NORMAL_STATE);

            DictionaryLoader.getInstance().registerData(typecode,
                    PaternityTreeBuilder.createTree(datalist));
        }
    }

    public List selectDirDataTreeByType(String typecode, String parentid) {
        // TODO Auto-generated method stub
        ITreeNode rootNode = (ITreeNode) DictionaryLoader.getInstance()
                .getAllDictionary().get(typecode);
        if (rootNode != null) {
            if (parentid != null && parentid.trim().length() > 0) {
                List result = new ArrayList();
                result.add(rootNode.find(parentid));
                return result;
            }
            return rootNode.getChildren();
        } else {
            return null;
        }
    }

    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        try {
            List<DirType> typelist = this.dirTypeDao.selectValidDirTypes();
            List datalist;
            for (DirType type : typelist) {
                datalist = dirDataDao.selectDirData(type.getDirTypeId(),DirData.NORMAL_STATE);
                DictionaryLoader.getInstance().registerData(
                        type.getDirTypeCode(),
                        PaternityTreeBuilder.createTree(datalist));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public DirData selectDirData(String id) {
        // TODO Auto-generated method stub
        return (DirData) dirDataDao.load(DirData.class, id);
    }

    public boolean saveDirData(DirDataEditInfo info) {
        // TODO Auto-generated method stub
        boolean sucess = false;
        try {
            DirData dirdata = null;
            if (info.getDirId() != null && info.getDirId().trim().length() > 0) {
                dirdata = this.selectDirData(info.getDirId());

                org.springframework.beans.BeanUtils.copyProperties(info,
                        dirdata, new String[] { "dirId", "createTime",
                                "isValid" });
            }
            if (dirdata == null) {
                dirdata = new DirData();
                BeanUtils.copyProperties(dirdata, info);
            }
            dirDataDao.save(dirdata);
            sucess = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return sucess;
    }

    public List selectDirDataList(String typeid, String parentid) {
        // TODO Auto-generated method stub
        return dirDataDao.selectDirDataBy(typeid, parentid, null);
    }

    public void doRefreshDirDataDic(String dirtypeid, String dirTypecode) {
        // TODO Auto-generated method stub
        if (DictionaryLoader.getInstance().getAllDictionary().containsKey(
                dirTypecode)) {
            List datalist = dirDataDao.selectDirData(dirtypeid, DirData.NORMAL_STATE);
            DictionaryLoader.getInstance().refreshData(dirTypecode,
                    PaternityTreeBuilder.createTree(datalist));
        }
    }

    public void doDirDataDelete(String dirDataid) {
        // TODO Auto-generated method stub
        DirData data = this.selectDirData(dirDataid);
        data.setIsValid(DirData.HIDDEN_STATE);
        dirDataDao.save(data);
    }

    public void doDirDataUse(String dirDataid) {
        // TODO Auto-generated method stub
        DirData data = this.selectDirData(dirDataid);
        data.setIsValid(DirData.NORMAL_STATE);
        dirDataDao.save(data);
    }

    public void doDirDataSort(String[] ids) {
        // TODO Auto-generated method stub
        try {
            if (ids != null) {
                DirData data;
                for (int i = 0; i < ids.length; i++) {
                    data = this.selectDirData(ids[i]);
                    data.setSortOrder(i + 1);
                    dirDataDao.save(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer selectUsableDirDataOrderNumber(String typeId,String parentId) {
        // TODO Auto-generated method stub
        return this.dirDataDao.selectUsableOrderNumber(typeId,parentId);
    }

    @Override
    public Integer selectUsableDirTypeOrderNumber() {
        // TODO Auto-generated method stub
        return this.dirTypeDao.selectUsableOrderNumber();
    }
}
