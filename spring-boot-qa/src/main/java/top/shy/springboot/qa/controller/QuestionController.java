package top.shy.springboot.qa.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.shy.springboot.qa.common.ResponseResult;
import top.shy.springboot.qa.entity.Question;
import top.shy.springboot.qa.service.QuestionService;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    @GetMapping("/all")
    public ResponseResult getAll(){
        List<Question> all = questionService.getAll();
        return ResponseResult.builder()
                .code(200)
                .msg("数据获取成功")
                .data(all)
                .build();
    }

    @GetMapping("/page")
    public ResponseResult getByPage(@RequestParam int limit,@RequestParam int offset){
        Map<String,Object> map = new HashMap();
        List<Question> questions = questionService.getByPage(limit,offset);
        map.put("questions",questions);
        map.put("total",questionService.getAll().size());
        return ResponseResult.builder()
                .code(200)
                .msg("数据获取成功")
                .data(map)
                .build();
    }

    @PostMapping("/submit")
    public ResponseResult insertQuestion(@RequestParam String userId, @RequestParam String username, @RequestParam String questionContent){
        // 获取当前日期和时间
        LocalDateTime now = LocalDateTime.now();

        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化日期时间
        String formattedDateTime = now.format(formatter);
        boolean success = questionService.insertQuestion(questionContent, Long.parseLong(userId), username, formattedDateTime);
        if (!success) {
            return ResponseResult.builder()
                    .code(400)
                    .msg("失败")
                    .build();
        }
        return ResponseResult.builder()
                .code(200)
                .msg("提出问题成功")
                .build();
    }

}
