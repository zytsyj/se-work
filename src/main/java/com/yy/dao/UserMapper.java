package com.yy.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface UserMapper extends BaseMapper<User> {
        List<User> findUserWithTodoEvents(Integer userId);
        @Delete("""
                DELETE user, todo_event
                FROM user
                LEFT JOIN todo_event ON user.id = todo_event.user_id
                WHERE user.id = #{userId}
                """)
        void deleteUserWithOrders(@Param("userId") Long userId);

}

