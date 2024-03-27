package com.yy.dao;

import com.yy.entity.TodoEvent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yy.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author young
 * @since 2024年03月16日
 */
@Mapper

public interface TodoEventMapper extends BaseMapper<TodoEvent> {
    TodoEvent findWithUser(Integer eventId);
}
