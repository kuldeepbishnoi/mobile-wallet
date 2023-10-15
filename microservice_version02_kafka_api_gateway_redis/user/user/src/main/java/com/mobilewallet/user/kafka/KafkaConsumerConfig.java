package com.mobilewallet.user.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.mobilewallet.user.entity.Wallet;

import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    

    // info to pass to producerFactory
    public Map<String, Object> consumerConfig(){
        // JsonDeserializer<Wallet> walletDeserializer = new JsonDeserializer<>(Wallet.class);

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Wallet.class);
        return config;
    }

    // creating cosumer instances
    // can make change like <String, User>
    @Bean
    public ConsumerFactory<String, Wallet> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    // to listen from all topics
    // can make change like <String, User>
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Wallet>> kafkaListenerContainerFactory(
        ConsumerFactory<String, Wallet> consumerFactory
    ){
            ConcurrentKafkaListenerContainerFactory<String, Wallet> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
    }
}
