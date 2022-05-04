package com.sndshun.library.common.config;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;


import com.sndshun.library.dto.UserInfoDTO;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

/**
 * @Author mr.sun
 * @Creation 2022 2022/3/26 17:44
 * @Desc:
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * 配置默认的缓存管理器
     */
    @Primary
    @Bean("defaultCacheManager")
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置最后一次写入后经过固定时间过期. 7天
                .expireAfterWrite(7, TimeUnit.DAYS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(1000));
        return cacheManager;
    }

    /**
     * token放在本地缓存中
     * 可以改造为放在redis中
     */
    @Bean("tokenCacheManager")
    public Cache<String, UserInfoDTO> caffeineCache() {
        return Caffeine.newBuilder()
                // 设置最后一次访问后经过固定时间过期.
                .expireAfterAccess(30L, TimeUnit.MINUTES)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(10000)
                .build();
    }
    /**
     * 邮件验证码
     */
    @Bean("emailCodeManager")
    public Cache<String,String> emailCodeCache(){

        return Caffeine.newBuilder()
                // 设置最后一次访问后经过固定时间过期. 60秒
                .expireAfterAccess(60L, TimeUnit.SECONDS)
                .initialCapacity(100)
                .maximumSize(10000)
                .build();
    }
}
