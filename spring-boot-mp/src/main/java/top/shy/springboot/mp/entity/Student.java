package top.shy.springboot.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;
@Data
public class Student {
    private Long id;
    private String name;
    private  Long clazzId;
    @TableField(exist = false)
    private List<Course> courses;
}
