package com.wangkai.appointment.controller;

import com.wangkai.appointment.AppointmentService;
import com.wangkai.appointment.bean.AppointmentBean;
import com.wangkai.information.bean.InfoBean;
import com.wangkai.information.service.InforService;
import com.wangkai.utils.ResultDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/add")
    public String handleAppointment(AppointmentBean appointmentBean) {
        appointmentBean.setTime(System.currentTimeMillis()/1000);
        appointmentService.handleAppointment(appointmentBean);
        return ResultDataUtils.build(200, "操作成功");
    }
}
