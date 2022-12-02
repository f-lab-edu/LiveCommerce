package com.flab.livecommerce.supports;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisSellerSessionConfig {

    @Value("${spring.redis.session.seller.host}")
    private String redisHost;

    @Value("${spring.redis.session.seller.port}")
    private int redisPort;

    @Bean(name = "sellerRedisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }

    @Bean(name = "sellerRedistemplate")
    public RedisTemplate<String, Object> redisTemplate(
        ObjectMapper objectMapper,
        @Qualifier("sellerRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory
    ) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        return redisTemplate;
    }
}
