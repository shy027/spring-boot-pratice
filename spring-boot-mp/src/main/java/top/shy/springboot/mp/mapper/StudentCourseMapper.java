package top.shy.springboot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.shy.springboot.mp.entity.Course;
import top.shy.springboot.mp.entity.StudentCourse;

import java.util.List;

public interface StudentCourseMapper extends BaseMapper<StudentCourse> {
    @Select("SELECT c.* FROM course c INNER JOIN student_course sc ON c.id = sc.course_id WHERE sc.course_id = #{studentId}")
    List<Course> selectCourseByStudentId(Long studentId);
}
