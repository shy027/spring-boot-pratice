package top.shy.springboot.thymeleaf.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
    @GetMapping("/msg")
    public String getMsg(Model model) {
        model.addAttribute( "message", "Hello Thymeleaf");
        return "msg";
    }
}
