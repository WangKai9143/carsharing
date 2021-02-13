package com.wangkai.information.controller;

/**
 * Created by Administrator on 2019/11/27.
 */


import com.wangkai.information.bean.InfoBean;
import com.wangkai.information.service.InforService;
import com.wangkai.utils.ResultDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/info")
@RestController
public class InfoController {
    @Autowired
    private InforService inforService;


    @GetMapping("/lists")
    public String lists(@RequestParam Map<String,Object> paramsMap){
        Collection<InfoBean> infoBeans =  inforService.listAllInfo(paramsMap);
        return ResultDataUtils.build(200, "操作成功", infoBeans);
    }

    @GetMapping("/details")
    public String queryDetails(String id){
        InfoBean infoBean = inforService.getDetails(id);
        return ResultDataUtils.build(200, "操作成功", infoBean);
    }

    @PostMapping("/add")
    public String handleAdd(InfoBean infoBean){
        final double d = Math.random();
        final int id = (int)(d*100);
        infoBean.setId(id);
        inforService.add(infoBean);
        return ResultDataUtils.build(200, "操作成功", infoBean.getId());
    }


}
