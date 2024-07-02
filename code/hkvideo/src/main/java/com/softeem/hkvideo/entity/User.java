package com.softeem.hkvideo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表

 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 标识列(PK)，自动生成，且唯一
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号 唯一
     */
    private String phone;

    /**
     * 注册时间 精确到时分秒  yyyy-MM-dd HH:mm:ss
     */
    private Date regtime;

    /**
     * 头像图片存储地址(oss技术)
     */
    private String img;

    /**
     * 生日格式yyyy-MM-dd
     */
    private Date birth;

    /**
     * 账号状态 0-正常    1-禁用
     */
    private Integer status;

    /**
     * 盐值，加密用
     */
    private String salt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}