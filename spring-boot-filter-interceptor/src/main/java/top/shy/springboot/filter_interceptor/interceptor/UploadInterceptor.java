package top.shy.springboot.filter_interceptor.interceptor;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


@Component
@Slf4j
public class UploadInterceptor implements HandlerInterceptor {
    private static final long MAX_SIZE = 300 * 1024;
    private static final String[] TYPES = {"image/png","image/jpg","image/jpeg"};
    private static final int MAX_WIDTH = 1000;
    private static final int MAX_HEIGHT = 1000;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(request instanceof MultipartHttpServletRequest)) {
            response.getWriter().write("请求类型错误，未包含文件");
            return false;
        }

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        // 1. 文件类型检查
        String fileType = file.getContentType();
        if (!StrUtil.containsAny(fileType, TYPES)) {
            response.getWriter().write("文件类型错误");
            return false;
        }

        // 2. 文件大小限制
        if (file.getSize() > MAX_SIZE) {
            response.getWriter().write("上传文件大小超出限制");
            return false;
        }

        // 3. 图片尺寸检查
        BufferedImage image = ImageIO.read(file.getInputStream());
        if (image.getWidth() > MAX_WIDTH || image.getHeight() > MAX_HEIGHT) {
            response.getWriter().write( "图片尺寸超出限制");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器响应处理完毕：path:{},endTime={}",request.getRequestURI(), LocalDateTime.now());
    }
}
