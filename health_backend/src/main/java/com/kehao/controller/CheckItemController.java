package com.kehao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kehao.constant.MessageConstant;
import com.kehao.entity.Result;
import com.kehao.pojo.CheckItem;
import com.kehao.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try{
            checkItemService.add(checkItem);
        }catch (Exception e){
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }

        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
}
