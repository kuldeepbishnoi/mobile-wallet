package com.mobilewallet.transaction.kafka;

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

// import com.mobilewallet.transaction.kafka.entity.TransactionEntity;
import com.mobilewallet.transaction.entity.Transaction;

import org.apache.kafka.common.serialization.StringDeserializer;
// import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // info to pass to producerFactory
    public Map<String, Object> consumerConfig(){
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Transaction.class);
        return config;
    }

    // creating cosumer instances
    // can make change like <String, User>
    @Bean
    public ConsumerFactory<String, Transaction> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    // to listen from all topics
    // can make change like <String, User>
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Transaction>> kafkaListenerContainerFactory(
        ConsumerFactory<String, Transaction> consumerFactory
    ){
            ConcurrentKafkaListenerContainerFactory<String, Transaction> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
    }
}
