package com.mobilewallet.transaction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableFeignClients
public class TransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}

	// @Bean
	// CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
	// 	return args -> {
	// 		for (int i = 0; i < 10; i++)
	// 		{
	// 			kafkaTemplate.send("transaction-topic", "transaction clr topic " + i);
	// 		}
		
	// 	};
	// }


}
