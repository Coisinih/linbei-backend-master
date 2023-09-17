package com.lin.beibei.service;

import com.lin.beibei.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * Redis 测试
 *
 * @author 林北
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("linbeiString", "dog");
        valueOperations.set("linbeiInt", 1);
        valueOperations.set("linbeiDouble", 2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("linbei");
        valueOperations.set("linbeiUser", user);
        // 查
        Object linbei = valueOperations.get("linbeiString");
        Assertions.assertTrue("dog".equals((String) linbei));
        linbei = valueOperations.get("linbeiInt");
        Assertions.assertTrue(1 == (Integer) linbei);
        linbei = valueOperations.get("linbeiDouble");
        Assertions.assertTrue(2.0 == (Double) linbei);
        System.out.println(valueOperations.get("linbeiUser"));
        valueOperations.set("linbeiString", "dog");
        redisTemplate.delete("linbeiString");
    }
}
