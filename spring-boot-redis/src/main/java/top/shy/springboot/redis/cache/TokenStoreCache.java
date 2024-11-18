package top.shy.springboot.redis.cache;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.shy.springboot.redis.config.RedisCache;
import top.shy.springboot.redis.config.RedisKeys;
import top.shy.springboot.redis.vo.UserLoginVO;

import java.util.ArrayList;
import java.util.List;
import static top.shy.springboot.redis.config.RedisCache.HOUR_SIX_EXPIRE;

@Component
@AllArgsConstructor
public class TokenStoreCache {
    private final RedisCache redisCache;

    public void saveUser(String accessToken, UserLoginVO user) {
        String accessTokenKey = RedisKeys.getAccessTokenKey(accessToken);
        String userIdKey = RedisKeys.getUserIdKey(user.getPkId());

        if (redisCache.get(userIdKey) != null) {
            redisCache.delete(String.valueOf(redisCache.get(userIdKey)));
        }

        System.out.println("[TokenStoreCache] accessToken = " + accessToken);

        // 设置新的用户 token
        redisCache.set(userIdKey, accessToken, HOUR_SIX_EXPIRE);
        redisCache.set(accessTokenKey, user, HOUR_SIX_EXPIRE);
    }

    public UserLoginVO getUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        return JSON.to(UserLoginVO.class, redisCache.get(key));
    }

    public void deleteUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisCache.delete(key);
    }

    public void deleteUserById(Long id) {
        String userId = RedisKeys.getUserIdKey(id);
        Object cachedAccessToken = redisCache.get(userId);
        if (cachedAccessToken != null) {
            String key = String.valueOf(cachedAccessToken);
            redisCache.delete(key);
        }
    }

    public void deleteUserByIds(List<Long> ids) {
        List<String> keys = new ArrayList<>();
        for (Long id : ids) {  // 使用 Long 而不是 Integer
            String userId = RedisKeys.getUserIdKey(id);
            Object cachedAccessToken = redisCache.get(userId);
            if (cachedAccessToken != null) {
                String key = String.valueOf(cachedAccessToken);
                keys.add(key);
            }
        }
        redisCache.delete(keys);
    }
}
