package top.shy.springboot.quickstart.BookController;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.shy.springboot.quickstart.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/adults")
    public List<String> getAdultsUserName(){
        return userService.getAdultUserName();
    }

}
