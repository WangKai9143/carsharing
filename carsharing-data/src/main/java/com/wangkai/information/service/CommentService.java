package com.wangkai.information.service;

import com.wangkai.information.bean.CommentBean;
import com.wangkai.utils.InforMaps;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 2019/11/27.
 */

@Service
public class CommentService {
    public Collection<CommentBean> getComment(CommentBean commentBean) {

        Collection<CommentBean> comments = new ArrayList<CommentBean>();
        try {
            String key;
            key = commentBean.getType() + "$" +commentBean.getIid();
            comments.add(InforMaps.getComment(key));
            comments.add(InforMaps.getComment(key));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return comments;
    }

    public boolean addComment(CommentBean commentBean) {
        try {
            String key = commentBean.getType() + "$" +commentBean.getIid();
            InforMaps.putComment(key,commentBean);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
