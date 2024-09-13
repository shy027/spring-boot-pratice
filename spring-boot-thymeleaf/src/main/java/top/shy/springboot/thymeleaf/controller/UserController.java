package top.shy.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.shy.springboot.thymeleaf.model.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();
    @GetMapping("/userList")
        public String userList(Model model){
        model.addAttribute("users",users);
        return "userList";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name, @RequestParam String email){
    Long id = (long)(users.size() + 1);
    User user = new User(id,name,email);
    users.add(user);
    return "redirect:/userList";
    }

//    @PostMapping("/deleteUser")
//    public String deleteUser(@RequestParam Long id){
//        users.removeIf(user -> user.getId().equals(id));
//        return "redirect:/userList";
//    }


}
