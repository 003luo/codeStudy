package com.codestudy.service;

import com.codestudy.entity.PageBean;
import com.codestudy.entity.User;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2024-04-06 14:56:32
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    void insert(User user);


    /**
     * 通过主键删除数据
     */
    void deleteById(List<Integer> ids);

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize);

    /**
     * 修改信息
     *
     * @param user
     */
    void updateUser(User user);
}
