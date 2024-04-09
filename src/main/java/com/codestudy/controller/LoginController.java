package com.codestudy.controller;

import com.codestudy.common.JwtUtils;
import com.codestudy.entity.Result;
import com.codestudy.entity.User;
import com.codestudy.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping
@Api(tags = "登录注册")
public class LoginController {

    @Resource
    LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResponseEntity<Result> login(@RequestBody User user) {
        log.info("登录：{}", user);
        User u = loginService.login(user.getUserName(), user.getPassword());
        if (u != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUserName());
            claims.put("password", u.getPassword());

            String jwt = JwtUtils.generateJwt(claims);
            return ResponseEntity.ok(Result.ok("登录成功",jwt));
        }
        return ResponseEntity.badRequest().body(Result.fail("用户名或密码错误"));
    }

    @PostMapping("/regedit")
    @ApiOperation(value = "注册")
    public Result<User> regedit(@RequestBody User user){
        log.info("注册：{}", user);
        return loginService.regedit(user);
    }
}
