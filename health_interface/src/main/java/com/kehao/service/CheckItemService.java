package com.kehao.service;

import com.kehao.entity.PageResult;
import com.kehao.entity.QueryPageBean;
import com.kehao.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    public void add(CheckItem checkItem);

    public PageResult findPage(QueryPageBean queryPageBean);

    public void deleteById(int id);

    public CheckItem findById(int id);

    void edit(CheckItem checkItem);

    List<CheckItem> findAll();
}
