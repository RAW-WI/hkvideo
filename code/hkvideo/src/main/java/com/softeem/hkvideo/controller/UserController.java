package com.softeem.hkvideo.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.softeem.hkvideo.dto.R;
import com.softeem.hkvideo.entity.Admin;
import com.softeem.hkvideo.entity.User;
import com.softeem.hkvideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @RequestMapping("/reg")
    public R registry(User user){
        boolean exists = userService.exists(Wrappers.query(User.class).eq("username", user.getUsername()));
        if(exists){
            return R.fail().setMsg("用户已存在");
        }
        String salt = RandomUtil.randomString(32);
        String pwd = user.getPassword() + salt;
        pwd = SecureUtil.md5(pwd);
        user.setSalt(salt);
        user.setPassword(pwd);
        return userService.save(user) ? R.ok().setMsg("注册成功").setData(user) : R.fail().setMsg("注册失败");
    }

    @RequestMapping("/login")
    public R login(String username,String password) {
        //1.根据账号，查询数据库
        User user = userService.getOne(Wrappers.query(User.class).eq("username",username));
        if(Objects.isNull(user)){
            //账号不存在
            return R.fail().setMsg("账号不存在");
        }
        if(user.getStatus() != 0){
            //账号被禁用
            return R.fail().setMsg("账号被禁用");
        }
        if(user.getPassword().equals(SecureUtil.md5(password + user.getSalt()))){
            //登录成功
            return R.ok().setMsg("登录成功").setData(user);
        }
        //密码错误
        return R.fail().setMsg("密码错误");
    }

    @RequestMapping("/update")
    public R modify(User user){
        //查看是否有提供id
        if(Objects.isNull(user.getId())){
            return R.fail().setMsg("用户id未获取");
        }
        //执行根据id修改
        boolean b = userService.updateById(user);
        return b ? R.ok().setMsg("修改完成").setData(user) : R.fail().setMsg("修改失败");
    }

    /**
     * 分页查询
     * @param page   当前页
     * @param limit  每页数据条数
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit){
        //执行分页查询  select * from user      limit ?,?
        List<User> list = userService.list(new Page<User>(page,limit));
        //查询总数据条数
        long count = userService.count();
        return R.ok().setData(list).setCount(count);
    }

    /**
     * 黑名单列表分页查询
     * @return
     */
    @RequestMapping("/black")
    public R blackList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit){
        //执行分页查询  select * from user      limit ?,?
        List<User> list = userService.list(new Page<User>(page,limit),Wrappers.query(User.class).eq("status",1));
        //查询总数据条数
        long count = userService.count(Wrappers.query(User.class).eq("status",1));
        return R.ok().setData(list).setCount(count);
    }

}
