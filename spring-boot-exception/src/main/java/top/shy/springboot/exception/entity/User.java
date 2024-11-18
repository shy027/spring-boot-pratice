package top.shy.springboot.exception.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import top.shy.springboot.exception.annotation.Phone;

@Data
public class User {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    @Max(value = 100,message = "年龄不能超过100岁")
    @Min(value = 1,message = "年龄不能小于1岁")
    private int age;

    @Phone(message = "手机号格式不正确")
    private String phone;
}
