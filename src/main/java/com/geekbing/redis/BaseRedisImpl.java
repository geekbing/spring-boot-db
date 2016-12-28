package com.geekbing.redis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by bing on 28/12/2016.
 */
public class BaseRedisImpl<T> implements BaseRedis<T> {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void add(String key, Long time, T object) {
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(object), time, TimeUnit.MINUTES);
    }

    @Override
    public void add(String key, Long time, List<T> objects) {
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(objects), time, TimeUnit.MINUTES);
    }

    @Override
    public T get(String key, Class<T> tClass) {
        Gson gson = new Gson();
        T object = null;
        String json = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(json)) {
            object = gson.fromJson(json, tClass);
        }
        return object;
    }

    @Override
    public List<T> getList(String key) {
        Gson gson = new Gson();
        List<T> ts = null;
        String listJson = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(listJson)) {
            ts = gson.fromJson(listJson, new TypeToken<List<T>>() {
            }.getType());
        }
        return ts;
    }

    @Override
    public void delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}
