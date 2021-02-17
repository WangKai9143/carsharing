package com.wangkai.appointment.bean;

import com.wangkai.information.bean.InfoBean;
import com.wangkai.user.bean.UserBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentAndInfoVo extends AppointmentBean{
    private InfoBean infoBean;
}
