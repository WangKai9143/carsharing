package com.wangkai.information.bean;

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
public class InfoBean {
   /* CREATE TABLE IF NOT EXISTS `xcx_info` (
            `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
            `time` int(11) DEFAULT NULL,
  `departure` varchar(1000) DEFAULT NULL,
  `destination` varchar(1000) DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `remark` text,
            `surplus` tinyint(4) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `vehicle` varchar(100) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
            `see` int(11) NOT NULL DEFAULT '0',
            `price` decimal(10,2) DEFAULT NULL,
  `addtime` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;*/
    private int id;
    private String date;
    private String time;
    private String departure;
    private String destination;
    private String isAgree;
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
