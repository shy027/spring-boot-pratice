package top.shy.springboot.quickstart.service;

import top.shy.springboot.quickstart.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final List<User> users = List.of(
            new User(1L,"张三",22)
    );

    public List<String> getAdultUserName(){
        return users.stream().filter(user -> user.getAge() > 18)
                .map(User::getName)
                .collect(Collectors.toList());
    }
}
