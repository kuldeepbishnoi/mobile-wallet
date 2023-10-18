package com.mobilewallet.user.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
// import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
// import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
// import org.springframework.data.redis.serializer.StringRedisSerializer;


// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPooled;

@Configuration
class RedisConfiguration {
  
   @Bean
    public JedisPooled jedicPooled() {
          return new JedisPooled("redis://default:1giCWJqybtdymT3jVRwHMbMF6d8syMEo@redis-12065.c17.us-east-1-4.ec2.cloud.redislabs.com:12065");
  }
    // @Bean
    // public JedisConnectionFactory jedisConnectionFactory() {
    //     RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
    //     config.setHostName("localhost");
    //     config.setPort(6379);
    //     return new JedisConnectionFactory(config);
    // }

    // public RedisTemplate<String, Object> redisTemplate() {
    //     RedisTemplate<String, Object> template = new RedisTemplate<>();
    //     template.setConnectionFactory(jedisConnectionFactory());
    //     template.setKeySerializer(new StringRedisSerializer());
    //     template.setHashKeySerializer(new StringRedisSerializer());
    //     template.setHashKeySerializer(new JdkSerializationRedisSerializer());
    //     template.setValueSerializer(new JdkSerializationRedisSerializer());
    //     template.setEnableTransactionSupport(true);
    //     template.afterPropertiesSet();
    //     return template;
    // }
    
}
