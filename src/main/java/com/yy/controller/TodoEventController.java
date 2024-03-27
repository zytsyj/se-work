package com.yy.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.yy.dao.TodoEventMapper;
import com.yy.entity.TodoEvent;
import com.yy.entity.User;
import com.yy.service.TodoEventService;
import com.yy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.service.UserService;
import com.yy.dao.UserMapper;
import com.example.demo.common.Result;
import com.yy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author young
 * @since 2024年03月16日
 */
@RestController
@RequestMapping("/todoEvent")
public class TodoEventController {
    @Autowired
    private TodoEventService TodoEventService;
    @Autowired
    private TodoEventMapper TodoEventMapper;
    @PostMapping
    public Result<?> save(@RequestBody TodoEvent TodoEvent){
        TodoEventService.add(TodoEvent);
        return Result.success();
    }
    @GetMapping("/{eventId}")
    public TodoEvent get(@PathVariable Integer eventId) {

        return TodoEventService.get(eventId);
    }
    @GetMapping("/all")
    public List<TodoEvent> getAll() {
        return TodoEventService.getAll();
    }
    @DeleteMapping("/delete/{eventId}")
    public Result<?> update(@PathVariable long eventId){
        TodoEventMapper.deleteById(eventId);


        return Result.success();
    }
    @GetMapping
    public Result<?>
    findPage(@RequestParam(defaultValue="1") Integer pageNum,
             @RequestParam(defaultValue ="10")Integer pageSize,
             @RequestParam(defaultValue= "") String search){

        Page<TodoEvent> userPage = TodoEventMapper.selectPage(new Page<>(pageNum, pageSize), Wrappers.<TodoEvent>lambdaQuery().like(TodoEvent::getEventName,search));

        return Result.success(userPage);
    }

    }


