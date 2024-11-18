package top.shy.springboot.qa.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.shy.springboot.qa.entity.Question;
import top.shy.springboot.qa.mapper.QuestionMapper;
import top.shy.springboot.qa.service.QuestionService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    public QuestionMapper questionMapper;
    @Override
    public List<Question> getAll() {
        List<Question> questions = questionMapper.selectAll();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        questions.forEach(question -> {
//            String format = df.format(new Date(Long.parseLong(question.getTime() + "000")));
//            question.setTime(format);
//        });
        return questions;
    }
    @Override
    public List<Question> getByPage(int limit,int offset){
        return questionMapper.selectByPage(limit,offset);
    }

    @Override
    public Boolean insertQuestion(String content,Long userId,String userName,String time){
        return questionMapper.insertQuestion(content, userId, userName, time);
    }
}
