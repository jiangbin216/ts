package cn.ts.core.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 通用缓存工具类
 *
 * @author Created by YL on 2017/6/2.
 */
public class JedisUtil {

    /**
     * 为给定 key 设置过期时间
     *
     * @param redisTemplate
     * @param key           指定key
     * @param timeout       过期时间
     * @param unit          过期单位
     */
    public static void expire(RedisTemplate<String, String> redisTemplate, String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 设置指定 key 的值
     *
     * @param key
     * @param value
     */
    public static void set(RedisTemplate<String, String> redisTemplate, String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout (以unit为单位)。
     *
     * @param key
     * @param value
     * @param timeout 过期时间
     * @param unit    过期单位
     */
    public static void set(RedisTemplate<String, String> redisTemplate, String key, String value, long
            timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public static String get(RedisTemplate<String, String> redisTemplate, String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除值
     *
     * @param key
     */
    public static void delete(RedisTemplate redisTemplate, String key) {
        redisTemplate.delete(key);
    }

    /**
     * 将一个或多个值插入到列表头部
     *
     * @param key
     * @param values
     */
    public static long lpush(RedisTemplate<String, String> redisTemplate, String key, String... values) {
        return redisTemplate.opsForList().leftPushAll(key, values).longValue();
    }

    /**
     * 移除列表元素
     *
     * @param key
     * @param count
     * @param value
     */
    public static void lrem(RedisTemplate<String, String> redisTemplate, String key, long count, String value) {
        redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 获取列表指定范围内的元素
     *
     * @param key
     * @param offset
     * @param limit
     */
    public static List<String> lrange(RedisTemplate<String, String> redisTemplate, String key, long offset, long
            limit) {
        return redisTemplate.opsForList().range(key, offset, limit);
    }

    /**
     * 获取列表长度
     *
     * @param key
     */
    public static long llen(RedisTemplate<String, String> redisTemplate, String key) {
        return redisTemplate.opsForList().size(key).longValue();
    }

    /**
     * 移除列表元素
     *
     * @param key
     * @param values
     */
    public static void srem(RedisTemplate<String, String> redisTemplate, String key, String... values) {
        redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 向集合添加一个或多个成员
     *
     * @param key
     * @param timeout 过期时间
     * @param unit    过期单位
     * @param value
     */
    public static void sadd(RedisTemplate<String, String> redisTemplate, String key, long timeout,
                            TimeUnit unit, String... value) {
        redisTemplate.opsForSet().add(key, value);
        redisTemplate.opsForSet().getOperations().expire(key, timeout, unit);
    }

    /**
     * 返回集合中的所有成员
     *
     * @param key
     */
    public static Set<String> smembers(RedisTemplate<String, String> redisTemplate, String key) {
        return redisTemplate.opsForSet().members(key);
    }
}
