package com.wangkai.appointment;

import com.wangkai.appointment.bean.AppointmentAndInfoVo;
import com.wangkai.appointment.bean.AppointmentAndUserVo;
import com.wangkai.appointment.bean.AppointmentBean;
import com.wangkai.appointment.dao.AppointmentDao;
import com.wangkai.common.GlobalCache;
import com.wangkai.user.bean.UserBean;
import com.wangkai.user.dao.UserDao;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentDao appointmentDao;


    public void handleAppointment(AppointmentBean appointmentBean) {
        Map<String,Object> userMap = (Map<String, Object>) GlobalCache.get(appointmentBean.getSk());
        appointmentBean.setUid(Integer.valueOf(userMap.get("id").toString()));
        appointmentDao.appointment(appointmentBean);
    }

    public List<AppointmentBean> findAppointments(String sk) {
        Map<String,Object> userMap = (Map<String, Object>) GlobalCache.get(sk);
        return appointmentDao.findAppointments(userMap.get("id").toString());
    }

    public List<Map<String,Object>> findAppointmentAndInfos(String sk) {
        Map<String,Object> userMap = (Map<String, Object>) GlobalCache.get(sk);
        return appointmentDao.findAppointmentAndInfos(userMap.get("id").toString());
    }
}
