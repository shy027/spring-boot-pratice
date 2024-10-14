package top.shy.springboot.mp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.shy.springboot.mp.entity.Clazz;
import top.shy.springboot.mp.service.ClazzService;

@RestController
@RequestMapping("/clazz")
@AllArgsConstructor
public class ClazzController {
    private final ClazzService clazzService;
    @GetMapping("/{id}")
    public Clazz getClazzWithTeacher(@PathVariable Long id){
        return clazzService.getClazzWithTeacher(id);
    }

}
