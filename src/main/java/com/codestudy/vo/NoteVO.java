package com.codestudy.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "success", description = "用户表")
public class NoteVO implements Serializable {
    private int id;
    private int userId;
    private String title;
    private String msg;
    private String img;
    private String annex;
    private int type;
    private Date createTime;
    private Date updateTime;
    /**
     * 点赞数
     */
    private Integer likeCount = 0;

    /**
     * 评论数
     */
    private Integer collectCount = 0;
}
