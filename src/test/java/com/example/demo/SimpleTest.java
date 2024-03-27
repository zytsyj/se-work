package com.example.demo;

import com.example.demo.common.Result;
import com.yy.controller.TodoEventController;
import com.yy.controller.UserController;
import com.yy.dao.UserMapper;
import com.yy.dao.UserMapper;
import com.yy.entity.TodoEvent;
import com.yy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserController userController;
    @Autowired
    private TodoEventController TodoEventController;
    @Test
    public void select() {
        User user = new User();
        user.setUsername("Alice");
        user.setAge(30);
        Result<?> result = userController.save(user);

        TodoEvent a = new TodoEvent();
        a.setEventName("淦");
        Result<?> result1 = TodoEventController.save(a);
    }
}
