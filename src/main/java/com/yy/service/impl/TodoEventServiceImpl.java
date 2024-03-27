package com.yy.service.impl;

import com.yy.dao.UserMapper;
import com.yy.entity.TodoEvent;
import com.yy.dao.TodoEventMapper;
import com.yy.entity.User;
import com.yy.service.TodoEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author young
 * @since 2024年03月16日
 */
@Service
public class TodoEventServiceImpl extends ServiceImpl<TodoEventMapper, TodoEvent> implements TodoEventService {
    @Autowired
    private TodoEventMapper TodoEventMapper;
    @Override
    public TodoEvent add(TodoEvent TodoEvent) {
        TodoEventMapper.insert(TodoEvent);
        return TodoEvent;
    }
    @Override
    public TodoEvent get(Integer eventId) {
        return TodoEventMapper.findWithUser(eventId); // 假设UserMapper中已经定义了findUserWithTodoEvents方法
    }
    public List<TodoEvent> getAll() {
        return TodoEventMapper.selectList(null);
    }
}
