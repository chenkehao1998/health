package com.kehao.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kehao.constant.MessageConstant;
import com.kehao.dao.CheckItemDao;
import com.kehao.entity.PageResult;
import com.kehao.entity.QueryPageBean;
import com.kehao.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckItem> checkItems = checkItemDao.selectByCondition(queryPageBean.getQueryString());
        return new PageResult(checkItems.getTotal(),checkItems.getResult());

    }

    @Override
    public void deleteById(int id) {
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count>0){
            throw new RuntimeException(MessageConstant.CHECKITEM_HAS_ASSCIATION);
        }else {
            checkItemDao.deleteById(id);
        }
    }

    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }
}
