package com.wangkai.appointment;

import com.wangkai.appointment.bean.AppointmentBean;
import com.wangkai.appointment.dao.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentDao appointmentDao;

    public void handleAppointment(AppointmentBean appointmentBean) {
        appointmentDao.appointment(appointmentBean);
    }
}
