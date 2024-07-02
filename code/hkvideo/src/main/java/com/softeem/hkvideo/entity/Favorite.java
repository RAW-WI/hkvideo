package com.softeem.hkvideo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 收藏表
 * @TableName favorite
 */
@TableName(value ="favorite")
@Data
public class Favorite implements Serializable {
    /**
     * 收藏id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 视频id
     */
    private Integer vid;

    /**
     * 收藏时间
     */
    private Date favtime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}