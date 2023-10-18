package com.mobilewallet.user.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic transactionProcessedTopic() {
        return TopicBuilder.name("tranasction-processed-topic")
            .build();
    }

    @Bean
    public NewTopic bankAccountProcessTopic() {
        return TopicBuilder.name("bankaccount-process-topic")
            .build();
    }

    
    @Bean
    public NewTopic transactionProcessTopic() {
        return TopicBuilder.name("tranasction-process-topic")
            .build();
    }

    // @Bean
    // public NewTopic walletTopic() {
    //     return TopicBuilder.name("wallet-topica")
    //         .build();
    // }
}
