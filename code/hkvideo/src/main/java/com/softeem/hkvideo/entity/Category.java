package com.softeem.hkvideo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 频道分类表
 * @TableName category
 */
@TableName(value ="category")
@Data
public class Category implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 频道分类名
     */
    private String name;

    /**
     * 频道分类介绍
     */
    private String summary;

    /**
     * 封面图地址
     */
    private String img;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}