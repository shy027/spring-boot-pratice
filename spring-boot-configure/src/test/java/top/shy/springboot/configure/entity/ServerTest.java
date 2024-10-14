package top.shy.springboot.configure.entity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ServerTest {
    @Resource
    private Server server;

    @Test
    void testServer(){
        System.out.println(server);
    }
}