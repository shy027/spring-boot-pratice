package top.shy.springboot.filter_interceptor.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("CustomFilter 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("请求前处理中");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("响应后处理中");
    }

    @Override
    public void destroy() {
        log.info("CustomFilter 销毁");
    }
}
