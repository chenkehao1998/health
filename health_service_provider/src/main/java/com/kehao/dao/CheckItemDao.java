package com.kehao.dao;

import com.github.pagehelper.Page;
import com.kehao.pojo.CheckItem;

public interface CheckItemDao {
    public void add(CheckItem checkItem);

    public Page<CheckItem> selectByCondition(String queryString);

    public long findCountByCheckItemId(Integer itemId);

    public void deleteById(int id);

    public CheckItem findById(int id);

    public void edit(CheckItem checkItem);
}
