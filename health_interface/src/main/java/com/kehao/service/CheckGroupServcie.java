package com.kehao.service;

import com.kehao.entity.PageResult;
import com.kehao.entity.QueryPageBean;
import com.kehao.pojo.CheckGroup;
import com.kehao.pojo.CheckItem;

import java.util.List;

public interface CheckGroupServcie {
    void add(int[] checkitemIds, CheckGroup checkGroup);

    PageResult findPage(QueryPageBean queryPageBean);

    CheckGroup findById(int id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(int[] checkitemIds, CheckGroup checkGroup);

    List<CheckItem> findAll();
}
