package top.shy.springboot.configure.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private String id;
    private String phone;
}
