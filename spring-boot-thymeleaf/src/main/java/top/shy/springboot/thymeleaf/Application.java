package top.shy.springboot.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @GetMapping
    public String hello(String name) {
        String greeting = "Hello !!" + name;
        return greeting;
    }

    @GetMapping("/add")
    public int count(int a,int b){
        int num = a + b;
        return num;
    }
}
