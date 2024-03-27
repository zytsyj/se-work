package com.yy.service;

import com.yy.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author young
 * @since 2024年03月16日
 */
@Service
public interface UserService extends IService<User> {
    User addUser(User user);
    List<User> getUserWithTodoEvents(Integer userId);
    List<User> getAllUsers();

    boolean verifyPassword(String password, String password1);

    User getUserByUsername(String username);
}


