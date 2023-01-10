package com.flab.livecommerce.supports;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Distribution Lock : redis - Redisson library 이용
 * pubsub 기반으로 lock 구현 제공
 * 락 획득 재시도를 기본으로 제공하기 때문제 재시도 로직을 직접 구현해줘야 하는 lettuce 보다 분산락을 사용하고 관리하기 편리하여 선택
 *
 * Production Level에서는 싱글스레드로 동작하는 redis 특성상 분산락과 기존 세션 스토리지 redis를 분리하여 사용하는 것이 성능상 더 좋다.
 * 해당 프로젝트에서는 데이터의 양이 적고, 테스트의 편의성을 위해 하나의 redis에서 동작하도록 구현하였음.
 * 추후 설정 변경만으로도 쉽게 분리가 가능하게끔 구현함
 */

@Configuration
public class RedisDistributionLockConfig {

    @Value("${spring.redis.host}")
    private String redissonHost;

    @Value("${spring.redis.lock.port}")
    private int redissonPort;

    private static final String REDISSON_HOST_PREFIX = "redis://";

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(REDISSON_HOST_PREFIX + redissonHost + ":" + redissonPort);
        return Redisson.create(config);
    }
}
