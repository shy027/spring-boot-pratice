package top.shy.springboot.database.mapper;

import top.shy.springboot.database.entity.Teacher;

public interface TeacherMapper {
    Teacher findTeacherById(int teacherId);
}
