package top.shy.springboot.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.shy.springboot.redis.entity.User;
import top.shy.springboot.redis.vo.UserInfoVO;
import top.shy.springboot.redis.vo.UserLoginVO;
@Service
public interface UserService extends IService<User> {
    UserLoginVO loginByPhone(String phone,String code);
    boolean checkUserEnabled(Long userId);
    UserInfoVO userInfo(Long userId);
}
