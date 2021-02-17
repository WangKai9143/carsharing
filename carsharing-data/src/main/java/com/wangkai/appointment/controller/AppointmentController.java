package com.wangkai.appointment.controller;

import com.wangkai.appointment.AppointmentService;
import com.wangkai.appointment.bean.AppointmentAndInfoVo;
import com.wangkai.appointment.bean.AppointmentBean;
import com.wangkai.utils.ResultDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/add")
    public String handleAppointment(AppointmentBean appointmentBean) {
        appointmentBean.setTime(System.currentTimeMillis()/1000);
        appointmentService.handleAppointment(appointmentBean);
        return ResultDataUtils.build(200, "操作成功",appointmentBean.getId());
    }

    @GetMapping("/my")
    public String findAppointments(String sk) {
        List<AppointmentBean> appointmentlist = appointmentService.findAppointments(sk);
        return ResultDataUtils.build(200, "操作成功",appointmentlist);
    }

    @GetMapping("/getPassenger")
    public String findAppointmentAndInfos(String sk) {
        List<Map<String,Object>> appointmentlist = appointmentService.findAppointmentAndInfos(sk);
        return ResultDataUtils.build(200, "操作成功",appointmentlist);
    }
}
