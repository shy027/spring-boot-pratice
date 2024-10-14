package top.shy.springboot.database.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.shy.springboot.database.common.ResponseResult;
import top.shy.springboot.database.mapper.SpecialMapper;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/zhihu")
public class SpecialController {
    private final SpecialMapper specialMapper;

    @GetMapping("/specials")
    public ResponseResult getSpecials(){
        return ResponseResult.builder()
                .code(200)
                .msg("请求成功")
                .data(specialMapper.findAll())
                .build();
    }
}
