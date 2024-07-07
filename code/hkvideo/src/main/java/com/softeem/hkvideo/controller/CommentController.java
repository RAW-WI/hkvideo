package com.softeem.hkvideo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.softeem.hkvideo.dto.R;
import com.softeem.hkvideo.entity.Comment;
import com.softeem.hkvideo.entity.User;
import com.softeem.hkvideo.entity.Video;
import com.softeem.hkvideo.service.CommentService;
import com.softeem.hkvideo.service.UserService;
import com.softeem.hkvideo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;

    /**
     * 分页查询评论列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        //分页查询评论列表
        List<Comment> list = commentService.list(new Page<>(page, limit));
        //收集所有评论用户id
        List<Integer> userIds = list.stream().map(Comment::getUid).toList();
        //收集所有评论视频id
        List<Integer> videoIds = list.stream().map(Comment::getVid).toList();

        //批量查询用户和视频
        List<User> users = userService.listByIds(userIds);
        List<Video> videos = videoService.listByIds(videoIds);

        //映射用户ID和用户对象为Map集合（StreamAPI实现）
        list.forEach(comment -> {
            comment.setUser(users.stream().filter(user -> user.getId().equals(comment.getUid())).findFirst().orElse(null));
            comment.setVideo(videos.stream().filter(video -> video.getId().equals(comment.getVid())).findFirst().orElse(null));
        });

        long count = commentService.count();
        //返回结果
        return R.ok().setData(list).setCount(count);
    }
}
