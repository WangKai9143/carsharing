package com.wangkai.information.controller;

/**
 * Created by Administrator on 2019/11/27.
 */


import com.wangkai.information.bean.InforBean;
import com.wangkai.information.service.InforService;
import com.wangkai.utils.ResultDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequestMapping("/info")
@RestController
public class InforController {
    @Autowired
    private InforService inforService;


    @GetMapping("/lists")
    public String lists(){
        Collection<InforBean> inforBeans =  inforService.lists();
        return ResultDataUtils.build(200, "操作成功",inforBeans);
    }

    @GetMapping("/details")
    public String queryDetails(String id){
        InforBean inforBean = inforService.getDetails(id);
        return ResultDataUtils.build(200, "操作成功",inforBean);
    }

    @PostMapping("/add")
    public String handleAdd(InforBean inforBean){
        final double d = Math.random();
        final int id = (int)(d*100);
        inforBean.setId(id);
        inforService.add(inforBean);
        return ResultDataUtils.build(200, "操作成功",inforBean.getId());
    }


}
