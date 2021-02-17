package com.wangkai.information.bean;

import com.wangkai.common.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by Administrator on 2019/11/25.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InfoBean extends BaseBean {
    private int id;
    private String date;
    private String time;
    private String departure;
    private String destination;
    private int gender;
    private String name;
    private String phone;
    private String remark;
    private int surplus;
    private int type;
    private String vehicle;
    private int uid;
    private int status;
    private int see;
    private double price;
    private int addtime;
}
