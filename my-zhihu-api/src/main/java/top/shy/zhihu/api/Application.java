package top.shy.zhihu.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan(basePackages = {"top.shy.zhihu.api.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

}
