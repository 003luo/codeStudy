package com.codestudy.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Goodorbad)实体类
 *
 * @author makejava
 * @since 2024-04-08 22:47:03
 */
@Data
public class Likes implements Serializable {
    private static final long serialVersionUID = -15255423478670499L;
    /**
     * ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 点赞时间
     */
    private Date timestamp;
    /**
     * 点赞状态：0 无点赞，1 点赞
     */
    private Integer active;
    /**
     * 点赞的帖子ID
     */
    private Integer noteId;



}

