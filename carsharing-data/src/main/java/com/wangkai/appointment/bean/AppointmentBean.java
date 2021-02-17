package com.wangkai.appointment.bean;

import com.wangkai.common.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentBean extends BaseBean {
    private int id;
    private int uid;
    private int iid;
    private String name;
    private String phone;
    private int surplus;
    private Long time;
    private int status;
}
