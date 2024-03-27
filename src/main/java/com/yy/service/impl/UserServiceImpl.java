package com.yy.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.dao.UserMapper;
import com.yy.entity.User;
import com.yy.dao.UserMapper;
import com.yy.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User addUser(User user) {
        userMapper.insert(user);
        return user;
    }
    @Override
    public List<User> getUserWithTodoEvents(Integer userId) {
        return userMapper.findUserWithTodoEvents(userId); // 假设UserMapper中已经定义了findUserWithTodoEvents方法
    }
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }
    public User getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    public boolean verifyPassword(String inputPassword, String storedPassword) {
        // 这里可以使用加密算法（如BCrypt）来验证密码是否匹配
        return BCrypt.checkpw(inputPassword, storedPassword);
    }
}