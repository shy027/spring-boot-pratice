package top.shy.springboot.qa.mapper;

import top.shy.springboot.qa.entity.User;

import java.util.List;

public interface UserMapper {
    Boolean loginByName(String name,String password);
    Boolean insertUser(String name,String password);

    Boolean checkUserExists(String name);
    List<User> selectByName(String name);
}
