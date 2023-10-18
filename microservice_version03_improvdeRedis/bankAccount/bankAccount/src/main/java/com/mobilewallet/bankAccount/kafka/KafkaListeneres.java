package com.mobilewallet.bankAccount.kafka;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.mobilewallet.bankAccount.entity.BankAccount;
// import com.mobilewallet.user.entity.Transaction;
// import com.mobilewallet.user.entity.Wallet;
// import com.mobilewallet.user.service.walletService.WalletService;
import com.mobilewallet.bankAccount.service.bankAccountService.BankAccountService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaListeneres{

    private final BankAccountService bankAccountService;
    // private Wallet updateWallet = null;
    // private final WalletService walletService;
    // private final CompletableFuture<Transaction> transactionFuture = new CompletableFuture<>();
   
    @KafkaListener(topics = "bankaccount-process-topic", groupId = "group_id")
    public void  transactionProcessEvent(BankAccount bankAccount) {
        System.out.println("bankaccount-process-topic: " + bankAccount); 
        try{
            bankAccountService.save(bankAccount);
        }
        catch(Exception e)
        {
            System.out.println("Error in saving bank account" + e.getMessage());
        }
        
    }
   


    // @KafkaListener(topics = "tranasction-processed-topic", groupId = "group_id")
    // public void  transactionProcessEvent(Transaction transaction) {
    //     System.out.println("transaction-processed-topic: " + transaction);
    //     if ("FAIL".equals(transaction.getStatus())) {
    //         Wallet wallet = walletService.findById(transaction.getFromWalletId());
    //         wallet.setBalance(wallet.getBalance() + transaction.getAmount());
    //         walletService.update(wallet);
    //     } else if ("SUCCESSFUL".equals(transaction.getStatus())) {
    //         // do nothing
    //     }

    //     transactionFuture.complete(transaction);
    // }

    // public CompletableFuture<Transaction> transactionStatus() {
    //     return transactionFuture;
    }

    // @KafkaListener(topics = "transaction-fail", groupId = "group_id")
    // public void transactionSuccessfull(Transaction transaction) {
    //    Wallet wallet = walletService.findById(transaction.getFromWalletId());
    //    wallet.setBalance(wallet.getBalance() + transaction.getAmount());
    //    walletService.update(wallet);
    // }

    // @KafkaListener(topics = "wallet-topica", groupId = "group_id")
    // public void consume(Wallet wallet) {
    //    System.out.println("Consumed message: " + wallet);
    //    updateWallet = wallet;
    // }

    // public Wallet getUpdateWallet() {
    //     return updateWallet;
    // }

