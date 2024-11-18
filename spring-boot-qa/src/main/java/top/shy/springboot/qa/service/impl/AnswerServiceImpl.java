package top.shy.springboot.qa.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.shy.springboot.qa.entity.Answer;
import top.shy.springboot.qa.mapper.AnswerMapper;
import top.shy.springboot.qa.service.AnswerService;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Resource
    public AnswerMapper answerMapper;

    @Override
    public List<Answer> getById(Long qId){
        List<Answer> answers = answerMapper.selectById(qId);
        return answers;
    }
    @Override
    public Boolean insertAnswer(Long qId,String content,String userName){
        return answerMapper.insertAnswer(qId,content,userName);
    }
}
