package top.shy.springboot.qa.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.shy.springboot.qa.entity.User;
import top.shy.springboot.qa.mapper.UserMapper;
import top.shy.springboot.qa.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    public UserMapper userMapper;

    @Override
    public Boolean getByName(String name,String password){
        return userMapper.loginByName(name,password);
    }

    @Override
    public Boolean insertUser(User user) {
        if (checkUserExists(user.getName())) {
            return false; // 用户名已存在
        }
        userMapper.insertUser(user.getName(),user.getPassword());
        return true;
    }

    @Override
    public Boolean checkUserExists(String username) {
        return userMapper.checkUserExists(username);
    }

    @Override
    public List<User> selectByName(String name) {
        return userMapper.selectByName(name);
    }
}
