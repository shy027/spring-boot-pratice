package top.shy.springboot.redis.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.shy.springboot.exception.result.Result;
import top.shy.springboot.redis.service.SmsService;

@RestController
@RequestMapping("/sms")
@AllArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public Result<Object> sendSms(@RequestParam(value="phone") String phone) {
        smsService.sendSms(phone);
        return Result.ok();
    }
}
