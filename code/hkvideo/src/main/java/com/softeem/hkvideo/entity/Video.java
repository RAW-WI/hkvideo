package com.softeem.hkvideo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 视频表
 * @TableName video
 */
@TableName(value ="video")
@Data
public class Video implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 详情
     */
    private String summary;

    /**
     * 封面图片地址
     */
    private String coverimg;

    /**
     * 视频文件地址
     */
    private String url;

    /**
     * 发布时间
     */
    private Date releasetime;

    /**
     * 观看数
     */
    private Integer viewcount;

    /**
     * 点赞数
     */
    private Integer lovecount;

    /**
     * 收藏数
     */
    private Integer favcount;

    /**
     * 转发数
     */
    private Integer forward;

    /**
     * 视频状态：0-审核中  1-禁用   2-正常  
     */
    private Integer status;

    /**
     * 创作者
     */
    private Integer uid;

    /**
     * 审核员
     */
    private Integer aid;

    /**
     * 标签
     */
    private String tags;

    /**
     * 频道分类
     */
    private Integer cid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}