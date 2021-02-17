package com.wangkai.appointment.dao;

import com.wangkai.appointment.bean.AppointmentAndInfoVo;
import com.wangkai.appointment.bean.AppointmentBean;

import java.util.List;
import java.util.Map;

public interface AppointmentDao {
    void appointment(AppointmentBean appointmentBean);

    List<AppointmentBean> findAppointments(String uid);

    List<Map<String,Object>> findAppointmentAndInfos(String uid);
}
