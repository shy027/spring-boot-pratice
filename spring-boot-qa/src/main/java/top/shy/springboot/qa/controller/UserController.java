package top.shy.springboot.qa.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.shy.springboot.qa.common.ResponseResult;
import top.shy.springboot.qa.entity.User;
import top.shy.springboot.qa.service.UserService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult getByName(@RequestParam(required = false) String name,@RequestParam(required = false) String password){
        Boolean user = userService.getByName(name,password);
        return ResponseResult.builder()
                .code(200)
                .msg("请求成功")
                .data(user)
                .build();
    }
    @PostMapping("/registered")
    public ResponseResult registerUser(@RequestBody User user) {
        boolean success = userService.insertUser(user);
        if (!success) {
            return ResponseResult.builder()
                    .code(400)
                    .msg("该账号已存在")
                    .build();
        }
        return ResponseResult.builder()
                .code(200)
                .msg("注册成功")
                .build();
    }
    @GetMapping("/data")
    public ResponseResult selectByName(@RequestParam String username){
        List<User> user = userService.selectByName(username);
        return ResponseResult.builder()
                .code(200)
                .msg("请求成功")
                .data(user)
                .build();
    }
}
