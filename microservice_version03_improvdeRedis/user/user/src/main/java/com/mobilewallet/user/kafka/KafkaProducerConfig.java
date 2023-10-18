package com.mobilewallet.user.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.mobilewallet.user.entity.BankAccount;
import com.mobilewallet.user.entity.Transaction;

import java.util.HashMap;
import java.util.Map;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@Configuration
// @EnableKafka
public class KafkaProducerConfig {

    // private final Logger logger = LoggerFactory.getLogger(KafkaProducerConfig.class);

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // @Value("${spring.kafka.properties.sasl.mechanism}")
    // private String saslMechanism;

    // @Value("${spring.kafka.properties.sasl.jaas.config}")
    // private String saslJaasConfig;

    // @Value("${spring.kafka.properties.security.protocol}")
    // private String securityProtocol;

    // @Value("${spring.kafka.properties.schema.registry.url}")
    // private String schemaRegistryUrl;

    // @Value("${spring.kafka.properties.schema.basic.auth.credentials.source}")
    // private String schemaAuthCredentialsSource;

    // @Value("${spring.kafka.properties.schema.basic.auth.user.info}")
    // private String schemaAuthUserInfo;

    
    // info to pass to producerFactory
    @Bean
    public Map<String, Object> producerConfig(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        // config.put("sasl.mechanism", saslMechanism);
        // config.put("sasl.jaas.config", saslJaasConfig);
        // config.put("security.protocol", securityProtocol);
        // config.put("schema.registry.url", schemaRegistryUrl);
        // config.put("schema.basic.auth.credentials.source", schemaAuthCredentialsSource);
        // config.put("schema.basic.auth.user.info", schemaAuthUserInfo);        
        return config;
    }

    // creating producer instances
    // can make change like <String, User>
    @Bean
    public ProducerFactory<String, Transaction> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    // to send messages
    // can make change like <String, User>
    @Bean
    public KafkaTemplate<String, Transaction> kafkaTemplate(ProducerFactory<String, Transaction> producerFactory){
        return new KafkaTemplate<>(producerFactory());
    }

    // creating producer instances
    // can make change like <String, User>
    @Bean
    public ProducerFactory<String, BankAccount> bankProducerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    // to send messages
    // can make change like <String, User>
    @Bean
    public KafkaTemplate<String, BankAccount> bankKafkaTemplate(ProducerFactory<String, BankAccount> bankProducerFactory){
        return new KafkaTemplate<>(bankProducerFactory());
    }
}
