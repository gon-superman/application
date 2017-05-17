package com.qph.app.config;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;


@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Resource
    private JedisConnectionFactory factory;
    
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);

    @Bean
    @Override
    public CacheManager cacheManager() {
    	if (logger.isDebugEnabled()){
    		logger.debug("RedisCacheConfig.cacheManager : 实例化");
    	}
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
        cacheManager.setDefaultExpiration(60 * 30); // 30min
        return cacheManager;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
    	if (logger.isDebugEnabled()){
    		logger.debug("RedisCacheConfig.redisTemplate : 实例化");
    	}
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(factory);

        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

   /**
    * 自定义key.
     * 此方法将会根据完全限定类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
    */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
    	if (logger.isDebugEnabled()){
    		logger.debug("RedisCacheConfig.keyGenerator : 实例化");
    	}
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                // This will generate a unique key of the class name, the method name
                // and all method parameters appended.
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                if (logger.isDebugEnabled()){
            		logger.debug("RedisCacheConfig : keyGenerator <== " + sb.toString());
            	}
                return sb.toString();
            }
        };
    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(cacheManager());
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        // 用于捕获从Cache中进行CRUD时的异常的回调处理器。
        return new SimpleCacheErrorHandler();
    }
}