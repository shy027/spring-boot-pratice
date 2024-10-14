package top.shy.springboot.mp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.shy.springboot.mp.entity.Clazz;
import top.shy.springboot.mp.entity.Teacher;
import top.shy.springboot.mp.mapper.ClazzMapper;

@Service
@AllArgsConstructor
public class ClazzService {
    private final ClazzMapper clazzMapper;
    public Clazz getClazzWithTeacher(Long clazzId){
        Clazz clazz = clazzMapper.selectById(clazzId);
        if (clazz != null){
            Teacher teacher = clazzMapper.selectTeacherByClazzId(clazz.getTeacherId());
            clazz.setTeacher(teacher);
        }
        return clazz;
    }
}
