package com.softeem.hkvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.hkvideo.entity.Comment;
import com.softeem.hkvideo.service.CommentService;
import com.softeem.hkvideo.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author mrchai
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2024-07-02 10:29:37
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




