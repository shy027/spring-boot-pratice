package top.shy.springboot.database.mapper;

import top.shy.springboot.database.entity.Course;

import java.util.List;

public interface CourseMapper {
    List<Course> selectAll();
}
