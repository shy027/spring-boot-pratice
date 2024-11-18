package top.shy.springboot.redis.utils;

import io.netty.util.internal.ThreadLocalRandom;

public class CommonUtils {
    public static int generateCode(){
        return ThreadLocalRandom.current().nextInt(1000,9999);
    }
}
