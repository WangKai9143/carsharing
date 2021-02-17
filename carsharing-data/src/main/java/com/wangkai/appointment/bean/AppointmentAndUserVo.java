package com.wangkai.appointment.bean;

import com.wangkai.user.bean.UserBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentAndUserVo extends AppointmentBean{
    private UserBean userBean;
}
