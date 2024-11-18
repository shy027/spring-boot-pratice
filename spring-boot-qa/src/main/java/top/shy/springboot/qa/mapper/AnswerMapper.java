package top.shy.springboot.qa.mapper;

import top.shy.springboot.qa.entity.Answer;

import java.util.List;

public interface AnswerMapper {
    List<Answer> selectById(Long qId);
    Boolean insertAnswer(Long qId,String content,String userName);
}
