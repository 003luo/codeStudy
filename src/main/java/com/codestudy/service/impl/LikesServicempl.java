package com.codestudy.service.impl;

import com.codestudy.common.LoginUtil;
import com.codestudy.dao.LikesDao;
import com.codestudy.entity.Likes;
import com.codestudy.service.LikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class LikesServicempl implements LikesService {

    // 构造器自动注入
    private final LikesDao likesDao;

    @Override
    public Boolean dolikes(int noteId) {
        int count = 0;
//        /**
//         * 方案一： 直接操作数据库
//         *  先拿用户id到数据库查
//         *  命中：表示用户在做取消点赞操作，删除该数据
//         *  未命中：表示用户在做点赞操作，新增数据
//         */
//        Goodorbad goodorbad = new Goodorbad();
//        goodorbad.setUserId(LoginUtil.getCurrentId());  //拿用户ID
//        goodorbad.setNoteId(noteId);
////        "select * from goodorbad where user_id = #{userId} and note_id = #{noteId}"
//        // 条件查询
//        List<Goodorbad> list = goodorbadDao.queryByCondition(goodorbad); // 永远最多只有一条
//        // 使用集合工具类判断集合是否为空
//        if (CollectionUtils.isEmpty(list)) {
//            // 没有查到用户信息，为新增操作  --------- 新增点赞
//            Goodorbad likes = new Goodorbad();
//            likes.setNoteId(noteId);
//            likes.setTimestamp(new Date());
//            likes.setUserId(LoginUtil.getCurrentId());
//            count = goodorbadDao.insert(likes);
//        } else {
//            // 查到了，为删除操作 ------ 取消点赞
//            // "delete from goodorbar where id = #{id}"
//            count = goodorbadDao.deleteById(list.get(0).getId());
//        }
//        return count > 0; // 0 > 0 false  1 > 0 true


        /**
         * 方案二 ： 按照状态去做新增或修改
         *  命中： 说明用户曾经点赞过，此时判断状态进行置反
         *  未命中： 插入数据并设置状态为点赞
         */

        Likes goodorbad = new Likes();
        goodorbad.setUserId(LoginUtil.getCurrentId());
        goodorbad.setNoteId(noteId);
        // 条件查询
        List<Likes> list = likesDao.queryByCondition(goodorbad); // 永远最多只有一条
        if (CollectionUtils.isEmpty(list)) {
            // 未命中
            Likes likes = new Likes();
            likes.setNoteId(noteId);
            likes.setTimestamp(new Date());
            likes.setUserId(LoginUtil.getCurrentId());
            likes.setActive(1);
            count = likesDao.insert(likes);
        } else {
            // 命中了
            // 取出当前数据的状态
            Integer active = list.get(0).getActive();
            Likes likes = new Likes();
            likes.setId(list.get(0).getId());
            if (active == 1) {
                // 此时为点赞，则改为取消点赞
                likes.setActive(0);
            } else {
                likes.setActive(1);
            }
            count = likesDao.update(likes);
        }
        return count > 0;


        /**
         * 方案三： 使用redis记录用户点赞数据 ， 开启定时任务 每天 晚上12点 准时刷入到mysql做持久化数据
         */
    }
}

