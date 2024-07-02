package com.softeem.hkvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.hkvideo.entity.User;
import com.softeem.hkvideo.service.UserService;
import com.softeem.hkvideo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author mrchai
* @description 针对表【user(用户表
)】的数据库操作Service实现
* @createDate 2024-07-02 10:29:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




