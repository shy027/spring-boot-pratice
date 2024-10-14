package top.shy.springboot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.shy.springboot.mp.entity.Clazz;
import top.shy.springboot.mp.entity.Teacher;

public interface ClazzMapper extends BaseMapper<Clazz> {
    @Select("SELECT * FROM teacher WHERE id = #{teacherId}")
    Teacher selectTeacherByClazzId(Long teacherId);
}
