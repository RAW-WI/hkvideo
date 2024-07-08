package com.softeem.hkvideo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.softeem.hkvideo.dto.R;
import com.softeem.hkvideo.entity.*;
import com.softeem.hkvideo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/comment")
@RestController
public class CommentController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/list")
    public R list(@RequestParam(defaultValue = "1") Integer page,
                  @RequestParam(defaultValue = "5") Integer limit,
                  @RequestParam(required = false, defaultValue = "") String keyword) {
        //创建查询条件
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        if (!keyword.isEmpty()) {
            queryWrapper.lambda().like(Comment::getContent, keyword);
        }
        // 获取分页数据
        List<Comment> list = commentService.list(new Page<Comment>(page, limit),queryWrapper);
        // 遍历评论，设置用户和视频信息
        list.forEach(v -> {
            Integer uid = v.getUid();
            if (uid != null) {
                User user = userService.getById(uid);
                v.setUser(user);
            }

            Integer cid = v.getVid();
            if (cid != null) {
                Video category = videoService.getById(cid);
                v.setVideo(category);
            }
        });

        // 获取评论总数
        long count = commentService.count();

        // 构建响应对象，设置数据和总数
        return R.ok().setData(list).setCount(count);
    }
    @RequestMapping("/delete")
    public R delete(Integer id) {
        return commentService.removeById(id) ? R.ok().setCode(0) : R.fail().setCode(1);
    }
}
