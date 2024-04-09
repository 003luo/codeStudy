package com.codestudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //异常
//@MapperScan("com.codestudy.dao") //mapper有时载入不进去bean容器中，需要使用这个注解
public class CodeStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeStudyApplication.class, args);
    }

}
