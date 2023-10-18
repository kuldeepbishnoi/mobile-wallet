package com.mobilewallet.transaction.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.mobilewallet.transaction.entity.Transaction;
import com.mobilewallet.transaction.entity.Wallet;
import com.mobilewallet.transaction.service.transactionService.TransactionService;
import com.mobilewallet.transaction.service.walletService.WalletService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaListeneres{

    private final TransactionService transactionService;
    // private final KafkaTemplate<String, Wallet> kafkaTemplate;
    // private final WalletService walletServiceClient;

    @KafkaListener(topics = "tranasction-process-topic", groupId = "group_id")
    public void consume(Transaction transaction) {
         System.out.println("Consumed message: " + transaction);
        transactionService.completeTransaction(transaction);
        // Wallet wallet = walletServiceClient.findById(transaction.getFromWalletId());
        // kafkaTemplate.send("wallet-topica", wallet);

    }
}
