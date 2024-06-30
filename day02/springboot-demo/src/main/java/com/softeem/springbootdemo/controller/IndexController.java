package com.softeem.springbootdemo.controller;

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
        //todo 实现连接数据库登录逻辑？
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
