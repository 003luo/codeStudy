package com.codestudy.controller;

import com.codestudy.entity.PageBean;
import com.codestudy.entity.Result;
import com.codestudy.entity.User;
import com.codestudy.service.UserService;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(User)表控制层
 *
 * @author makejava
 * @since 2024-04-06 14:56:30
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("user")
@Api(tags = "用户管理")
@EnableSwaggerBootstrapUI  //开启swagger美化
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping
    @ApiOperation(value = "分页查询,没完成",notes = "需要页数和一页多少条数据")
    public ResponseEntity<Result<User>> queryByPage(@RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询,参数：{},{}", page, pageSize);
        PageBean pageBean = userService.page(page, pageSize);

        System.out.println(pageBean);
        return ResponseEntity.ok(Result.ok(String.valueOf(pageBean)));
    }

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询用户",notes = "请给我id")
    public ResponseEntity<Result<User>> queryById(@PathVariable Integer id) {
        log.info("通过id查询用户：{}",id);
        return ResponseEntity.ok(Result.ok("",userService.queryById(id)));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping
    @ApiOperation(value = "新增用户")
    public ResponseEntity<Result<User>> add(@RequestBody User user) {
        log.info("新增用户：{}",user);
        userService.insert(user);
        return ResponseEntity.ok(Result.ok(""));
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    @ApiOperation(value = "编辑用户")
    public Result<User> edit(@RequestBody User user) {
        log.info("编辑用户：{}",user);
      userService.updateUser(user);
        return Result.ok("");
    }

    /**
     * 批量删除用户
     */
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "批量删除用户")
    public ResponseEntity<Result<Void>> deleteById(@PathVariable List<Integer> ids) {
        log.info("删除用户id：{}",ids);
        userService.deleteById(ids);
        return ResponseEntity.ok(Result.ok("删除成功！"));
    }

}

