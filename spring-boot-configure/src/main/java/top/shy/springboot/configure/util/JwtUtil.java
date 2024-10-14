package top.shy.springboot.configure.util;

import jakarta.annotation.Resource;
import cn.hutool.jwt.JWTUtil;
import org.springframework.stereotype.Component;
import top.shy.springboot.configure.config.JwtConfig;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    @Resource
    private JwtConfig jwtConfig;
    public String generateToken(Map<String, Object> claims){
        claims.put("exp", new Date(System.currentTimeMillis() + jwtConfig.getExpiration()));
        return JWTUtil.createToken(claims,jwtConfig.getSecret().getBytes());
    }
}
