package com.wangkai.information.controller;

import com.wangkai.information.bean.CommentBean;
import com.wangkai.information.service.CommentService;
import com.wangkai.utils.ResultDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by Administrator on 2020/2/23.
 */

@RequestMapping("/comment")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/get")
    public String queryComment(CommentBean commentBean) {
//        CommentBean comment = null;
        Collection<CommentBean> commentBeans;
        try {
//          comment = commentService.getComment(commentBean);
            commentBeans = commentService.getComment(commentBean);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDataUtils.build(20001, "操作失败");
        }
        return ResultDataUtils.build(200, "操作成功", commentBeans);
    }

    @GetMapping("/get_count")
    public String getCount(CommentBean commentBean) {
        Integer count = 2;
        try {
            count = 2;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDataUtils.build(20001, "操作失败", count);
        }
        return ResultDataUtils.build(200, "操作成功", count);
    }

    @PostMapping("/add")
    public String replyComment(CommentBean commentBean) {
        boolean result;
        try {
            result = commentService.addComment(commentBean);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDataUtils.build(20001, "操作失败");
        }
        return result ? ResultDataUtils.build(200, "操作成功") : ResultDataUtils.build(20001, "操作失败");
    }
}
