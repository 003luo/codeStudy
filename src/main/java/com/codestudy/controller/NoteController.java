package com.codestudy.controller;

import com.codestudy.entity.Note;
import com.codestudy.entity.Result;
import com.codestudy.entity.Result1;
import com.codestudy.service.NoteService;
import com.codestudy.vo.NoteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/note")
@Slf4j
@CrossOrigin  //允许跨域请求
@Api(tags = "笔记相关接口")
public class NoteController {

    @Autowired
    private NoteService noteService;

    //添加笔记
    @ApiOperation("添加笔记")
    @PostMapping("/add")
    public Result1 addNote(@RequestBody Note note) {
        log.info("note:{}", note);
        return noteService.addNote(note);
    }

    //通过id查询笔记
    @ApiOperation("通过id查询笔记")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    @GetMapping("/findbyid/{userId}")
    public Result1 lookNote(@PathVariable int userId) {
        System.out.println("userid为：===" + userId);
        List<Note> note = noteService.lookNote(userId);
        return Result1.success(note);
    }

    @GetMapping("/queryInfo/{noteId}")
    public Result<NoteVO> queryInfo(@PathVariable int noteId) {
        NoteVO res = noteService.queryInfo(noteId);
        return Result.ok("success", res);
    }
}
