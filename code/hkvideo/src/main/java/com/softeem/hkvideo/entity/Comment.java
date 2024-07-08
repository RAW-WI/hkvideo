package com.softeem.hkvideo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date commtime;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 视频id
     */
    private Integer vid;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Video video;
    
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;



}