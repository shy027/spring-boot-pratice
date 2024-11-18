package top.shy.springboot.exception.service;

import org.springframework.stereotype.Service;
import top.shy.springboot.exception.exception.ServerException;

@Service
public class ExceptionService {
    public void unAuthorizedError(){
        throw new ServerException("没有登录");
    }
    public void systemError(){
        throw new ServerException("系统异常");
    }
}
