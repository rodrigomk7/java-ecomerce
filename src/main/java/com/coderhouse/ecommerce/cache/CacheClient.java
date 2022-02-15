package com.coderhouse.ecommerce.cache;

public interface CacheClient<T> {

    T save(String key, T data) throws Exception;
    T recover(String key, Class<T> classValue) throws Exception;
    boolean exist(String key);
    void delete(String key);
}
