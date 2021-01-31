package com.wangkai.information.bean;

import com.wangkai.common.BaseInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Administrator on 2020/2/23.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentBean extends BaseInfo {
  /*  CREATE TABLE IF NOT EXISTS `xcx_comment` (
        `id` int(11) NOT NULL,
        `uid` int(11) DEFAULT NULL,
        `iid` int(11) DEFAULT NULL,
        `time` int(11) DEFAULT NULL,
        `type` varchar(50) NOT NULL DEFAULT 'info',
        `content` text NOT NULL,
        `img` text NOT NULL,
        `zan` int(11) NOT NULL DEFAULT '0',
        `reply` text
  ) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;*/
    private int id;
    private int uid;
    private int iid;
    private Date time;
    private String type;
    private String content;
    private String img;
    private int zan;
    private String reply;
}
