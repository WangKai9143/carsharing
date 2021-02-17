package com.wangkai.comment;

import com.github.pagehelper.PageHelper;
import com.wangkai.comment.bean.CommentBean;
import com.wangkai.comment.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2019/11/27.
 */

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    private static int pageSize = 20;


    public Collection<CommentBean> getComment(Map<String,Object> paramsMap) {
        int currPage = 1;
        if (paramsMap != null && !paramsMap.isEmpty()) {
            currPage = Integer.parseInt(paramsMap.get("page").toString());
        }
        PageHelper.startPage(currPage, pageSize);
        List<CommentBean> comments = commentDao.getComment(paramsMap);
        return comments;
    }

    public void replyComment(CommentBean commentBean) {
         commentDao.replyComment(commentBean);
    }

    public Integer getCommentCount(CommentBean commentBean) {
        return commentDao.getCommentCount(commentBean);
    }
}
