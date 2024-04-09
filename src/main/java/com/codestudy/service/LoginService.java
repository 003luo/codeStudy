package com.codestudy.service;

import com.codestudy.entity.Result;
import com.codestudy.entity.User;

public interface LoginService {


    User login(String userName, String password);

    Result<User> regedit(User user);
}
