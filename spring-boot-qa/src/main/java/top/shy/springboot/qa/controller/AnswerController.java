package top.shy.springboot.qa.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.shy.springboot.qa.common.ResponseResult;
import top.shy.springboot.qa.entity.Answer;
import top.shy.springboot.qa.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Resource
    public AnswerService answerService;

    @GetMapping("/all")
    public ResponseResult getById(@RequestParam Long qId){
        List<Answer> all = answerService.getById(qId);
        return ResponseResult.builder()
                .code(200)
                .msg("数据获取成功")
                .data(all)
                .build();
    }
    @PostMapping("/submit")
    public ResponseResult insertQuestion(@RequestParam Long qId, @RequestParam String content, @RequestParam String userName){
        Boolean success = answerService.insertAnswer(qId,content,userName);
        if (!success) {
            return ResponseResult.builder()
                    .code(400)
                    .msg("失败")
                    .build();
        }
        return ResponseResult.builder()
                .code(200)
                .msg("回答成功")
                .build();
    }

}
