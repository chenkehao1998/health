package com.kehao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kehao.constant.MessageConstant;
import com.kehao.entity.PageResult;
import com.kehao.entity.QueryPageBean;
import com.kehao.entity.Result;
import com.kehao.pojo.CheckGroup;
import com.kehao.pojo.CheckItem;
import com.kehao.service.CheckGroupServcie;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupServcie checkGroupService;

    @RequestMapping("/add")
    public Result add(int[] checkitemIds,@RequestBody CheckGroup checkGroup){
        try{
            checkGroupService.add(checkitemIds,checkGroup);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }

        return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult result = checkGroupService.findPage(queryPageBean);
        return result;
    }

    @RequestMapping("/findById")
    public Result findById(int id){
        try{
            CheckGroup item = checkGroupService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,item);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer id){
        try{
            List<Integer> checkitemIds =
                    checkGroupService.findCheckItemIdsByCheckGroupId(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(int[] checkitemIds,@RequestBody CheckGroup checkGroup){
        try{
            checkGroupService.edit(checkitemIds,checkGroup);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }

        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<CheckItem> list = checkGroupService.findAll();
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }


}
