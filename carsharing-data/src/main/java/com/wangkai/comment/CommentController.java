package com.wangkai.comment;

import com.wangkai.comment.bean.CommentBean;
import com.wangkai.utils.ResultDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Administrator on 2020/2/23.
 */

@RequestMapping("/comment")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/get")
    public String queryComment(@RequestParam Map<String,Object> paramsMap) {
        Collection<CommentBean> commentBeans;
        try {
            commentBeans = commentService.getComment(paramsMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDataUtils.build(20001, "评论加载失败");
        }
        return ResultDataUtils.build(200, "评论加载成功", commentBeans);
    }

    @GetMapping("/getCount")
    public String getCount(CommentBean commentBean) {
        Integer count = 0;
        try {
            count = commentService.getCommentCount(commentBean);
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
