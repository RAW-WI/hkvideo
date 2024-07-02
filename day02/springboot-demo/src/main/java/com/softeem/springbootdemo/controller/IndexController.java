package com.softeem.springbootdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.softeem.springbootdemo.dao.UserDAO;
import com.softeem.springbootdemo.dto.Result;
import com.softeem.springbootdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class IndexController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/")
    public String index(){
        return "这是第一个SpringBoot程序,这里是首页!";
    }

    @RequestMapping("/login")
    public Map<String,Object> login(String username, String password){
        //创建Map集合对象，用于传递丰富的数据到前端
        Map<String,Object> map = new HashMap<>();
        //创建查询条件包装器
        QueryWrapper<User> query = Wrappers.query(User.class);
        //username=?
        query.eq("username",username);
        // 1.根据用户名查询用户对象（返回一个用户对象）  select * from user where username=?
        User user = userDAO.selectOne(query);
        // 2.判断用户是否存在
        if(Objects.isNull(user)){
            map.put("code",-1);
            map.put("msg", "用户不存在");
        }else{
            // 3.如果存在则判断密码是否正确，不存在直接返回错误结果
            if(Objects.equals(user.getPassword(),password)){
                map.put("code",1);
                map.put("msg","登录成功");
            }else{
                map.put("code",0);
                map.put("msg","密码错误");
            }
        }
        return map;
    }

    @PostMapping("/reg")
    public Result reg(String username,String password){
        Result r = new Result();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int i = userDAO.insert(user);
        if(i > 0){
            r.setCode(1);
            r.setMsg("注册成功");
            r.setData(user);
        }else{
            r.setCode(-1);
            r.setMsg("注册失败");
        }
        return r;
    }
}
