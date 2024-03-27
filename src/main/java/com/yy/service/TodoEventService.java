package com.yy.service;

import com.yy.entity.TodoEvent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author young
 * @since 2024年03月16日
 */
public interface TodoEventService extends IService<TodoEvent> {
    List<TodoEvent> getAll();
    TodoEvent add(TodoEvent TodoEvent);

    TodoEvent get(Integer eventId);
}
