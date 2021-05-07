package com.kehao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kehao.constant.MessageConstant;
import com.kehao.entity.Result;
import com.kehao.pojo.Setmeal;
import com.kehao.service.CheckGroupServcie;
import com.kehao.service.SetmealService;
import com.kehao.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetMealController {

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        String originalFilename = imgFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString()+suffix;
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(true,"input success",fileName);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try {
            setmealService.add(setmeal,checkgroupIds);
        }catch (Exception e){
            //新增套餐失败
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        //新增套餐成功
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

}
