package com.mobilewallet.bankAccount.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.mobilewallet.bankAccount.entity.BankAccount;

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
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, BankAccount.class);
        return config;
    }

    // creating cosumer instances
    // can make change like <String, User>
    @Bean
    public ConsumerFactory<String, BankAccount> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    // to listen from all topics
    // can make change like <String, User>
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, BankAccount>> kafkaListenerContainerFactory(
        ConsumerFactory<String, BankAccount> consumerFactory
    ){
            ConcurrentKafkaListenerContainerFactory<String, BankAccount> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
    }
}
