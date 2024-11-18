package top.shy.springboot.qa.service;

import top.shy.springboot.qa.entity.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> getById(Long qId);

    Boolean insertAnswer(Long qId,String content,String userName);
}
