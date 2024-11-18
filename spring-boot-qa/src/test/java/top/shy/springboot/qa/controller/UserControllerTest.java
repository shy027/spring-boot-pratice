package top.shy.springboot.qa.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.shy.springboot.qa.entity.User;
import top.shy.springboot.qa.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserControllerTest {
    @Resource
    private UserService userService;
    @Test
    void getByUsername() {
        boolean users = userService.getByName("root","123456");
        log.info(String.valueOf(users));
    }
}