package top.shy.springboot.database.mapper;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import top.shy.springboot.database.entity.Course;
import top.shy.springboot.database.entity.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Service
class StudentMapperTest {
    @Resource
    private StudentMapper studentMapper;

    @Test
    void insert() {
        Student student = Student.builder().clazzId(1).studentName("测试学生").hometown("江苏南京").birthday(LocalDate.now()).build();
        int n = studentMapper.insert(student);
        assertEquals(1,n);
    }

    @Test
    void findStudentById() {
        Student student = studentMapper.findStudentById(8001);
        assertEquals("钱智康", student.getStudentName());
    }

    @Test
    void updateById() {
        Student student = Student.builder().studentId(8001).studentName("123").build();
        int n = studentMapper.updateById(student);
        assertEquals(1,n);
    }

    @Test
    void deleteById() {
        int n = studentMapper.deleteById(8001);
        assertEquals(1,n);
    }

    @Test
    void batchInsert() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = Student.builder()
                    .studentId(7000 + i)
                    .clazzId(1)
                    .studentName("测试学生" + i)
                    .hometown("江苏南京")
                    .birthday(LocalDate.now())
                    .build();
            students.add(student);
        }
        int n = studentMapper.batchInsert(students);
        assertEquals(10,n);
    }

    @Test
    void batchDelete() {
        List<Integer> idList = List.of(7000, 7001, 7002);
        int n = studentMapper.batchDelete(idList);
        assertEquals(3,n);
    }

    @Test
    void batchUpdate() {
        List<Student> studentsToUpdate = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = Student.builder()
                    .studentId(7000 + i)
                    .studentName("更新" + i)
                    .build();
            studentsToUpdate.add(student);
        }

        int updateCount = studentMapper.batchUpdate(studentsToUpdate);
        assertEquals(1, updateCount);
    }

    @Test
    void selectByDynamicSql() {
        Student student = Student.builder().hometown("州").build();
        List<Student> students = studentMapper.selectByDynamicSql(student);
        students.forEach(System.out::println);
    }

    @Test
    void getStudentManyToOne() {
        Student student = studentMapper.getStudentManyToOne(1001);
        log.info("{},{},{}",student.getStudentName(),student.getHometown(),student.getClazz().getClazzName());
    }

    @Test
    void getStudent() {
        Student student = studentMapper.getStudent(1001);
        log.info("{},{}",student.getStudentName(),student.getHometown());
        log.info("{}",student.getClazz().getClazzName());
        List<Course> courses = student.getCourses();
        courses.forEach(course -> log.info("{}",course.getCourseName()));
    }
}