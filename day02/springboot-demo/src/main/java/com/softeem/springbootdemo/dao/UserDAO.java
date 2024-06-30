package com.softeem.springbootdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO extends BaseMapper<User> {


}
