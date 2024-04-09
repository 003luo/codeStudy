package com.codestudy.controller;

import com.codestudy.entity.Likes;
import com.codestudy.entity.Result;
import com.codestudy.service.LikesService;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/likes")
@Api(tags = "点赞收藏关注")
public class LikesController {
    @Resource
    private LikesService likesService;

    /**
     * 用户点赞
     *
     * @param
     * @param
     */
    @PostMapping("/dolike")
    public Result dolikes(@RequestBody Likes likes) {
        try {
            // 成功的逻辑
            Preconditions.checkNotNull(likes.getNoteId(), "笔记Id不能为空");
            Boolean res = likesService.dolikes(likes.getNoteId());
            if (res) return Result.ok("操作成功！");
            return Result.fail("操作失败！");
        } catch (Exception e) {
            // 业务层如果有抛异常，那么走这里
            log.error("likesController.dolikes.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }
}
