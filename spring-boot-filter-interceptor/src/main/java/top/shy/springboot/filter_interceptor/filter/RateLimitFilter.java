package top.shy.springboot.filter_interceptor.filter;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class RateLimitFilter implements Filter {
    private static final int LIMIT = 5;
    private static final int TIME_WINDOW = 60 * 1000;
    private static final ConcurrentHashMap<String,UserRequest> USER_REQUESTS = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String,String> CAPTCHA_STORE = new ConcurrentHashMap<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("RateLimitFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String clientIp = request.getRemoteAddr();
        UserRequest userRequest = USER_REQUESTS.compute(clientIp,(key, value) -> {
            if (value == null || System.currentTimeMillis() - value.timestamp > TIME_WINDOW){
                return new UserRequest(1,System.currentTimeMillis());
            }else {
                value.count++;
                return value;
            }
        });

        if (userRequest.count > LIMIT){
            LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
            String code = lineCaptcha.getCode();
            CAPTCHA_STORE.put(clientIp,code);
            response.setContentType("image/png");
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("RateLimitFilter destroy");
    }

    private static class UserRequest{
        int count;
        long timestamp;
        UserRequest(int count, long timestamp){
            this.count = count;
            this.timestamp = timestamp;
        }
    }
}
