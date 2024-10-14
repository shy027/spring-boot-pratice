package top.shy.springboot.database.mapper;

import org.springframework.data.repository.query.Param;
import top.shy.springboot.database.entity.Student;

import java.util.List;

public interface StudentMapper {
    int insert(Student student);
    Student findStudentById(int studentId);
    int updateById(Student student);
    int deleteById(int studentId);
    int batchInsert(@Param("students") List<Student> students);
    int batchDelete(@Param("idList") List<Integer> ids);
    int batchUpdate(@Param("students") List<Student> students);
    List<Student> selectByDynamicSql(Student student);

    Student getStudentManyToOne(int studentId);
    Student getStudent(int studentId);
}
