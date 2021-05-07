package com.kehao.dao;

import com.github.pagehelper.Page;
import com.kehao.pojo.CheckGroup;
import com.kehao.pojo.CheckItem;

import java.util.HashMap;
import java.util.List;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(HashMap<String, Integer> map);

    Page<CheckGroup> selectByCondition(String queryString);

    CheckGroup findById(int id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void deleteAssociation(Integer id);

    void edit(CheckGroup checkGroup);

    List<CheckItem> findAll();
}
