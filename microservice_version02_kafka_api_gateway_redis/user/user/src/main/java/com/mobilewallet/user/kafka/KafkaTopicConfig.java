package com.mobilewallet.user.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic transactionTopic() {
        return TopicBuilder.name("tranasction-topica")
            .build();
    }

    @Bean
    public NewTopic walletTopic() {
        return TopicBuilder.name("wallet-topica")
            .build();
    }
}
