package com.wangkai.comment.bean;

import com.wangkai.common.BaseBean;
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
public class CommentBean extends BaseBean {
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
