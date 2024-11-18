package top.shy.springboot.qa.mapper;

import top.shy.springboot.qa.entity.Question;

import java.util.List;

public interface QuestionMapper {
    List<Question> selectAll();
    List<Question> selectByPage(int limit,int offset);

    Boolean insertQuestion(String content,Long userId,String userName,String time);
}
