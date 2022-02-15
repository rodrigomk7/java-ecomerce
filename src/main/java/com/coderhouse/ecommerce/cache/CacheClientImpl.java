package com.coderhouse.ecommerce.cache;

import com.coderhouse.ecommerce.config.AppProperties;
import com.coderhouse.ecommerce.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheClientImpl<T> implements CacheClient<T> {

    private final RedisTemplate<String, T> redisTemplate;
    private final AppProperties properties;
    private final ObjectMapper mapper;
    private HashOperations<String, String, String> hashOperations;

    @PostConstruct
    void setHashOperations() {
        hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public T save(String key, T data) throws Exception {
       hashOperations.put(Constants.MAP, key, serializeItem(data));
       this.redisTemplate.expire(Constants.MAP, Duration.ofMillis(properties.getTimeOfLife()));
       return data;
    }

    @Override
    public T recover(String key, Class<T> classValue) throws Exception {
        var item = hashOperations.get(Constants.MAP, key);
        if (item == null) return null;
        return deserializeItem(item, classValue);
    }

    @Override
    public boolean exist(String key) {
        var item = hashOperations.get(Constants.MAP, key);
        return item != null;
    }

    @Override
    public void delete(String key) {
        if(exist(key)) hashOperations.delete(Constants.MAP, key);
    }

    private String serializeItem(T item) throws Exception {
        var serializeItem = mapper.writeValueAsString(item);
        return serializeItem;
    }

    private T deserializeItem(String jsonInput, Class<T> classValue) throws Exception {
        return mapper.readValue(jsonInput, classValue);
    }



}
