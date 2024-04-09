package com.codestudy.service.impl;

import com.codestudy.dao.LoginDao;
import com.codestudy.entity.Result;
import com.codestudy.entity.User;
import com.codestudy.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service("LoginService")
public class LoginServiceImpl implements LoginService {
    @Resource
    LoginDao loginDao;


    @Override
    public User login(String userName, String password) {
        return loginDao.getByUsernameAndPassword(userName, password);
    }

    /**
     * 注册账号
     * @param user
     * @return
     */
    @Override
    public Result<User> regedit(User user) {
        int usersCount = loginDao.select(user);
        if (usersCount != 0) {
            return Result.fail("账号已存在");
        }
        user.setCreateTime(new Date());
        user.setUpdataTime(new Date());
        loginDao.regedit(user);
        return Result.ok("注册成功！");
    }

}
