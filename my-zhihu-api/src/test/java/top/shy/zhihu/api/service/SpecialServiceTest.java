package top.shy.zhihu.api.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.shy.zhihu.api.entity.Special;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SpecialServiceTest {
    @Resource
    private SpecialService SpecialService;

    @Test
    void getAll() {
        List<Special> all = SpecialService.getAll();
        all.forEach(System.out::println);
    }
}