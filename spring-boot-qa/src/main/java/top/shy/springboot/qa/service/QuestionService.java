package top.shy.springboot.qa.service;

import top.shy.springboot.qa.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAll();

    List<Question> getByPage(int limit, int offset);
    Boolean insertQuestion(String content,Long userId,String userName,String time);
}
