package top.shy.springboot.qa.service;

import top.shy.springboot.qa.entity.User;

import java.util.List;

public interface UserService {
    Boolean getByName(String name,String password);
    Boolean insertUser(User user);
    Boolean checkUserExists(String name);
    List<User> selectByName(String name);
}
