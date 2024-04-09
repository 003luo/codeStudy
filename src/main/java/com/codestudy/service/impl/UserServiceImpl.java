package com.codestudy.service.impl;

import com.codestudy.dao.UserDao;
import com.codestudy.entity.PageBean;
import com.codestudy.entity.User;
import com.codestudy.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-04-06 14:56:33
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(User user) {
        user.setCreateTime(new Date());
        user.setUpdataTime(new Date());
        userDao.insert(user);
    }


    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public void deleteById(List<Integer> ids) {
        userDao.deleteById(ids);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1. 设置分页参数
        PageHelper.startPage(page, pageSize);

        //2. 执行查询
        List<User> userList = userDao.list(page, pageSize);
        Page<User> p = (Page<User>) userList;

        //3. 封装PageBean对象
        return new PageBean(p.getTotal(), p.getResult());
    }

    /**
     * 修改数据
     * @param user
     */
    @Override
    public void updateUser(User user) {
        user.setUpdataTime(new Date());
        userDao.update(user);

    }
}
