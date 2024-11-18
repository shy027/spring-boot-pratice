package top.shy.springboot.filter_interceptor.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.shy.springboot.filter_interceptor.utils.OssUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @GetMapping("test")
    public String test() {
        return "test";
    }
    @Resource
    private OssUtil ossUtil;

    @PostMapping("upload")
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileUrl = ossUtil.uploadFile(file);
        Map<String, Object> response = new HashMap<>();
        response.put("fileUrl", fileUrl);
        return response;
    }
}
