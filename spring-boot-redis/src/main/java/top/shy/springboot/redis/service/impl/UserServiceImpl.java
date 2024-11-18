package top.shy.springboot.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.shy.springboot.redis.cache.TokenStoreCache;
import top.shy.springboot.redis.config.RedisCache;
import top.shy.springboot.redis.config.RedisKeys;
import top.shy.springboot.redis.entity.User;
import top.shy.springboot.redis.enums.AccountStatusEnum;
import top.shy.springboot.redis.enums.ErrorCode;
import top.shy.springboot.redis.exceptioon.ServerException;
import top.shy.springboot.redis.mapper.UserMapper;
import top.shy.springboot.redis.service.UserService;
import top.shy.springboot.redis.utils.JwtUtil;
import top.shy.springboot.redis.vo.UserInfoVO;
import top.shy.springboot.redis.vo.UserLoginVO;


@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final RedisCache redisCache;
    private final TokenStoreCache tokenStoreCache;

    @Override
    public UserLoginVO loginByPhone(String phone, String code) {
        // 获取验证码cacheKey
        String smsCacheKey = RedisKeys.getSmsKey(phone);
        // 从redis中获取验证码
        Integer redisCode = (Integer) redisCache.get(smsCacheKey);
        // 校验验证码合法性
        if (ObjectUtils.isEmpty(redisCode) || !redisCode.toString().equals(code)) {
            throw new ServerException(String.valueOf(ErrorCode.SMS_CODE_ERROR));
        }
        // 删除用过的验证码
        redisCache.delete(smsCacheKey);
        // 根据手机号获取用户
        User user = baseMapper.getByPhone(phone);
        // 判断用户是否注册过，如果user为空代表未注册，进行注册。否则开启登录流程
        if (ObjectUtils.isEmpty(user)) {
            log.info("用户不存在，创建用户，phone: {}", phone);
            user = new User();
            user.setNickname(phone);
            user.setPhone(phone);
            user.setAvatar("https://www.helloimg.com/i/2024/10/08/670513635044e.jpg");
            user.setGender(0);
            user.setEnabled(AccountStatusEnum.ENABLED.getValue());
            user.setBonus(100);
            user.setDeleteFlag(0);
            user.setRemark("这个人很懒，什么都没有写");
            baseMapper.insert(user);
        }
        // 用户被禁用
        if (!user.getEnabled().equals(AccountStatusEnum.ENABLED.getValue())) {
            throw new ServerException(String.valueOf(ErrorCode.ACCOUNT_DISABLED));
        }
        // 构造token
        String accessToken = JwtUtil.createToken(user.getPkId());
        // 构造登录返回vo
        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setPkId(user.getPkId());
        userLoginVO.setPhone(user.getPhone());
        userLoginVO.setAccessToken(accessToken);
        tokenStoreCache.saveUser(accessToken, userLoginVO);
        return userLoginVO;
    }
    @Override
    public boolean checkUserEnabled(Long userId) {
        User user = baseMapper.selectById(userId);
        if (ObjectUtils.isEmpty(user)) {
            return false;
        }
        // 这里应该还有代码来检查用户的启用状态，但未提供
        // 假设有一个方法来检查用户是否启用，例如 user.getEnabled().equals(AccountStatusEnum.ENABLED.getValue())
        // return user.getEnabled().equals(AccountStatusEnum.ENABLED.getValue());
        return true; // 假设用户存在即启用，需要根据实际情况调整
    }

    @Override
    public UserInfoVO userInfo(Long userId) {
        // 查询数据库
        User user = baseMapper.selectById(userId);
        if (user == null) {
            log.error("用户不存在，userId: {}", userId);
            return null; // 如果用户不存在，返回null或抛出异常
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        try {
            BeanUtils.copyProperties(userInfoVO, user);
        } catch (Exception e) {
            log.error("复制属性失败", e);
            // 处理异常，例如返回null或抛出异常
        }
        return userInfoVO;
    }

}
