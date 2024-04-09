package com.codestudy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result1 {

    private Integer code;  //状态码 0失败 1成功
    private String msg;  //调接口信息成功与否
    private Object data;  //具体数据

    //成功调用不需要数据
    public static Result1 success(){
        return new Result1(1,"success",null);
    }

    //成功调用需要数据
    public static Result1 success(Object data){
        return new Result1(1,"success",data);
    }

    //失败调用
    public static Result1 error(String msg){
        return new Result1(0,msg,null);
    }

}
