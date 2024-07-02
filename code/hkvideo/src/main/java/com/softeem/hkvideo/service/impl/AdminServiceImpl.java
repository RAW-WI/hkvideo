package com.softeem.hkvideo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.hkvideo.entity.Admin;
import com.softeem.hkvideo.service.AdminService;
import com.softeem.hkvideo.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author mrchai
* @description 针对表【admin(管理员/审核员表)】的数据库操作Service实现
* @createDate 2024-07-02 10:29:37
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




