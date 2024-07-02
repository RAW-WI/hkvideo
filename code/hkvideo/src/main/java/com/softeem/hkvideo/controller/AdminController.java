package com.softeem.hkvideo.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.softeem.hkvideo.dto.R;
import com.softeem.hkvideo.entity.Admin;
import com.softeem.hkvideo.service.AdminService;
import com.softeem.hkvideo.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired  //由spring容器注入实现
    private AdminService adminService;

    /**
     * 管理员添加
     */
    @RequestMapping("/add")
    public R add(Admin admin) {
        //1. 判断当前管理员是否存在  select * from admin where username=?
        boolean exists = adminService.exists(Wrappers.query(Admin.class).eq("username",admin.getUsername()));
        if(exists){
            //管理员已存在
            return R.fail().setMsg("管理员已存在");
        }
        //1.生成一个随机盐（token）
        String salt = RandomUtil.randomString(32);
        //2.将输入密码和随机盐组合加密
        String pwd = admin.getPassword() + salt; //123456abcde
        pwd = SecureUtil.md5(pwd);               //EDUDNASTYPOD7D9DSDDFVBXCUIXYCI
        //3.将随机盐和加密后的密码同时保存
        admin.setSalt(salt);
        admin.setPassword(pwd);
        //4.保存到数据库
        return adminService.save(admin) ? R.ok().setMsg("添加成功").setData(admin) : R.fail().setMsg("添加失败");
    }

}
