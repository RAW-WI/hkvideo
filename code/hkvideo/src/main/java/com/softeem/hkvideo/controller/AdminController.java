package com.softeem.hkvideo.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.softeem.hkvideo.dto.R;
import com.softeem.hkvideo.entity.Admin;
import com.softeem.hkvideo.service.AdminService;
import com.softeem.hkvideo.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

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

    /**
     * 管理员登录,提供账号密码
     */
    @RequestMapping("/login")
    public R login(Admin admin) {
        //1.根据账号，查询数据库
        Admin admin1 = adminService.getOne(Wrappers.query(Admin.class).eq("username",admin.getUsername()));
        if(Objects.isNull(admin1)){
            //账号不存在
            return R.fail().setMsg("账号不存在");
        }
        if(admin1.getStatus() != 0){
            //账号被禁用
            return R.fail().setMsg("账号被禁用");
        }
        if(admin1.getPassword().equals(SecureUtil.md5(admin.getPassword() + admin1.getSalt()))){
            //登录成功
            return R.ok().setMsg("登录成功").setData(admin1);
        }
        //密码错误
        return R.fail().setMsg("密码错误");
    }

    @RequestMapping("/list")
    public R list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        List<Admin> list = adminService.list(new Page<>(page, limit));
        return R.ok().setData(list).setCount(adminService.count());
    }

}
