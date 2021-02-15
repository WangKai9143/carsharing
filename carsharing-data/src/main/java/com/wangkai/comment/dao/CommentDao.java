package com.wangkai.comment.dao;

import com.wangkai.comment.bean.CommentBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/2/23.
 */
public interface CommentDao {
    Integer getCommentCount(CommentBean commentBean);

    List<CommentBean> getComment(Map<String,Object> paramsMap);
}
