package com.yy.controller;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.service.UserService;
import com.yy.dao.UserMapper;
import com.example.demo.common.Result;
import com.yy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author young
 * @since 2024年03月16日
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/{userId}")
    public List<User> getUserWithTodoEvents(@PathVariable Integer userId) {

        return userService.getUserWithTodoEvents(userId);
    }
    @GetMapping("all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public Result<?> save(@RequestBody User user){
        userService.saveOrUpdate(user);
        return Result.success();
    }
   @DeleteMapping("/delete/{userId}")
   public Result<?> update(@PathVariable long userId){
       userMapper.deleteUserWithOrders(userId);


       return Result.success();
   }
    @PostMapping("/login")
    public Result<?> login(@RequestBody User userd) {


        User user = userService.getUserByUsername(userd.getUsername());
        if (user != null && Objects.equals(user.getPassword(), userd.getPassword())) {
            // 验证成功，返回成功信息或者生成 Token 等操作
            return Result.success(user);
        }
        return Result.error("1","失败");
    }
    @GetMapping
    public Result<?>
    findPage(@RequestParam(defaultValue="1") Integer pageNum,
             @RequestParam(defaultValue ="10")Integer pageSize,
             @RequestParam(defaultValue= "") String search){

Page <User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<User>lambdaQuery().like(User::getNickName,search));

    return Result.success(userPage);
}
}
