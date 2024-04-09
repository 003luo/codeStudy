package com.codestudy.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiModel(value = "success", description = "文章表")
public class Note implements Serializable {
    private int id;
    private int userId;
    private String title;
    private String msg;
    private String img;
    private String annex;
    private int type;
    private Date createTime;
    private Date updateTime;
}
