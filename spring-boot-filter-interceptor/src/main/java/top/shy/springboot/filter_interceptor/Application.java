package top.shy.springboot.filter_interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.shy.springboot.filter_interceptor.config.OssConfig;

@SpringBootApplication
@EnableConfigurationProperties(OssConfig.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
