package top.shy.springboot.quickstart.BookController;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.shy.springboot.quickstart.enums.RequestType;
import top.shy.springboot.quickstart.service.CustomerService;

@RestController
@RequestMapping("/requests")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @GetMapping("/{type}")
    public String handleRequest(@PathVariable RequestType type){
        return customerService.handleRequest(type);
    }
}
