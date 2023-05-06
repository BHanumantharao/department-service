package com.ms.department.service.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@TestConfiguration
public class RedisConfigurationTest {

    @Value("${spring.redis.host}")
    private String REDIS_HOSTNAME;

    @Value("${spring.redis.port}")
    private int REDIS_PORT;


    public RedisConfigurationTest() {

            final RedisTemplate<String, Object> redisTemplate =
                    new RedisTemplate<String, Object>();

            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new GenericToStringSerializer<String>(String.class));

            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

            RedisStandaloneConfiguration configuration =
                    new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);

            JedisClientConfiguration jedisClientConfiguration =
                    JedisClientConfiguration.builder().build();

            JedisConnectionFactory factory =
                    new JedisConnectionFactory(configuration, jedisClientConfiguration);

            factory.afterPropertiesSet();
            redisTemplate.setConnectionFactory(factory);
        }
}