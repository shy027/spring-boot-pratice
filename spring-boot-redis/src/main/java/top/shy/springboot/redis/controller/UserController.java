package top.shy.springboot.redis.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.shy.springboot.exception.result.Result;
import top.shy.springboot.redis.cache.TokenStoreCache;
import top.shy.springboot.redis.enums.ErrorCode;
import top.shy.springboot.redis.exceptioon.ServerException;
import top.shy.springboot.redis.service.UserService;
import top.shy.springboot.redis.utils.JwtUtil;
import top.shy.springboot.redis.vo.UserInfoVO;
import top.shy.springboot.redis.vo.UserLoginVO;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final TokenStoreCache tokenStoreCache;

    @PostMapping("/login")
    @Operation(summary = "手机短信登录")
    public Result<UserLoginVO> loginByPhone(@RequestParam(value="phone") String phone,@RequestParam(value="code") String code){
        return Result.ok(userService.loginByPhone(phone, code));
    }

    @GetMapping("info")
    @Operation(summary = "查询用户信息")
    public Result<UserInfoVO> userInfo() {
        // 获取HttpServletRequest对象
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        // 从request中获取accessToken
        String accessToken = JwtUtil.getAccessToken(request);

        // accessToken为空，抛出UNAUTHORIZED的异常信息
        if (StringUtils.isBlank(accessToken)) {
            throw new ServerException(String.valueOf(ErrorCode.UNAUTHORIZED));
        }

        // 校验accessToken是否有效，无效也是抛出UNAUTHORIZED异常信息
        if (!JwtUtil.validate(accessToken)) {
            throw new ServerException(String.valueOf(ErrorCode.UNAUTHORIZED));
        }

        // 根据accessToken，从Redis中查询到用户信息
        UserLoginVO user = tokenStoreCache.getUser(accessToken);

        // 没查到，抛出LOGIN_STATUS_EXPIRE异常信息
        if (ObjectUtils.isEmpty(user)) {
            throw new ServerException(String.valueOf(ErrorCode.LOGIN_STATUS_EXPIRE));
        }

        // 验证用户是否可用
        boolean enabledFlag = userService.checkUserEnabled(user.getPkId());

        // 不可用，抛出ACCOUNT_DISABLED异常信息
        if (!enabledFlag) {
            throw new ServerException(String.valueOf(ErrorCode.ACCOUNT_DISABLED));
        }

        // 根据id查询到用户信息，返回给客户端
        return Result.ok(userService.userInfo(user.getPkId()));
    }
}
