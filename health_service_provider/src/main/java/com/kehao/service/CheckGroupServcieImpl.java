package com.kehao.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kehao.dao.CheckGroupDao;
import com.kehao.entity.PageResult;
import com.kehao.entity.QueryPageBean;
import com.kehao.pojo.CheckGroup;
import com.kehao.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CheckGroupServcieImpl implements CheckGroupServcie {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void add(int[] checkitemIds, CheckGroup checkGroup) {
        checkGroupDao.add(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckGroup> page = checkGroupDao.selectByCondition(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    public void edit(int[] checkitemIds, CheckGroup checkGroup) {
        checkGroupDao.deleteAssociation(checkGroup.getId());
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
        checkGroupDao.edit(checkGroup);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkGroupDao.findAll();
    }

    private void setCheckGroupAndCheckItem(Integer checkGroupId, int[] checkitemIds) {
        if(checkitemIds!=null && checkitemIds.length>0){
            for (int checkitemId : checkitemIds) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("checkGroupId",checkGroupId);
                map.put("checkitemId",checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
